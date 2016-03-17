package com.rieke.bmore.feud.category;

import com.rieke.bmore.feud.database.SQLiteDataSource;
import com.rieke.bmore.feud.value.Value;
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
import java.util.ArrayList;
import java.util.List;

/**
 * Created by tcrieke on 3/15/15.
 */
@ActiveProfiles(profiles = "unit_test")
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations= "classpath:spring/application-context.xml")
@DirtiesContext(classMode=DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class CategoryDaoTest {

    @Autowired
    DataSource dataSource;

    @Autowired
    private CategoryDao categoryDao;

    @After
    public void cleanUp() {
        SQLiteDataSource.resetDatabase();
    }

    @Test
    public void testCreate() throws DataAccessException {
        List<Value> values = new ArrayList<Value>();
        values.add(new Value("val",100,null));
        Category categoryBefore = new Category("cat",values);
        categoryDao.create(categoryBefore);

        Category categoryAfter = categoryDao.getByName("cat");
        Assert.assertEquals(categoryBefore,categoryAfter);
    }

    @Test
    public void testUpdate() throws DataAccessException {
        List<Value> values = new ArrayList<Value>();
        values.add(new Value("val",200,null));
        values.add(new Value("val2",100,null));
        Category categoryBefore = new Category("cat",values);
        categoryDao.create(categoryBefore);

        Category categoryAfter = categoryDao.getByName("cat");
        Assert.assertEquals(categoryBefore,categoryAfter);

        categoryAfter.setName("dog");
        categoryDao.update(categoryAfter);
        Category categoryAfterUpdate = categoryDao.get(categoryAfter.getId());

        Assert.assertEquals(categoryAfter,categoryAfterUpdate);
    }

    @Test
    public void testNext() throws DataAccessException {
        List<Value> values = new ArrayList<Value>();
        values.add(new Value("val",200,null));
        values.add(new Value("val2",100,null));
        Category categoryBefore = new Category("cat",values);
        categoryDao.create(categoryBefore);

        Category categoryAfter = categoryDao.getByName("cat");
        Assert.assertEquals(categoryBefore,categoryAfter);

        categoryAfter.setName("dog");
        categoryDao.update(categoryAfter);
        Category categoryAfterUpdate = categoryDao.get(categoryAfter.getId());

        Assert.assertEquals(categoryAfter,categoryAfterUpdate);

        Category next = categoryDao.getNextCategory();
        Assert.assertEquals(categoryAfterUpdate,next);

    }

    @Test
    public void testDelete() throws DataAccessException {
        List<Value> values = new ArrayList<Value>();
        values.add(new Value("val",100,null));
        Category categoryBefore = new Category("cat",values);
        categoryDao.create(categoryBefore);

        Category categoryAfter = categoryDao.getByName("cat");
        Assert.assertEquals(categoryBefore,categoryAfter);

        categoryDao.delete(categoryAfter.getId());

        Category categoryAfterDelete = categoryDao.getByName("cat");
        Assert.assertNull(categoryAfterDelete);
    }
}
