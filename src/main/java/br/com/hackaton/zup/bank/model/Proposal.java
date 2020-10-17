package br.com.hackaton.zup.bank.model;

import br.com.hackaton.zup.bank.controller.form.AccountProposalForm;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Table(name="Proposal")
@Entity
public class Proposal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="name")
    private String name;

    @Column(name = "lastname")
    private String lastName;

    @Column(name = "email")
    private String email;

    @Column(name = "datebirth")
    private Date dateBirth;

    @Column(name="cpf")
    private String cpf;


    public Proposal() {}
    public Proposal(String name, String lastName, String email, Date dateBirth, String cpf) {
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.dateBirth = dateBirth;
        this.cpf = cpf;
    }

    public Proposal(AccountProposalForm form) {
        this.name = form.getName();
        this.lastName = form.getLastName();
        this.email = form.getEmail();
        this.dateBirth = form.getDateBirth();
        this.cpf = form.getCpf();
    }


    public Long getId() {
        return id;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Proposal prospect = (Proposal) o;
        return Objects.equals(id, prospect.id) &&
                Objects.equals(name, prospect.name) &&
                Objects.equals(lastName, prospect.lastName) &&
                Objects.equals(email, prospect.email) &&
                Objects.equals(dateBirth, prospect.dateBirth) &&
                Objects.equals(cpf, prospect.cpf);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, lastName, email, dateBirth, cpf);
    }
}