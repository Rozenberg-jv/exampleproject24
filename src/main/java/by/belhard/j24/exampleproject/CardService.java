package by.belhard.j24.exampleproject;

import by.belhard.j24.exampleproject.model.Account;

import java.sql.SQLException;

public class CardService {

    private CardDao cardDao = new CardDao();


    public Account checkIfExistsById(String input) throws SQLException {

        return cardDao.checkIfExistsById(Integer.parseInt(input));
    }
}
