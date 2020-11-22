package br.com.hackaton.zup.bank.controller.dto;

import br.com.hackaton.zup.bank.model.Address;
import br.com.hackaton.zup.bank.model.Image;
import br.com.hackaton.zup.bank.model.Proposal;

import java.time.LocalDate;

public class ProposalAccountInformationDto {

    private String name;
    private String lastName;
    private String email;
    private LocalDate dateBirth;
    private String cpf;

    private Address address;
    private Image image;


    public ProposalAccountInformationDto(Proposal proposal) {
        this.name = proposal.getName();
        this.lastName = proposal.getLastName();
        this.email = proposal.getEmail();
        this.dateBirth = proposal.getDateBirth();
        this.cpf = proposal.getCpf();
        this.address = proposal.getAddress();
        this.image = proposal.getImage();
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


    public LocalDate getDateBirth() {
        return dateBirth;
    }


    public String getCpf() {
        return cpf;
    }


    public Address getAdress() {
        return address;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }
}
