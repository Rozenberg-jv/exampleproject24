package by.belhard.j24.exampleproject;

import by.belhard.j24.exampleproject.model.Account;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;

public class Menu {

    private static final String MENU_LEGEND =
            "1. Add cash\n2. Get cash\n3. Transaction\n4. Balance\ne. exit";

    private CardService cardService = new CardService();

    void start() {

        String input = "";

        while (!"e".equals(input)) {

            try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {

                System.out.println("Enter card(id):");
                input = reader.readLine();
                Account account = cardService.checkIfExistsById(input);
                if (account != null) {
                    System.out.println("Enter password:");
                    // input password
                    // password check account.checkPassword(input);
                } else {
                    System.err.println("No such account");
                    continue;
                }


            } catch (IOException | SQLException e) {
                e.printStackTrace();
            }

        }

    }
}
