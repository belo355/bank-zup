package br.com.hackaton.zup.bank.controller;

import br.com.hackaton.zup.bank.model.Proposal;
import br.com.hackaton.zup.bank.repository.ProposalRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ProposalControllerTest {

    @Autowired
    private ProposalRepository repository;

    @Autowired
    private MockMvc mock;

    @Test
    public void registerNewProposal() throws Exception {

        URI uri = new URI("/abertura-conta");
        String jsonRequest = "{\"cpf\": \"42036080820\", \"dateBirth\":\"2000-02-20\", \"email\":\"email@contato.com\",\"lastName\":\"Silva\",\"name\":\"Maria\"}";

        mock.perform(MockMvcRequestBuilders
                .post(uri)
                .content(jsonRequest)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers
                        .status()
                        .is(201));
    }

    @Test
    public void getAllListProposal() throws Exception {

        URI uri = new URI("/abertura-conta");
        String jsonRequest = "{\"cpf\": \"42036080820\", \"dateBirth\":\"2000-02-20\", \"email\":\"email@contato.com\",\"lastName\":\"Silva\",\"name\":\"Maria\"}";

        mock.perform(MockMvcRequestBuilders
                .post(uri)
                .content(jsonRequest)
                .contentType(MediaType.APPLICATION_JSON));

        List<Proposal> proposals = repository.findAll();
        Assert.assertNotNull(proposals.get(0));
    }

    @Test
    public void notRegisterNewProposalAndReturn400() throws Exception {
        URI uri = new URI("/abertura-conta");
        String jsonRequest = "{\"cpf\": \"00\", \"dateBirth\":\"2000-02-20\", \"email\":\"email@contato.com\",\"lastName\":\"Silva\",\"name\":\"Maria\"}";


        mock.perform(MockMvcRequestBuilders
                .post(uri)
                .content(jsonRequest)
                .contentType(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers
                        .status()
                        .is(400));
    }
}
