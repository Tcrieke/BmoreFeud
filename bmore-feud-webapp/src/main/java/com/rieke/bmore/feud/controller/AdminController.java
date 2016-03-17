package com.rieke.bmore.feud.controller;

import com.rieke.bmore.feud.admin.AdminService;
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
 * Created by tcrieke on 3/16/15.
 */
@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @RequestMapping(value="/incorrect", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<Map<String, Object>> markIncorrect(
            @RequestParam(value = "quiet", required = false) Boolean quiet,
            HttpServletRequest request) {

        quiet = (quiet==null?false:quiet);
        Map<String, Object> responseMap = new HashMap<String,Object>();
        adminService.markIncorrect(quiet);
        responseMap.put("gameboard", adminService.getGameBoard());
        return new ResponseEntity<Map<String, Object>>(responseMap,
                HttpStatus.OK);
    }

    @RequestMapping(value="/subincorrect", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<Map<String, Object>> subIncorrect(
            HttpServletRequest request) {

        Map<String, Object> responseMap = new HashMap<String,Object>();

        if(adminService.getGameBoard().getIncorrectCount()>0) {
            adminService.getGameBoard().setIncorrectCount(adminService.getGameBoard().getIncorrectCount() - 1);
        }
        responseMap.put("gameboard", adminService.getGameBoard());
        return new ResponseEntity<Map<String, Object>>(responseMap,
                HttpStatus.OK);
    }

    @RequestMapping(value="/new", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<Map<String, Object>> startNewRound(
            HttpServletRequest request) {

        Map<String, Object> responseMap = new HashMap<String,Object>();
        adminService.newRound();
        responseMap.put("gameboard", adminService.getGameBoard());
        return new ResponseEntity<Map<String, Object>>(responseMap,
                HttpStatus.OK);
    }

    @RequestMapping(value = "/playing", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<Map<String, Object>> setTeamPlaying(
            @RequestParam(value = "team", required = true) Integer team, HttpServletRequest request) {


        Map<String, Object> responseMap = new HashMap<String, Object>();
        adminService.setPlayingTeam(team);
        responseMap
                .put("gameboard", adminService.getGameBoard());
        return new ResponseEntity<Map<String, Object>>(responseMap,
                HttpStatus.OK);
    }

    @RequestMapping(value = "/flip", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<Map<String, Object>> flipValue(
            @RequestParam(value = "value", required = true) String value,
            @RequestParam(value = "visible", required = false) Boolean visible, HttpServletRequest request) {

        visible = (visible==null?true:visible);

        Map<String, Object> responseMap = new HashMap<String, Object>();
        adminService.setValueVisible(value,visible);
        responseMap
                .put("gameboard", adminService.getGameBoard());
        return new ResponseEntity<Map<String, Object>>(responseMap,
                HttpStatus.OK);
    }

    @RequestMapping(value = "/update_name", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<Map<String, Object>> updateTeamName(
            @RequestParam(value = "team", required = true) Integer team,
            @RequestParam(value = "name", required = true) String name, HttpServletRequest request) {

        name = name.trim();
        Map<String, Object> responseMap = new HashMap<String, Object>();
        if(!name.isEmpty()) {
            if (team == 1) {
                adminService.getGameBoard().setTeam1Name(name);
            } else {
                adminService.getGameBoard().setTeam2Name(name);
            }
        }
        responseMap
                .put("gameboard", adminService.getGameBoard());
        return new ResponseEntity<Map<String, Object>>(responseMap,
                HttpStatus.OK);
    }

    @RequestMapping(value = "/update_score", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<Map<String, Object>> updateTeamScore(
            @RequestParam(value = "team", required = true) Integer team,
            @RequestParam(value = "score", required = true) Integer score, HttpServletRequest request) {

        Map<String, Object> responseMap = new HashMap<String, Object>();
        if(team==1) {
            adminService.getGameBoard().setTeam1Score(score);
        } else {
            adminService.getGameBoard().setTeam2Score(score);
        }
        responseMap
                .put("gameboard", adminService.getGameBoard());
        return new ResponseEntity<Map<String, Object>>(responseMap,
                HttpStatus.OK);
    }
}
