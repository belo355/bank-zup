package br.com.hackaton.zup.bank.controller.dto;

import br.com.hackaton.zup.bank.domain.Adress;

public class AdressAccountDto {

    private String cep;
    private String street;
    private String region;
    private String complement;
    private String city;
    private String state;

    public AdressAccountDto(Adress adress) {
        this.cep = adress.getCep();
        this.street = adress.getStreet();
        this.region = adress.getRegion();
        this.complement = adress.getComplement();
        this.city = adress.getCity();
        this.state = adress.getState();
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
