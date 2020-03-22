package msa.timeline.msatimeline.exception;

public class AmqpRejectAndDontRequeueException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public AmqpRejectAndDontRequeueException() {
    }

    public AmqpRejectAndDontRequeueException(String message) {
        super(message);
    }
}
