package br.com.hackaton.zup.bank.controller.form;

import br.com.hackaton.zup.bank.model.Proposal;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.*;
import java.util.Date;

public class AccountProposalForm {

    @NotNull @NotBlank @NotEmpty
    private String name;

    @NotNull @NotBlank @NotEmpty
    private String lastName;

    @NotNull @Email
    private String email;

    @NotNull @Past
    private Date dateBirth;

    @NotNull @CPF
    private String cpf;

    public AccountProposalForm(){}
    public AccountProposalForm(Proposal proposal) {
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
