package br.com.hackaton.zup.bank.controller.dto;

import br.com.hackaton.zup.bank.model.Proposal;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class ProposalAccountDto {

    private String name;
    private String lastName;
    private String email;
    private LocalDate dateBirth;
    private String cpf;


    public ProposalAccountDto(List<Proposal> proposals){
       proposals.forEach(p -> {
           this.name = p.getName();
           this.lastName = p.getLastName();
           this.email = p.getEmail();
           this.dateBirth = p.getDateBirth();
           this.cpf = p.getCpf();
       });
    };

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

    public LocalDate getDateBirth() {
        return dateBirth;
    }

    public String getCpf() {
        return cpf;
    }

}
