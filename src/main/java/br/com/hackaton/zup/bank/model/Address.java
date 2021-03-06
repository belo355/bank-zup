package br.com.hackaton.zup.bank.model;

import br.com.hackaton.zup.bank.controller.form.AddressProposalForm;

import javax.persistence.*;

@Table(name="Address")
@Entity
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="cep")
    private String cep;

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

    public Address() {}
    public Address(String cep, String street, String region, String complement, String city, String state) {
        this.cep = cep;
        this.street = street;
        this.region = region;
        this.complement = complement;
        this.city = city;
        this.state = state;
    }

    public Address(AddressProposalForm form) {
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

    public String getCep() {
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

}
