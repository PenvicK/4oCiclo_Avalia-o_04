package br.com.api.avalicao4.controller;

import br.com.api.avalicao4.controllers.ProdutoController;
import br.com.api.avalicao4.models.Produto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@SpringBootTest
@ExtendWith(SpringExtension.class)
public class ProdutoControllerTest {

    private MockMvc mockMvc;

    @Autowired
    private ProdutoController controller;

    @BeforeEach
    public void setup(){
        this.mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    @DisplayName("Deve retornar status code 201 quando criar produto com dados corretos")
    public void shouldReturnStatusCode201_WhenCreateProductWithCorrectData() throws Exception {
        Produto produtos = new Produto(1L,"nome", 1, 2.0, 3.0, null, null);

        ObjectMapper mapper = new ObjectMapper();

        String json = mapper.writeValueAsString(produtos);

        this.mockMvc.perform(MockMvcRequestBuilders.post("/produtos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(MockMvcResultMatchers.status().isCreated());
    }

    @Test
    @DisplayName("Deve retornar status code 400 quando criar produto com nome existente")
    public void shouldReturnStatusCode422_WhenCreateProductWithExistentName() throws Exception {
        Produto existingP = new Produto(1L,"nome", 1, 2.0, 3.0, null, null);
        Produto produto = new Produto(2L, "nome", 1, 2.0, 3.0, null, null);

        ObjectMapper mapper = new ObjectMapper();

        String json = mapper.writeValueAsString(existingP);
        String json2 = mapper.writeValueAsString(produto);

        this.mockMvc.perform(MockMvcRequestBuilders.post("/produtos")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json));

        this.mockMvc.perform(MockMvcRequestBuilders.post("/produtos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json2))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }


}
