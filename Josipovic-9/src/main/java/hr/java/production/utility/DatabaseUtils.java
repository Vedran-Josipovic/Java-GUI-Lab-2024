package hr.java.production.utility;

import hr.java.production.model.Category;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.*;

public class DatabaseUtils {
    private static final Logger logger = LoggerFactory.getLogger(DatabaseUtils.class);
    private static final String DATABASE_FILE = "conf/database.properties";

    private static Connection connectToDatabase() throws SQLException, IOException {
        Properties properties = new Properties();
        properties.load(new FileReader(DATABASE_FILE));
        String databaseUrl = properties.getProperty("databaseUrl");
        String username = properties.getProperty("username");
        String password = properties.getProperty("password");
        return DriverManager.getConnection(databaseUrl, username, password);
    }

    public static List<Category> getCategories() {
        List<Category> categories = new ArrayList<>();

        try (Connection connection = connectToDatabase()) {
            String sqlQuery = "SELECT * FROM CATEGORY";
            Statement statement = connection.createStatement();
            statement.execute(sqlQuery);
            ResultSet resultSet = statement.getResultSet();
            mapResultSetToCategoryList(resultSet, categories);
        } catch (SQLException | IOException ex) {
            String message = "An error occurred while connecting to database!";
            logger.error(message, ex);
            System.out.println(message);
        }

        return categories;
    }

    private static void mapResultSetToCategoryList(ResultSet resultSet, List<Category> categories) throws SQLException {
        while (resultSet.next()) {
            Long id = resultSet.getLong("ID");
            String name = resultSet.getString("NAME");
            String description = resultSet.getString("DESCRIPTION");

            Category category = new Category(id, name, description);

            categories.add(category);
        }
    }

    public static List<Category> getCategoriesByFilters(Category categoryFilter) {
        List<Category> categories = new ArrayList<>();
        Map<Integer, Object> queryParams = new HashMap<>();
        int paramOrdinalNumber = 1;

        try (Connection connection = connectToDatabase()) {
            StringBuilder baseSqlQuery = new StringBuilder("SELECT * FROM CATEGORY WHERE 1=1");

            if (Optional.ofNullable(categoryFilter.getId()).isPresent()) {
                baseSqlQuery.append(" AND ID = ?");
                queryParams.put(paramOrdinalNumber++, categoryFilter.getId());
            }
            if (!categoryFilter.getName().isEmpty()) {
                baseSqlQuery.append(" AND LOWER(NAME) LIKE ?");
                queryParams.put(paramOrdinalNumber++, "%" + categoryFilter.getName().toLowerCase() + "%");
            }
            if (!categoryFilter.getDescription().isEmpty()) {
                baseSqlQuery.append(" AND LOWER(DESCRIPTION) LIKE ?");
                queryParams.put(paramOrdinalNumber++, "%" + categoryFilter.getDescription().toLowerCase() + "%");
            }

            PreparedStatement preparedStatement = connection.prepareStatement(baseSqlQuery.toString());

            for (Integer paramNumber : queryParams.keySet()) {
                if(queryParams.get(paramNumber) instanceof String stringQueryParam){
                    preparedStatement.setString(paramNumber, stringQueryParam);
                }
                else if(queryParams.get(paramNumber) instanceof Long longQueryParam){
                    preparedStatement.setLong(paramNumber, longQueryParam);
                }
            }

            ResultSet resultSet = preparedStatement.executeQuery();
            mapResultSetToCategoryList(resultSet, categories);
        } catch (SQLException | IOException ex) {
            String message = "An error occurred while connecting to the database!";
            logger.error(message, ex);
            System.out.println(message);
        }

        return categories;
    }



}
