package msa.gateway.msagateway.exception;

public class IOException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public IOException() {
    }

    public IOException(String message) {
        super(message);
    }
}
