package br.com.hackaton.zup.bank.controller.dto;

import br.com.hackaton.zup.bank.model.Account;

public class AccountDTO {

    private String number;
    private double valor;

    public AccountDTO(Account account) {
        this.number = number;
        this.valor = valor;
    }

    public String getNumber() {
        return number;
    }

    public double getValor() {
        return valor;
    }
}
