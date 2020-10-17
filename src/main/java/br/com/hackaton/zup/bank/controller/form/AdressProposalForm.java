package br.com.hackaton.zup.bank.controller.form;

import br.com.hackaton.zup.bank.model.Adress;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class AdressProposalForm {

    @NotNull @NotEmpty @Pattern(regexp = "\\d{5}-\\d{3}", message = "cep invalido")
    private String cep;

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
