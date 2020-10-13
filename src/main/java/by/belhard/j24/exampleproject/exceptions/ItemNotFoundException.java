package by.belhard.j24.exampleproject.exceptions;

public class ItemNotFoundException extends RuntimeException {

    public ItemNotFoundException(String fieldName) {
        super(fieldName);
    }
}
