package by.belhard.j24.exampleproject.model;

import lombok.*;
import org.apache.commons.codec.digest.DigestUtils;

@Getter
@Setter
@ToString(exclude = "password")
@EqualsAndHashCode(of = "id")
public class Account {

    private int id;
    private String name;
    private long cash;
    @Setter(AccessLevel.NONE)
    private String password;

    public Account(int id, String name, long cash, String password) {
        this.id = id;
        this.name = name;
        this.cash = cash;
        setPassword(password);
    }

    public void setPassword(String password) {
        this.password = DigestUtils.sha256Hex(password);
    }

    public boolean checkPassword(String rawPassword) {
        return DigestUtils.sha256Hex(rawPassword).equals(password);
    }
}
