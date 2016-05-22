package com.rieke.bmore.feud.controller;

import com.rieke.bmore.feud.fastmoney.FastMoney;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by tylerrieke on 4/16/16.
 */
@Controller
@RequestMapping("/fastmoney")
public class FastMoneyController {

    @RequestMapping(method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<Map<String, Object>> getGameBoard(
            HttpServletRequest request) {
        Map<String, Object> responseMap = new HashMap<>();
        responseMap.put("gameboard", new FastMoney());
        return new ResponseEntity<>(responseMap,
                HttpStatus.OK);
    }
}
