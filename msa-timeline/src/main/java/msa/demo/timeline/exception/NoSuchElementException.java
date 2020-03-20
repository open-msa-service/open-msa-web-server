package msa.demo.timeline.exception;

public class NoSuchElementException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public NoSuchElementException(){}

    public NoSuchElementException(String message){
        super(message);
    }
}
