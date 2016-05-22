package com.rieke.bmore.feud.controller;

import com.rieke.bmore.feud.audience.AudienceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by tcrieke on 3/27/15.
 */
@Controller
@RequestMapping("/audience")
public class AudienceController {

    @Autowired
    private AudienceService audienceService;

    @RequestMapping(value="/vote", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<Map<String, Object>> vote(
            @RequestParam(value = "category_id", required = true) Integer categoryId,
            HttpServletRequest request) {

        Map<String, Object> responseMap = new HashMap<String,Object>();
        String ip = getIpAddr(request);
        responseMap.put("success",audienceService.voteForCategory(ip,categoryId));
        responseMap.put("vote", audienceService.getVoteForIp(ip));
        responseMap.put("poll", audienceService.getPolledCategories());
        return new ResponseEntity<>(responseMap,
                HttpStatus.OK);
    }

    @RequestMapping(value="/poll", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<Map<String, Object>> getPoll(
            HttpServletRequest request) {
        Map<String, Object> responseMap = new HashMap<String,Object>();
        String ip = getIpAddr(request);
        responseMap.put("vote", audienceService.getVoteForIp(ip));
        responseMap.put("poll", audienceService.getPolledCategories());
        return new ResponseEntity<>(responseMap,
                HttpStatus.OK);
    }

    public static String getIpAddr(HttpServletRequest request) {
        String ip = request.getHeader("X-Real-IP");
        if (null != ip && !"".equals(ip.trim())
                && !"unknown".equalsIgnoreCase(ip)) {
            return ip;
        }
        ip = request.getHeader("X-Forwarded-For");
        if (null != ip && !"".equals(ip.trim())
                && !"unknown".equalsIgnoreCase(ip)) {
            // get first ip from proxy ip
            int index = ip.indexOf(',');
            if (index != -1) {
                return ip.substring(0, index);
            } else {
                return ip;
            }
        }
        return request.getRemoteAddr();
    }
}
