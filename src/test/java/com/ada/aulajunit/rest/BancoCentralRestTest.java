package com.ada.aulajunit.rest;

import com.ada.aulajunit.modelo.DollarQuotation;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@SpringBootTest
@AutoConfigureMockMvc
class BancoCentralRestTest {

    @Autowired
    private MockMvc mockMvc;

    //Teste fazendo GET para a API
    @Test
    void getDollarQuotation() throws Exception {
        String startDate = "04-11-2023";
        String path = "/banco/cotacao";

        mockMvc.perform(MockMvcRequestBuilders.get(path)
                        .contentType(MediaType.APPLICATION_JSON)
                        .param("startDate", startDate)
                ).andDo(print())
                .andExpect(content().contentType("application/json"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.resultado").value("DollarQuotation(id=null, date=Tue Apr 11 00:00:00 BRT 2023, purchaseQuotation=5.0122, saleQuotation=5.0128)"));

    }

    // Teste para verificar a criação da cotação no banco de dados
    @Test
    void postDollarQuotation() throws Exception{
        String path = "/banco/register";
        mockMvc.perform(MockMvcRequestBuilders.post(path)
                        .content("{\"date\": \"2023-04-25\", \"purchaseQuotation\": 4.9202, \"saleQuotation\": 4.9208}")
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }

    //Teste para verificar a cotação no dia
    @Test
    void getQuotationFromDb() throws Exception{
        String path = "/banco/getQuotationFromDb";
        String date = "2022-03-22";
        mockMvc.perform(MockMvcRequestBuilders.get(path)
                        .param("date",date)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }
}