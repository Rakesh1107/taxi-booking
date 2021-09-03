package taxibooking;

public class TaxiException extends Throwable {
    String message;

    TaxiException (String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
