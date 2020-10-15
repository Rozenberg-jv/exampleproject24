package by.belhard.j24.exampleproject.service;

import by.belhard.j24.exampleproject.dto.CardDto;
import by.belhard.j24.exampleproject.repository.CardRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class CardServiceTest {

    private CardService service = new CardService();

    @BeforeClass
    public static void beforeClass() {

    }

    @Before
    public void setUp() throws SQLException {

        CardRepository cardRepository = new CardRepository();

        Connection connection = cardRepository.getConnection();
        Statement statement = connection.createStatement();
        statement.executeUpdate("insert into cards value (9999, 'VasilyTest', 10000, '123');");
    }

    @After
    public void after() throws SQLException {

        CardRepository cardRepository = new CardRepository();

        Connection connection = cardRepository.getConnection();
        Statement statement = connection.createStatement();
        statement.executeUpdate("delete from cards where id = 9999;");
    }

    @Test
    public void getById() throws SQLException {

        CardDto cardDto = service.getById("9999");

        assert cardDto.getName().equals("VasilyTest");
    }

}
