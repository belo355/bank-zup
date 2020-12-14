package br.com.hackaton.zup.bank.controller;

import br.com.hackaton.zup.bank.model.Address;
import br.com.hackaton.zup.bank.repository.AddressRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class AddressControllerTest {

    @Autowired
    private AddressRepository repository;


    @Autowired
    private MockMvc mock;

    @Test
    public void getAdressDetailUnityTest(){
        Long id = 1L;
        Address address = repository.getOne(id);
        Assert.assertNotNull(address);
        Assert.assertEquals(id, address.getId());
    }

    @Test
    public void getListAddressExistsTest(){
        List<Address> address = repository.findAll();
        Assert.assertNotNull(address.get(0));
    }

    @Test
    public void get400ForRegistreAddress() throws Exception {

        URI uri = new URI("/abertura-conta/endereco");
        String XLocationHeaders = "http://localhost:8080/abertura-conta/1";
        String jsonRequest = "{\"cep\": \"04811-120\", \"city\":\"São Paulo\", \"complement\":\"NA\",\"region\":\"CENTRO\",\"state\":\"São Paulo\",\"street\":\"Av das Oliveira,221\"}";

        mock.perform(MockMvcRequestBuilders
                .post(uri)
                .content(jsonRequest)
                .contentType(MediaType.APPLICATION_JSON)
                .header("x-com-location", XLocationHeaders))
                .andExpect(MockMvcResultMatchers
                        .status()
                        .is(400));
    }

//TODO: Necessario criar proposal para parametro x-location
//    @Test
//    public void get201ForRegistreAddress() throws Exception {
//
//        URI uri = new URI("/abertura-conta/endereco");
//        String XLocationHeaders = "http://localhost:8080/abertura-conta/1";
//        String jsonRequest = "{\"cep\": \"04811-120\", \"city\":\"São Paulo\", \"complement\":\"NA\",\"region\":\"CENTRO\",\"state\":\"São Paulo\",\"street\":\"Av das Oliveira,221\"}";
//
//        mock.perform(MockMvcRequestBuilders
//                .post(uri)
//                .content(jsonRequest)
//                .contentType(MediaType.APPLICATION_JSON)
//                .header("x-com-location", XLocationHeaders))
//                .andExpect(MockMvcResultMatchers
//                        .status()
//                        .is(400));
//    }
}

