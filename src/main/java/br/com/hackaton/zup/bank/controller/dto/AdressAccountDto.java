package br.com.hackaton.zup.bank.controller.dto;

import br.com.hackaton.zup.bank.domain.Adress;

public class AdressAccountDto {

    private int cep;
    private String street;
    private String region;
    private String complement;
    private String city;
    private String state;

    public AdressAccountDto(){}
    public AdressAccountDto(Adress adress) {
        this.cep = getCep();
        this.street = getStreet();
        this.region = getRegion();
        this.complement = getComplement();
        this.city = getCity();
        this.state = getState();
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
}
