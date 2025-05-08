package br.edu.ifmg.produto.resources;

import br.edu.ifmg.produto.dtos.ProductDTO;
import br.edu.ifmg.produto.services.ProductService;
import br.edu.ifmg.produto.util.Factory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.data.domain.PageImpl;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.springframework.http.MediaType;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import org.junit.jupiter.api.DisplayName;


/*
Teste automatizado permite a integração contínua. Podendo ser executado várias vezes a cada novo commit ou merge.
 */
@WebMvcTest(ProductResource.class) //mais leve que o @SpringBootTest pois não carrega o contexto inteiro da aplicação
class ProductResourceTest {
    @Autowired
    private MockMvc mockMvc; //responsável por simular as requisições HTTP sem subir um servidor local

    @MockitoBean //camada que quero mocar.
    private ProductService productService;

    private ProductDTO productDTO;
    private PageImpl<ProductDTO> page;
    private long nonExistingId;
    private long existingId;

    @BeforeEach
    void setUp() {
        existingId = 1L;
        nonExistingId = 2000L;
        productDTO = Factory.createProductDTO();
        productDTO.setId(1);
        page = new PageImpl<>(List.of(productDTO));
    }

    @Test
    void findAllShouldReturnAllPage() throws Exception {

        //cria o método mocado
        when(productService.findAll(any()))
                .thenReturn(page);

        //testar requisição
        ResultActions resultActions =
                mockMvc.perform(get("/product").accept("application/json"));

        //analisa o resultado
        resultActions.andExpect(status().isOk());
        
    }
    @Test
    @DisplayName("findById deve retornar ProductDTO quando ID existir")
    void findByIdShouldReturnProductWhenIdExists() throws Exception {
        when(productService.findById(existingId))
                .thenReturn(productDTO);

        ResultActions result = 
            mockMvc.perform(get("/product/{id}", existingId)
                    .accept(MediaType.APPLICATION_JSON));

        result.andExpect(status().isOk())
          .andExpect(jsonPath("$.id").value(existingId))
          .andExpect(jsonPath("$.name").exists())
          .andExpect(jsonPath("$.description").exists());

        verify(productService).findById(existingId);
    }
}