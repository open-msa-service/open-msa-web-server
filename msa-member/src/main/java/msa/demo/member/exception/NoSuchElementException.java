package msa.demo.member.exception;

public class NoSuchElementException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public NoSuchElementException(){}

    public NoSuchElementException(String message){
        super(message);
    }
}
