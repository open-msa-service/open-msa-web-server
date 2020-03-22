package msa.member.msamember.exception;

public class NullPointerException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public NullPointerException() {
    }

    public NullPointerException(String message) {
        super(message);
    }
}
