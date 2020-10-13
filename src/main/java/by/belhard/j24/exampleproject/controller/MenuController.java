package by.belhard.j24.exampleproject.controller;

import by.belhard.j24.exampleproject.dto.CardDto;
import by.belhard.j24.exampleproject.exceptions.CashNotEnaughException;
import by.belhard.j24.exampleproject.exceptions.ItemNotFoundException;
import by.belhard.j24.exampleproject.exceptions.WrongInputException;
import by.belhard.j24.exampleproject.service.CardService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;

public class MenuController {

    private static final String MENU_LEGEND =
            "--------\n1. Add cash\n2. Get cash\n3. Transaction\n4. Balance\ne. exit\n--------";

    private CardService cardService = new CardService();

    public void start() {

        String input = "";

        while (!"e".equals(input)) {

            try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {

                System.out.println("Enter card(id):");
                input = reader.readLine();
                CardDto card = cardService.getById(input);
                if (card != null) {
                    System.out.println("Enter password:");
                    input = reader.readLine();
                    if (cardService.checkPassword(input, card)) {
                        startCardSession(card.getId());
                    } else {
                        System.err.println("Wrong password");
                    }
                } else {
                    System.err.println("No such card");
                }
            } catch (ItemNotFoundException e) {
                System.err.println("There is no such record in DB: " + e.getMessage());
            } catch (IOException | SQLException e) {
                e.printStackTrace();
            }

        }

    }

    private void startCardSession(int currentCardId) {

        String input = "";

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {

            while (!"e".equals(input)) {

                try {

                    System.out.println(MENU_LEGEND);
                    input = reader.readLine();

                    switch (input) {
                        case "1":
                            System.out.println("Enter amount to add:");
                            int amountToAdd = Integer.parseInt(reader.readLine());
                            cardService.addCash(currentCardId, amountToAdd);
                            break;
                        case "2":
                            System.out.println("Enter amount to add:");
                            int amountToGet = Integer.parseInt(reader.readLine());
                            cardService.getCash(currentCardId, amountToGet);
                            break;
                        case "3":
                            System.out.println("Enter receiver card id and amount to send:");
                            String[] transactionData = reader.readLine().split(" ");
                            cardService.doTransaction(
                                    currentCardId,
                                    transactionData[0],
                                    Integer.parseInt(transactionData[1])
                            );
                            break;
                        case "4":
                            System.out.println("Your balance: " +
                                    cardService.getById(currentCardId + "").getCash());
                            break;
                        case "e":
                            System.out.println("Good bye!");
                            break;
                        default:
                            throw new WrongInputException();
                    }
                } catch (CashNotEnaughException e) {
                    System.err.println("You have not enough money");
                } catch (NumberFormatException | WrongInputException e) {
                    System.err.println("Wrong input");
                } catch (SQLException e) {
                    e.printStackTrace();
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
