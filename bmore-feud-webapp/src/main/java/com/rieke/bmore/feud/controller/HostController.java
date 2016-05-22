package com.rieke.bmore.feud.controller;

import com.rieke.bmore.feud.board.Gameboard;
import com.rieke.bmore.feud.category.Category;
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
 * Created by tylerrieke on 5/14/16.
 */
@Controller
@RequestMapping("/host")
public class HostController {

    @RequestMapping(value="/category", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<Map<String, Object>> getCategory(
            HttpServletRequest request) {

        Map<String, Object> responseMap = new HashMap<>();


        responseMap.put("category",getCurrentCategory());
        responseMap.put("isFastMoney",!Gameboard.active);
        return new ResponseEntity<>(responseMap,
                HttpStatus.OK);
    }

    @RequestMapping(value="/next", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<Map<String, Object>> getNextCategory(
            HttpServletRequest request) {

        Map<String, Object> responseMap = new HashMap<>();
        FastMoney.iterateNextCategoryIndex();

        responseMap.put("category",getCurrentCategory());
        return new ResponseEntity<>(responseMap,
                HttpStatus.OK);
    }

    private Category getCurrentCategory() {
        Category category;
        if(Gameboard.active) {
            category = Gameboard.category;
        } else {
            category = FastMoney.currentPlayer.getCategories()[FastMoney.selectedCategoryIndex];
        }

        return category;
    }
}
