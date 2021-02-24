package sb.testmanager.controller.exceptions;

public class AlreadyExistException extends RuntimeException {

    private static final String TEST_SPECIFICATION_ALREADY_EXISTS = "Test specification already exists.";
    private static final String TEST_SPECIFICATION_NOT_FOUND = "Test specification not found.";

    public AlreadyExistException(String message) {
        super(message);
    }

    public static AlreadyExistException testSpecAlreadyExists() {
        return new AlreadyExistException(TEST_SPECIFICATION_ALREADY_EXISTS);
    }

    public static AlreadyExistException testSpecNotFound() {
        return new AlreadyExistException(TEST_SPECIFICATION_NOT_FOUND);
    }
}
