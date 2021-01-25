package br.com.hackaton.zup.bank.service;

import br.com.hackaton.zup.bank.model.Proposal;

import java.time.LocalDate;

public interface HandleProposal {

    public boolean handleValidProposal(Proposal proposal);
    public boolean handleDateBirth(LocalDate birth);
}
