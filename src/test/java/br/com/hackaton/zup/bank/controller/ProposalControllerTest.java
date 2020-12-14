package br.com.hackaton.zup.bank.controller;

import br.com.hackaton.zup.bank.model.Proposal;
import br.com.hackaton.zup.bank.repository.ProposalRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ProposalControllerTest {

    @Autowired
    private ProposalRepository repository;

    @Test
    public void getListProposalsNotExistsTest(){
        List<Proposal> proposals = repository.findAll();
        Assert.assertNotNull(proposals);
    }
}
