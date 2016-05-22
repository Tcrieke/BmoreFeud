package com.rieke.bmore.feud.controller;

import com.rieke.bmore.feud.admin.FastMoneyAdminService;
import com.rieke.bmore.feud.board.Gameboard;
import com.rieke.bmore.feud.fastmoney.FastMoney;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by tylerrieke on 4/16/16.
 */
@Controller
@RequestMapping("/fastmoney_admin")
public class FastMoneyAdminController {

    @Autowired
    private FastMoneyAdminService fastMoneyAdminService;

    @RequestMapping(value = "/enable_player", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<Map<String, Object>> updateTeamScore(
            @RequestParam(value = "player1", required = false) Boolean player1,
            @RequestParam(value = "player2", required = false) Boolean player2, HttpServletRequest request) {

        player1=(player1==null?false:player1);
        player2=(player2==null?false:player2);


        Map<String, Object> responseMap = new HashMap<>();

        fastMoneyAdminService.getGameBoard().getPlayer1().setVisible(player1);
        fastMoneyAdminService.getGameBoard().getPlayer2().setVisible(player2);

        responseMap
                .put("gameboard", fastMoneyAdminService.getGameBoard());
        return new ResponseEntity<>(responseMap,
                HttpStatus.OK);
    }

    @RequestMapping(value = "/flip", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<Map<String, Object>> flipValue(
            @RequestParam(value = "category", required = true) Integer categoryId,
            @RequestParam(value = "value", required = true) String value,
            @RequestParam(value = "visible", required = false) Boolean visible, HttpServletRequest request) {

        visible = (visible==null?true:visible);

        Map<String, Object> responseMap = new HashMap<>();
        fastMoneyAdminService.flipCategoryValue(categoryId, value, visible);
        responseMap
                .put("gameboard", fastMoneyAdminService.getGameBoard());
        return new ResponseEntity<>(responseMap,
                HttpStatus.OK);
    }

    @RequestMapping(value = "/update_answer", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<Map<String, Object>> updateTeamName(
            @RequestParam(value = "answer", required = true) Integer answerIndex,
            @RequestParam(value = "value", required = false) String value,
            @RequestParam(value = "visible", required = false) Boolean visible,
            @RequestParam(value = "valueVisible", required = false) Boolean valueVisible,HttpServletRequest request) {

        value = (value!=null?value.trim():null);
        Map<String, Object> responseMap = new HashMap<>();
        fastMoneyAdminService.updateAnswer(answerIndex, value, visible, valueVisible);
        responseMap
                .put("gameboard", fastMoneyAdminService.getGameBoard());
        return new ResponseEntity<Map<String, Object>>(responseMap,
                HttpStatus.OK);
    }

    @RequestMapping(value = "/current_player", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<Map<String, Object>> currentPlayer(
            @RequestParam(value = "player", required = true) int player,HttpServletRequest request) {

        Map<String, Object> responseMap = new HashMap<>();
        fastMoneyAdminService.setCurrentPlayer(player);
        responseMap
                .put("gameboard", fastMoneyAdminService.getGameBoard());
        return new ResponseEntity<>(responseMap,
                HttpStatus.OK);
    }

    @RequestMapping(value = "/select_category", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<Map<String, Object>> selectCategory(
            @RequestParam(value = "categoryIndex", required = true) Integer categoryIndex,HttpServletRequest request) {

        Map<String, Object> responseMap = new HashMap<>();
        fastMoneyAdminService.setSelectedCategory(categoryIndex);
        responseMap
                .put("gameboard", fastMoneyAdminService.getGameBoard());
        return new ResponseEntity<>(responseMap,
                HttpStatus.OK);
    }

    @RequestMapping(value = "/family_feud", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<Map<String, Object>> familyFeud(HttpServletRequest request, HttpServletResponse response) {

        Map<String, Object> responseMap = new HashMap<>();
        FastMoney.active=false;
        Gameboard.active=true;
        responseMap
                .put("gameboard", fastMoneyAdminService.getGameBoard());
        return new ResponseEntity<>(responseMap,
                HttpStatus.OK);
    }

    @RequestMapping(value = "/new", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<Map<String, Object>> selectCategory(HttpServletRequest request) {

        Map<String, Object> responseMap = new HashMap<>();
        fastMoneyAdminService.newRound();
        responseMap
                .put("gameboard", fastMoneyAdminService.getGameBoard());
        return new ResponseEntity<>(responseMap,
                HttpStatus.OK);
    }

    protected String toFamilyFeud(HttpServletRequest request, HttpServletResponse response) {
        String logInfo = "";

        String url = "/bmorefeud/";
        try {

            RequestDispatcher dispatcher =
                    request.getRequestDispatcher("/bmorefeud/");
            dispatcher.forward(request, response);

            final StringBuilder builder = new StringBuilder();
            builder.append("audit:->: logout success event for user[");
            builder.append("];  ");
            logInfo = builder.toString();
        } catch (ServletException | IOException e) {
        }
        return logInfo;
    }


}
