package by.belhard.j24.exampleproject.service;

import by.belhard.j24.exampleproject.dto.CardDto;
import by.belhard.j24.exampleproject.exceptions.CashNotEnaughException;
import by.belhard.j24.exampleproject.exceptions.ItemNotFoundException;
import by.belhard.j24.exampleproject.exceptions.WrongInputException;
import by.belhard.j24.exampleproject.model.Card;
import by.belhard.j24.exampleproject.repository.CardRepository;
import org.apache.commons.codec.digest.DigestUtils;

import java.sql.SQLException;

public class CardService {

    private CardRepository cardRepository = new CardRepository();


    public CardDto getById(String input) throws SQLException, ItemNotFoundException {

        Card card = cardRepository.getById(Integer.parseInt(input));

        return CardDto.builder()
                .id(card.getId())
                .name(card.getName())
                .cash(card.getCash())
                .password(card.getPassword())
                .build();
    }

    public void addCash(int id, int amount) throws SQLException {

        checkMoreThanZero(amount);

        Card card = cardRepository.getById(id);

        cardRepository.updateCashAmount(card.getCash() + amount, card.getId());
    }

    public void getCash(int id, int amount) throws SQLException {

        checkMoreThanZero(amount);

        Card card = cardRepository.getById(id);

        checkHasEnaughMoney(amount, card.getCash());

        cardRepository.updateCashAmount(card.getCash() - amount, card.getId());
    }

    public boolean checkPassword(String rawPassword, CardDto card) {

        return DigestUtils.sha256Hex(rawPassword).equals(card.getPassword());
    }

    public void doTransaction(int currentCardId, String receiverCardId, int amount)
            throws SQLException, ItemNotFoundException {

        checkMoreThanZero(amount);

        Card card = cardRepository.getById(currentCardId);

        checkHasEnaughMoney(amount, card.getCash());

        getById(receiverCardId);

        getCash(currentCardId, amount);

        addCash(Integer.parseInt(receiverCardId), amount);
    }

    // privates
    private void checkMoreThanZero(int amount) {
        if (amount <= 0)
            throw new WrongInputException();
    }

    private void checkHasEnaughMoney(int amount, int current) {
        if (amount > current)
            throw new CashNotEnaughException();
    }
}
