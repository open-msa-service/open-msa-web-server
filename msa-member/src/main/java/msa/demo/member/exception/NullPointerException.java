package msa.demo.member.exception;

public class NullPointerException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public NullPointerException() {
    }

    public NullPointerException(String message) {
        super(message);
    }
}
