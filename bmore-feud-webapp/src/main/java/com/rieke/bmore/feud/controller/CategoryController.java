package com.rieke.bmore.feud.controller;

import com.rieke.bmore.feud.category.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
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
 * Created by tcrieke on 3/15/15.
 */
@Controller
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @RequestMapping(value="/next", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<Map<String, Object>> getSettingForService(
            HttpServletRequest request) {

        Map<String, Object> responseMap = new HashMap<String,Object>();
        responseMap.put("category", categoryService.getNextCategory());
        return new ResponseEntity<>(responseMap,
                HttpStatus.OK);
    }

}
