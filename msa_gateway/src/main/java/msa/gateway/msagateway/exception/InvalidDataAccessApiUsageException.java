package msa.gateway.msagateway.exception;

public class InvalidDataAccessApiUsageException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public InvalidDataAccessApiUsageException() {
    }

    public InvalidDataAccessApiUsageException(String message) {
        super(message);
    }
}
