package by.belhard.j24.exampleproject.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
//@Builder
public class CardDto {

    private int id;
    private String name;
    private int cash;
    private String password;

    public static CardDtoBuilder builder() {
        return new CardDtoBuilder();
    }

    public final static class CardDtoBuilder {

        private final CardDto cardDto = new CardDto();

        public CardDtoBuilder id(int id) {
            cardDto.id = id;
            return this;
        }

        public CardDtoBuilder name(String name) {
            cardDto.name = name;
            return this;
        }

        public CardDtoBuilder cash(int cash) {
            cardDto.cash = cash;
            return this;
        }

        public CardDtoBuilder password(String password) {
            cardDto.password = password;
            return this;
        }

        public CardDto build() {
            return cardDto;
        }
    }
}
