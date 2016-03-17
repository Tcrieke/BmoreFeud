package com.rieke.bmore.feud.admin;

import com.rieke.bmore.feud.category.Category;
import com.rieke.bmore.feud.category.CategoryService;
import org.junit.AfterClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by tylerrieke on 3/17/16.
 */
@ActiveProfiles(profiles = "unit_test")
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations= "classpath:spring/application-context.xml")
@DirtiesContext(classMode=DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class AdminServiceTest {

    @Autowired
    private AdminService adminService;

    @Test
    public void testNewRound() {

        adminService.newRound();
    }
}
