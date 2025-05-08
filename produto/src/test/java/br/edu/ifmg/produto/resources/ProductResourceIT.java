package br.edu.ifmg.produto.resources;

import br.edu.ifmg.produto.util.Factory;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.transaction.annotation.Transactional;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class ProductResourceIT {
    
    @Autowired //objeto que irá fazer as requisições HTTP
    private MockMvc mockMvc;
    
    @Autowired
    private ObjectMapper objectMapper; //objeto responsável por fazer conversões entre JSON e objetos Java
    
    private long existingId;
    private long nonExistingId;

    @BeforeEach
    void setUp() {
        existingId = 1L;
        nonExistingId = 2000L;
    }

    @Test
    public void findalAllShouldReturnSortedPageWhenSortByName() throws Exception{
        ResultActions resultActions =
        mockMvc.perform(get("/product?page=0&size=10&sort,asc")
                .accept(MediaType.APPLICATION_JSON));
        resultActions.andExpect(status().isOk());
        resultActions.andExpect(jsonPath(
                "$.content[0].name").value("Mackbook Pro"));
    }
}
