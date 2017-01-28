package me.tatocaster.buildnumberoverlaylibrary.exceptions;

/**
 * Created by tatocaster on 1/28/17.
 */

public class NumberOverlayException extends RuntimeException {
    /**
     * Constructor
     */
    public NumberOverlayException() {
        super();
    }

    /**
     * Constructor
     *
     * @param message
     */
    public NumberOverlayException(String message) {
        super(message);
    }

    /**
     * Constructor
     *
     * @param message
     * @param throwable cause of exception
     */
    public NumberOverlayException(String message, Throwable throwable) {
        super(message, throwable);
    }

    @Override
    public String toString() {
        return getMessage();
    }
}
