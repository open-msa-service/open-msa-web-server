package msa.member.msamember.exception;

public class FileUploadException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public FileUploadException() {
    }

    public FileUploadException(String message) {
        super(message);
    }

}