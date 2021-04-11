package br.com.hackaton.zup.bank.model;

import javax.persistence.*;

@Table(name="Acount")
@Entity
public class Account {

    public Account(){}

    public Account(String agencia, String number, double saldo){
        this.agencia = agencia;
        this.number = number;
        this.saldo = saldo;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="agencia")
    private String agencia;

    @Column(name="number")
    private String number;

    @Column(name="saldo")
    private double saldo;


    public String getAgencia() {
        return agencia;
    }
    public String getNumber() {
        return number;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setAgencia(String agencia) {
        this.agencia = agencia;
    }
    public void setNumber(String number) {
        this.number = number;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    @Override
    public String toString() {
        return "Count{" +
                "number='" + number + '\'' +
                ", saldo=" + saldo +
                '}';
    }
}
