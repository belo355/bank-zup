package br.com.hackaton.zup.bank.controller.dto;

import br.com.hackaton.zup.bank.model.Address;

public class AdressAccountDto {

    private String cep;
    private String street;
    private String region;
    private String complement;
    private String city;
    private String state;

    public AdressAccountDto(Address address) {
        this.cep = address.getCep();
        this.street = address.getStreet();
        this.region = address.getRegion();
        this.complement = address.getComplement();
        this.city = address.getCity();
        this.state = address.getState();
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
