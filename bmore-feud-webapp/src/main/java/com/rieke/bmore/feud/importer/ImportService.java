package com.rieke.bmore.feud.importer;

import com.rieke.bmore.feud.category.Category;
import com.rieke.bmore.feud.category.CategoryService;
import com.rieke.bmore.feud.value.Value;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by tcrieke on 3/20/15.
 */
public class ImportService {

    private CategoryService categoryService;

    public ImportService() {

    }

    public ImportService(CategoryService categoryService) {
        this.categoryService = categoryService;
    }


    public boolean parseFile(File file) {
        boolean success = false;
        try {
        BufferedReader br = new BufferedReader(new FileReader(file));
            return parseFile(br);
        } catch (IOException e) {
            System.out.print("File not found");
        }
        return success;
    }

    public boolean parseFile(BufferedReader br) {
        boolean success = false;
        try {
            for (String line; (line = br.readLine()) != null; ) {
                line = line.trim();
                if (!line.isEmpty()) {
                    line = line.replaceAll("[^\\p{ASCII}]", "'");
                    String[] columns = line.split("\\t");
                    try {
                        if(columns.length>2) {
                            String name = columns[0];
                            List<Value> values = new ArrayList<>();
                            for(int i = 1; i <columns.length;i+=2) {
                                values.add(new Value(columns[i],Integer.parseInt(columns[i+1]),null));
                            }
                            if(!values.isEmpty()) {
                                Category category = new Category(name,values);
                                categoryService.createCategory(category);
                                success = true;
                            }
                        }
                    } catch (Exception e) {

                    }
                }
            }
        } catch (IOException e) {

        } finally {
            if(br!=null) {
                try {
                    br.close();
                } catch (IOException e) {

                }
            }
        }

        return success;
    }
}
