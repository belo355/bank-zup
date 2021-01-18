package br.com.hackaton.zup.bank.model;

import javax.persistence.*;

@Table(name="Count")
@Entity
public class Account {

    public Account(){}

    public Account(String number, double saldo){
        this.number = number;
        this.saldo = saldo;
    }

    public Account(Account conta){
        this.number = conta.number;
        this.saldo = conta.saldo;
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
