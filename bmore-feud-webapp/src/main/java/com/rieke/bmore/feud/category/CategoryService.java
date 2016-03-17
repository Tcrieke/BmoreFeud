package com.rieke.bmore.feud.category;

import org.slf4j.Logger;
import org.springframework.dao.DataAccessException;

/**
 * Created by tcrieke on 3/15/15.
 */
public class CategoryService {

    private Logger logger = org.slf4j.LoggerFactory.getLogger(CategoryService.class);

    private CategoryDao categoryDao;

    public CategoryService(CategoryDao categoryDao) {
        this.categoryDao = categoryDao;
    }

    public Category getNextCategory() {
        Category category = null;

        try {
            category = categoryDao.getNextCategory();
        } catch(DataAccessException e) {
            logger.error(e.getMessage(),e);
        }
        return category;
    }

    public void createCategory(Category category) {
        try {
            categoryDao.create(category);
        } catch(DataAccessException e) {
            logger.error(e.getMessage(),e);
        }
    }

    public Category getCategory(int id) {
        Category category = null;
        try {
            category = categoryDao.get(id);
        } catch (DataAccessException e) {
            logger.error(e.getMessage(),e);
        }
        return category;
    }
}
