package hr.java.production.utility;
import hr.java.production.model.Category;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.*;
import java.time.LocalDate;
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
        Connection connection = DriverManager.getConnection(databaseUrl,
                username,password);
        return connection;
    }

    public static List<Category> getCategories() {
        List<Category> categories = new ArrayList<>();

        try (Connection connection = connectToDatabase()) {
            String sqlQuery = "SELECT * FROM CATEGORY";
            Statement statement = connection.createStatement();
            statement.execute(sqlQuery);
            ResultSet resultSet = statement.getResultSet();
            mapResultSetToCategoryList(resultSet, cars);
        } catch (SQLException | IOException ex) {
            String message = "An error occurred while connecting to database!";
            logger.error(message, ex);
            System.out.println(message);
        }

        return categories;
    }

    private static void mapResultSetToCategoryList(ResultSet resultSet, List<Category> categories) throws SQLException {
        while (resultSet.next()) {

        }
    }



}
