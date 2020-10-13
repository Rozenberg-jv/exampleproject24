package by.belhard.j24.exampleproject.model;

import lombok.*;
import org.apache.commons.codec.digest.DigestUtils;

@Getter
@Setter
@ToString(exclude = "password")
@EqualsAndHashCode(of = "id")
public class Card {

    private int id;
    private String name;
    private int cash;
    @Setter(AccessLevel.NONE)
    private String password;

    public Card(int id, String name, int cash, String password) {
        this.id = id;
        this.name = name;
        this.cash = cash;
        this.password = password;
//        setPassword(password);
    }

    public void setPassword(String password) {
        this.password = DigestUtils.sha256Hex(password);
    }

}
