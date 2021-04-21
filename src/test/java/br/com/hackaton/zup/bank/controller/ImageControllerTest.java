package br.com.hackaton.zup.bank.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.net.URI;

class ImageControllerTest {

    @Autowired
    private MockMvc mock;

    @Test
    void upload200ImageUser() throws Exception {
        MockMultipartFile image = new MockMultipartFile("data", "filename.txt", "text/plain", "some xml".getBytes());

        URI uri = new URI("/proposal/upload/image");
        String XLocationHeaders = "http://localhost:8080/proposal/1";

        mock.perform(MockMvcRequestBuilders
                .multipart(uri)
                .file(image)
                .header("x-com-location", XLocationHeaders)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is(200));
    }

}