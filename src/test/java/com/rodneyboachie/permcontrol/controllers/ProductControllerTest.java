package com.rodneyboachie.permcontrol.controllers;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.rodneyboachie.permcontrol.PermControlApplication;
import com.rodneyboachie.permcontrol.config.ContainersEnvironment;
import com.rodneyboachie.permcontrol.controllers.dto.ProductDTO;
import com.rodneyboachie.permcontrol.controllers.dto.UserDTO;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.math.BigDecimal;

/**
 * author: acerbk
 * Date: 23/02/2022
 * Time: 1:12 am
 */

@ActiveProfiles("test")
@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
@SpringBootTest(classes = PermControlApplication.class,webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ProductControllerTest extends ContainersEnvironment {

    @Autowired
    private MockMvc mockMvc;

    public static final String RODNEY_USERNAME = "rodney"; //has buyer role
    public static final String BEA_USERNAME = "bea"; //has seller role
    public static final String MAX_USERNAME = "max"; //has seller role

    //JWT tokens for three sample users already in db
    public static String RODNEY_JWT;
    public static String MAX_JWT;
    public static String BEA_JWT;

    @Before
    public void setUp() throws Exception {

        RODNEY_JWT = getJWT("rodney", "password");
        MAX_JWT = getJWT("max", "password");
        BEA_JWT = getJWT("bea", "password");
    }

    @After
    public void tearDown() {

    }

    /**
     * since we didn't add any Authorization header, we expect 403
     *
     * @throws Exception
     */
    @Test
    public void should_get_all_products_expect_403() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/products")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isForbidden());
    }

    @Test
    public void should_get_all_products_successfully_when_user_with_buyer_role_requests() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("Authorization", "Bearer " + RODNEY_JWT))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.data").exists());
    }

    @Test
    public void should_get_forbidden_when_user_with_seller_role_requests_all_products() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("Authorization", "Bearer " + MAX_JWT))
                .andExpect(MockMvcResultMatchers.status().isForbidden());
    }

    @Test
    public void should_get_only_seller_products_when_seller_requests_to_view_products() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/products/user/" + BEA_USERNAME)
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("Authorization", "Bearer " + BEA_JWT))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void should_create_new_product_expect_created_201_status() throws Exception {
        ProductDTO productDTO = new ProductDTO(MAX_USERNAME, "Sony Sound System",
                new BigDecimal(234500), new BigDecimal(40));
        this.mockMvc.perform(MockMvcRequestBuilders.post("/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(productDTO))
                        .header("Authorization", "Bearer " + MAX_JWT))
                .andExpect(MockMvcResultMatchers.status().isCreated());
    }

    @Test
    public void should_try_to_create_new_product_as_buyer_expect_forbidden_403_status() throws Exception {
        ProductDTO productDTO = new ProductDTO(RODNEY_JWT, "Sony Sound System",
                new BigDecimal(234500), new BigDecimal(40));
        this.mockMvc.perform(MockMvcRequestBuilders.post("/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(productDTO))
                        .header("Authorization", "Bearer " + RODNEY_JWT))
                .andExpect(MockMvcResultMatchers.status().isForbidden());
    }

    /**
     * Make a post request to get jwt from header
     *
     * @param username
     * @param password
     * @return
     * @throws Exception
     */
    public String getJWT(String username, String password) throws Exception {
        ResultActions resultActions = this.mockMvc.perform(MockMvcRequestBuilders.post("/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(new UserDTO(username, password))));
        MvcResult mvcResult = resultActions.andReturn();

        String authorizationHeaderValue = mvcResult.getResponse().getHeader("Authorization");

        authorizationHeaderValue = authorizationHeaderValue.replace("Bearer ", "");

        return authorizationHeaderValue;
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
