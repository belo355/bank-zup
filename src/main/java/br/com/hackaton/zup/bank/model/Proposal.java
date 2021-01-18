package br.com.hackaton.zup.bank.model;

import br.com.hackaton.zup.bank.controller.form.AccountProposalForm;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
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
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private LocalDate dateBirth;

    @Column(name="cpf")
    private String cpf;

    @OneToOne
    private Address address;

    @OneToOne
    private Image image;

    public Proposal() {}

    public Proposal(String name, String lastName, String email ) {
        this.name = name;
        this.lastName = lastName;
        this.email = email;
    }
    public Proposal(String name, String lastName, String email, LocalDate dateBirth, String cpf) {
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

    public LocalDate getDateBirth() {
        return dateBirth;
    }

    public String getCpf() {
        return cpf;
    }

    public Address getAddress() {
        return address;
    }

    public Image getImage() {
        return image;
    }

    public void setAddress(Address address) {
        this.setAddress(address);
    }
    public void setImage(Image image) {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Proposal proposal = (Proposal) o;
        return Objects.equals(id, proposal.id) &&
                Objects.equals(name, proposal.name) &&
                Objects.equals(lastName, proposal.lastName) &&
                Objects.equals(email, proposal.email) &&
                Objects.equals(dateBirth, proposal.dateBirth) &&
                Objects.equals(cpf, proposal.cpf) &&
                Objects.equals(address, proposal.address) &&
                Objects.equals(image, proposal.image);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, lastName, email, dateBirth, cpf, address, image);
    }


}
