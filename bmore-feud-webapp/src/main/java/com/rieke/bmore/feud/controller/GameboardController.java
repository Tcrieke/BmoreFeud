package com.rieke.bmore.feud.controller;

import com.rieke.bmore.feud.board.Gameboard;
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
 * Created by tcrieke on 3/16/15.
 */
@Controller
@RequestMapping("/gameboard")
public class GameboardController {

    @RequestMapping(method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<Map<String, Object>> getGameBoard(
            HttpServletRequest request) {
        Map<String, Object> responseMap = new HashMap<String,Object>();
        responseMap.put("gameboard", new Gameboard());
        return new ResponseEntity<>(responseMap,
                HttpStatus.OK);
    }
}
