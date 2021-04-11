package br.com.hackaton.zup.bank.config;

public class ErrorFormDto {

    private String field;
    private String message;

    public ErrorFormDto(String field, String message) {
        this.field = field;
        this.message = message;
    }

}
