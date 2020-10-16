package br.com.hackaton.zup.bank.domain;

import br.com.hackaton.zup.bank.controller.form.AdressProposalForm;

import javax.persistence.*;
import javax.validation.Valid;
import java.util.Date;
import java.util.Objects;

@Entity
public class Adress {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="cep")
    private int cep;

    @Column(name="rua")
    private String street;

    @Column(name="bairro")
    private String region;

    @Column(name="complemento")
    private String complement;

    @Column(name="cidade")
    private String city;

    @Column(name="estado")
    private String state;

    public Adress() {}
    public Adress(int cep, String street, String region, String complement, String city, String state) {
        this.cep = cep;
        this.street = street;
        this.region = region;
        this.complement = complement;
        this.city = city;
        this.state = state;
    }

    public Adress(AdressProposalForm form) {
        this.cep = form.getCep();
        this.street = form.getStreet();
        this.region = form.getRegion();
        this.complement = form.getComplement();
        this.city = form.getCity();
        this.state = form.getState();
    }

    public Long getId() {
        return id;
    }

    public int getCep() {
        return cep;
    }

    public String getStreet() {
        return street;
    }

    public String getRegion() {
        return region;
    }

    public String getComplement() {
        return complement;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Adress adress = (Adress) o;
        return cep == adress.cep &&
                Objects.equals(id, adress.id) &&
                Objects.equals(street, adress.street) &&
                Objects.equals(region, adress.region) &&
                Objects.equals(complement, adress.complement) &&
                Objects.equals(city, adress.city) &&
                Objects.equals(state, adress.state);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, cep, street, region, complement, city, state);
    }
}
