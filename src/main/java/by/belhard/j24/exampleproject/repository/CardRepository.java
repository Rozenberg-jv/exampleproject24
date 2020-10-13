package by.belhard.j24.exampleproject.repository;

import by.belhard.j24.exampleproject.exceptions.ItemNotFoundException;
import by.belhard.j24.exampleproject.model.Card;

import java.sql.*;

public class CardRepository {

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


    public Card getById(int cardNumber) throws SQLException {

        PreparedStatement preparedStatement =
                getConnection().prepareStatement("select * from cards where id = ?;");
        preparedStatement.setInt(1, cardNumber);

        ResultSet resultSet = preparedStatement.executeQuery();

        if (!resultSet.next())
            throw new ItemNotFoundException("Card");

        int id = resultSet.getInt("id");
        String name = resultSet.getString("name");
        String password = resultSet.getString("password");
        int cash = resultSet.getInt("cash");

        return new Card(id, name, cash, password);
    }

    public void updateCashAmount(int newAmount, int id) throws SQLException {

        PreparedStatement preparedStatement =
                getConnection().prepareStatement("update cards set cash = ? where id = ?;");
        preparedStatement.setInt(1, newAmount);
        preparedStatement.setInt(2, id);

        preparedStatement.executeUpdate();
    }
}
