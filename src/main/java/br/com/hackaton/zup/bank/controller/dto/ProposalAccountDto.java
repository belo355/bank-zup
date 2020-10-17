package br.com.hackaton.zup.bank.controller.dto;

import br.com.hackaton.zup.bank.domain.Proposal;

import java.util.Date;

public class ProposalAccountDto {

    private String name;
    private String lastName;
    private String email;
    private Date dateBirth;
    private String cpf;

    public ProposalAccountDto(Proposal proposal) {
        this.name = proposal.getName();
        this.lastName = proposal.getLastName();
        this.email = proposal.getEmail();
        this.dateBirth = proposal.getDateBirth();
        this.cpf = proposal.getCpf();
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

}
