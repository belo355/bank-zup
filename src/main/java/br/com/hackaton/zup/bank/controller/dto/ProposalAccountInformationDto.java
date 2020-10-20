package br.com.hackaton.zup.bank.controller.dto;

import br.com.hackaton.zup.bank.model.Adress;
import br.com.hackaton.zup.bank.model.Proposal;

import java.util.Date;

public class ProposalAccountInformationDto {

    private String name;
    private String lastName;
    private String email;
    private Date dateBirth;
    private String cpf;

    private String cep;
    private String street;
    private String region;
    private String complement;
    private String city;
    private String state;

    public ProposalAccountInformationDto(Proposal proposal, Adress adress) {
        this.name = proposal.getName();
        this.lastName = proposal.getLastName();
        this.email = proposal.getEmail();
        this.dateBirth = proposal.getDateBirth();
        this.cpf = proposal.getCpf();
        this.cep = adress.getCep();
        this.street = adress.getStreet();
        this.region = adress.getRegion();
        this.complement = adress.getRegion();
        this.city = adress.getCity();
        this.state = adress.getState();
    }

    public String getName() {
        return name;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public Date getDateBirth() {
        return dateBirth;
    }

    public String getCpf() {
        return cpf;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getComplement() {
        return complement;
    }

    public void setComplement(String complement) {
        this.complement = complement;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
