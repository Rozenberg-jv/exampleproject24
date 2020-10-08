package by.belhard.j24.exampleproject;

import by.belhard.j24.exampleproject.model.Account;

import java.sql.*;

public class CardDao {

    private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/bh24?serverTimezone=UTC";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "root";

    private Connection connection;

    public Connection getConnection() throws SQLException {

        if (connection == null) {
            Connection connection = DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD);
            this.connection = connection;
        }

        return connection;
    }


    public Account checkIfExistsById(int id) throws SQLException {

        PreparedStatement preparedStatement = getConnection().prepareStatement("select name from accounts where id = ?;");
        preparedStatement.setInt(1, id);

        ResultSet resultSet = preparedStatement.executeQuery();
        // get account

        return resultSet.next();
    }
}
