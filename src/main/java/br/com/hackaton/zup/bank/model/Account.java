package br.com.hackaton.zup.bank.model;

import br.com.hackaton.zup.bank.controller.dto.AccountDTO;

import javax.persistence.*;

@Table(name="Acount")
@Entity
public class Account {

    public Account(){}

    public Account(String number, double saldo){
        this.number = number;
        this.saldo = saldo;
    }

    public Account(AccountDTO conta){
        this.number = conta.getNumber();
        this.saldo = conta.getValor();
    }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="number")
    private String number;

    @Column(name="saldo")
    private double saldo;

    public String getNumber() {
        return number;
    }

    public double getSaldo() {
        return saldo;
    }

    @Override
    public String toString() {
        return "Count{" +
                "number='" + number + '\'' +
                ", saldo=" + saldo +
                '}';
    }
}
