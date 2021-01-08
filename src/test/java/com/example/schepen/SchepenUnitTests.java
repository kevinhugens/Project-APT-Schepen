package com.example.schepen;

import com.example.schepen.model.Schip;
import com.example.schepen.repository.SchipRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class SchepenUnitTests {



    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SchipRepository schipRepository;

    private ObjectMapper mapper = new ObjectMapper();

    @Test
    public void unitTestGetSchipByNaam() throws Exception {
        Schip schip1 = new Schip("USS Enterprise", 15, "Turnhout", "Geel","2");

        given(schipRepository.getByNaam("USS Enterprise")).willReturn(schip1);

        mockMvc.perform(get("/schepen/naam/{naam}", "USS Enterprise"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.naam", is("USS Enterprise")))
                .andExpect(jsonPath("$.capaciteit", is(15)))
                .andExpect(jsonPath("$.startLocatie", is("Turnhout")))
                .andExpect(jsonPath("$.eindLocatie", is("Geel")))
                .andExpect(jsonPath("$.rederijId", is("2")));
    }

    @Test
    public void unitTestGetSchepenByStartLocatie() throws Exception {
        List<Schip> schepen = new ArrayList<>();
        Schip schip1 = new Schip("USS Enterprise", 15, "Turnhout", "Geel","2");
        Schip schip2 = new Schip("Bismarck", 25, "Dessel", "Geel", "1");
        Schip schip3 = new Schip("yamato", 18, "Turnhout", "Rotterdam", "3");
        Schip schip4 = new Schip("USS Hornet", 16, "Geel", "Londen", "2");

        schepen.add(schip1);
        schepen.add(schip2);
        schepen.add(schip3);
        schepen.add(schip4);

        List<Schip> giveSchips = new ArrayList<>();

        giveSchips.add(schip1);
        giveSchips.add(schip3);

        given(schipRepository.getAllByStartLocatie("Turnhout")).willReturn(giveSchips);

        mockMvc.perform(get("/schepen/startlocatie/{locatie}", "Turnhout"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].name", is("USS Enterprise")))
                .andExpect(jsonPath("$[0].capaciteit", is(15)))
                .andExpect(jsonPath("$[0].startLocatie", is("Turnhout")))
                .andExpect(jsonPath("$[0].eindLocatie", is("Geel")))
                .andExpect(jsonPath("$[0].rederijId", is("2")))
                .andExpect(jsonPath("$[1].name", is("yamato")))
                .andExpect(jsonPath("$[1].capaciteit", is(18)))
                .andExpect(jsonPath("$[1].startLocatie", is("Turnhout")))
                .andExpect(jsonPath("$[1].eindLocatie", is("Rotterdam")))
                .andExpect(jsonPath("$[1].rederijId", is("3")));
    }

    @Test
    public void testGetSchepenByEindLocatie() throws Exception {
        List<Schip> schepen = new ArrayList<>();
        Schip schip1 = new Schip("USS Enterprise", 15, "Turnhout", "Geel","2");
        Schip schip2 = new Schip("Bismarck", 25, "Dessel", "Geel", "1");
        Schip schip3 = new Schip("yamato", 18, "Turnhout", "Rotterdam", "3");
        Schip schip4 = new Schip("USS Hornet", 16, "Geel", "Londen", "2");

        schepen.add(schip1);
        schepen.add(schip2);
        schepen.add(schip3);
        schepen.add(schip4);

        given(schipRepository.getAllByEindLocatie("Geel")).willReturn(schepen);

        mockMvc.perform(get("/schepen/eindlocatie/{locatie}", "Geel"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].name", is("USS Enterprise")))
                .andExpect(jsonPath("$[0].capaciteit", is(15)))
                .andExpect(jsonPath("$[0].startLocatie", is("Turnhout")))
                .andExpect(jsonPath("$[0].eindLocatie", is("Geel")))
                .andExpect(jsonPath("$[0].rederijId", is("2")))
                .andExpect(jsonPath("$[1].name", is("Bismarck")))
                .andExpect(jsonPath("$[1].capaciteit", is(25)))
                .andExpect(jsonPath("$[1].startLocatie", is("Dessel")))
                .andExpect(jsonPath("$[1].eindLocatie", is("Geel")))
                .andExpect(jsonPath("$[1].rederijId", is("1")));
    }

    @Test
    public void testPostSchip() throws Exception {
        Schip schipTest = new Schip("HMS Hood", 35, "Londen", "Kaapstad", "3");

        mockMvc.perform(post("/containers/insert")
                .content(mapper.writeValueAsString(schipTest))
                .contentType("application/json"))
                .andExpect(content().contentType("application/json"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.schipId", is(2)))
                .andExpect(jsonPath("$.gewicht", is(4300.00)))
                .andExpect(jsonPath("$.inhoud", is("Tennisballen")))
                .andExpect(jsonPath("$.startLocatie", is("Helsinki")))
                .andExpect(jsonPath("$.eindLocatie", is("Amsterdam")))
                .andExpect(jsonPath("$.serieCode", is("da57e")));
    }

    @Test
    public void unitTestDeleteSchip() throws Exception {
        Schip schip1 = new Schip("Schip 1",500,"Antwerpen","New York","1");

        given(schipRepository.findById(1)).willReturn(java.util.Optional.of(schip1));

        mockMvc.perform(delete("/schepen/delete/{id}", 1)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}
