package com.rieke.bmore.feud.importer;

import com.rieke.bmore.feud.category.Category;
import com.rieke.bmore.feud.category.CategoryDao;
import com.rieke.bmore.feud.category.CategoryService;
import com.rieke.bmore.feud.database.SQLiteDataSource;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.sql.DataSource;
import java.io.File;

/**
 * Created by tcrieke on 3/20/15.
 */
@ActiveProfiles(profiles = "unit_test")
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations= "classpath:spring/application-context.xml")
@DirtiesContext(classMode=DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class ImportServiceTest {

    @Autowired
    private CategoryService categoryService;
    @Autowired
    private CategoryDao categoryDao;


    @Test
    public void testParse() throws DataAccessException{
        Category category = categoryService.getNextCategory();
        Category category2 = categoryService.getNextCategory();
        Category category3 = categoryDao.get(20);
        Category category1 = categoryDao.get(1258);

        Assert.assertNotEquals(category.getName(),category2.getName());
    }
}
