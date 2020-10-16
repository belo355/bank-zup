package br.com.hackaton.zup.bank.controller.form;

import br.com.hackaton.zup.bank.domain.Adress;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class AdressProposalForm {

    //TODO @NumberFormat("000000-000")
    @NotNull
    @NotEmpty
    private int cep;

    @NotNull @NotEmpty
    private String street;

    @NotNull @NotEmpty
    private String region;

    @NotNull @NotEmpty
    private String complement;

    @NotNull @NotEmpty
    private String city;

    @NotNull @NotEmpty
    private String state;

    public AdressProposalForm(){}
    public AdressProposalForm(Adress adress) {
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
