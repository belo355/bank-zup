package br.com.hackaton.zup.bank.config.files;

public class ResponseMessageHandler {
    private String message;

    public ResponseMessageHandler(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
