package com.rieke.bmore.feud.category;

import com.rieke.bmore.feud.value.Value;
import org.springframework.jdbc.core.ResultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;


/**
 * Created by tcrieke on 3/15/15.
 */
public class CategoryMapper implements ResultSetExtractor<Category> {

    @Override
    public Category extractData(ResultSet rs) throws SQLException {
        Category category = null;
        Map<Integer,Value> valueMap = new HashMap<Integer, Value>();

        while(rs.next()) {
            if(category==null) {
                category = new Category(rs.getInt("id"), rs.getString("name"), null);
            }
            int valueId = rs.getInt("value_id");
            if(!valueMap.containsKey(valueId)) {
                valueMap.put(valueId,
                        new Value(valueId, rs.getString("value_value"),rs.getInt("value_points"),null));
                category.getValues().add(valueMap.get(valueId));
            }

            int acceptedId = rs.getInt("value_accepted_id");
            if(acceptedId>0) {
                Value value = valueMap.get(valueId);
                value.getAcceptedValues().add(rs.getString(rs.getString("value_accepted")));
            }
        }
        return category;
    }

}