package com.rieke.bmore.feud.category;

import com.rieke.bmore.feud.value.Value;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by tcrieke on 3/15/15.
 */
public class CategoryDao {

    protected JdbcTemplate jdbcTemplate;

    protected DataSource dataSource;

    public CategoryDao() {

    }

    public CategoryDao(DataSource source) {
        this.dataSource = source;
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Transactional
    public void create(Category category) throws DataAccessException {
        String SQL = "insert into category ("
                + "name,"
                + "last_used)"
                + " values (?,?)";

        jdbcTemplate.update(SQL,category.getName(),new Timestamp(System.currentTimeMillis()));
        Category temp = getByName(category.getName());
        addValuesToCategory(temp, category.getValues());
    }

    @Transactional
    public void update(Category category) throws DataAccessException {
        String SQL = "update category set "
                + "name = ?, "
                + "last_used = ? "
                + "where id = ?";
        jdbcTemplate.update(SQL,category.getName(),new Timestamp(System.currentTimeMillis()),category.getId());
        clearValuesForCategory(category);
        addValuesToCategory(category, category.getValues());
    }

    @Transactional
    public void addValuesToCategory(Category category, List<Value> values) throws DataAccessException{
        String valueSQL = "insert into value ("
                + "category_id,"
                + "value,"
                + "points) "
                + " values (?,?,?)";

        String acceptedSQL = "insert into accepted ("
                + "value_id,"
                + "value) "
                + " values (?,?)";

        List<Object[]> valueInsertParams = new ArrayList<Object[]>();
        List<Object[]> acceptedInsertParams = new ArrayList<Object[]>();

        for(Value value:values) {
            if(value != null) {
                valueInsertParams.add(new Object[] {
                        category.getId(),
                        value.getValue(),
                        value.getPoints()
                });

                for(String accepted:value.getAcceptedValues()) {
                    if(accepted!=null) {
                        acceptedInsertParams.add(new Object[]{
                            value.getId(),
                            accepted
                        });
                    }
                }

            }
        }

        if(!valueInsertParams.isEmpty()) {
            jdbcTemplate.batchUpdate(valueSQL,valueInsertParams);
            if(!acceptedInsertParams.isEmpty()) {
                jdbcTemplate.batchUpdate(acceptedSQL,acceptedInsertParams);
            }
        }
    }

    @Transactional
    private void clearValuesForCategory(Category category) throws DataAccessException {
        String SQL = "delete from value where category_id = ? ";

        this.jdbcTemplate.update(SQL, category.getId());

    }

    @Transactional(readOnly = true)
    public Category get(int id) throws DataAccessException {
        String SQL = getCategoryValueViewQuery("id = ?");

        return jdbcTemplate.query(SQL, new Object[]{id}, new CategoryMapper());
    }

    @Transactional(readOnly = true)
    public Category getByName(String name) throws DataAccessException {
        String SQL = getCategoryValueViewQuery("name = ?");

        return jdbcTemplate.query(SQL, new Object[]{name}, new CategoryMapper());
    }

    @Transactional
    public Category getNextCategory() {
        String idSQL = "select cvv.id from ("+getCategoryValueViewQuery(null)+") cvv order by cvv.last_used ASC limit 1";
        String SQL = getCategoryValueViewQuery("id = ?");

        int id = jdbcTemplate.queryForObject(idSQL, new Object[]{}, Integer.class);

        Category category = jdbcTemplate.query(SQL, new Object[]{id}, new CategoryMapper());

        //Update so it moves to the end of the list
        update(category);

        return category;
    }


    private String getCategoryValueViewQuery(String categoryWhereClause) {

        return  "  SELECT" +
                "    c.id AS id," +
                "    c.name AS name," +
                "    c.last_used AS last_used," +
                "    v.id AS value_id," +
                "    v.points AS value_points," +
                "    v.value AS value_value," +
                "    a.id AS value_accepted_id," +
                "    a.value AS value_accepted" +
                "  FROM "+(categoryWhereClause!=null && !categoryWhereClause.isEmpty()
                        ?"(Select id,name,last_used from category where "+categoryWhereClause+")"
                        :"category")+" c" +
                "    LEFT JOIN value v ON c.id = v.category_id" +
                "    LEFT JOIN accepted a ON v.id = a.value_id" +
                "  ORDER BY c.id, v.points DESC, a.id DESC";
    }

    @Transactional
    public void delete(int id) {
        String SQL = "delete from category where id = ?";
        this.jdbcTemplate.update(SQL,id);
    }
}
