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

    private Adress adress;

    public ProposalAccountInformationDto(Proposal proposal) {
        this.name = proposal.getName();
        this.lastName = proposal.getLastName();
        this.email = proposal.getEmail();
        this.dateBirth = proposal.getDateBirth();
        this.cpf = proposal.getCpf();
        this.adress = proposal.getAdress();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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


    public Adress getAdress() {
        return adress;
    }

}
