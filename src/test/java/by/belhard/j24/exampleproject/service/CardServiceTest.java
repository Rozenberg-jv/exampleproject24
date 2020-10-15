package by.belhard.j24.exampleproject.service;

import by.belhard.j24.exampleproject.dto.CardDto;
import by.belhard.j24.exampleproject.repository.CardRepository;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class CardServiceTest {

	private CardService service;

	@Before
	public void setUp() throws SQLException {

		service = new CardService();

		CardRepository cardRepository = new CardRepository();

		Connection connection = cardRepository.getConnection();
		Statement statement = connection.createStatement();
		statement.executeQuery("insert into cards value (9999, 'VasilyTest', )");
	}

	@Test
	public void getById() throws SQLException {

		CardDto cardDto = service.getById("1");

		assert cardDto.getId() == 9999;
	}

}
