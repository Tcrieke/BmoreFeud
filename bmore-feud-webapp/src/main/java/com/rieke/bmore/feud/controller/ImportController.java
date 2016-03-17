package com.rieke.bmore.feud.controller;

import com.rieke.bmore.feud.importer.ImportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by tcrieke on 3/20/15.
 */
@Controller
@RequestMapping("/import")
public class ImportController {

    @Autowired
    private ImportService importServiceService;

    @RequestMapping(value="/categories", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<Map<String, Object>> getSettingForService(
            @RequestParam(value = "file", required = true) String fileLoc,
            HttpServletRequest request) {


        Map<String, Object> responseMap = new HashMap<String,Object>();
        responseMap.put("category", importServiceService.parseFile(new File(fileLoc)));
        return new ResponseEntity<>(responseMap,
                HttpStatus.OK);
    }
}
