package org.motion.api;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.motion.model.productType.Coffee;
import org.motion.model.productType.Fish;
import org.motion.model.productType.Product;
import org.motion.persistence.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@WebMvcTest(value = ProductController.class, secure = false)
public class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductRepository mockRepository;


    @Test
    public void testCreateProduct() throws Exception {
        Coffee mockCoffee = new Coffee("1", "u123", "coffee", "coffeeDescription", 12.0, 12121L,  12312L, 1.0, "arabica");
        Mockito.when(mockRepository.save(Mockito.any(Product.class))).thenReturn(mockCoffee);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/products/create")
                                        .accept(MediaType.APPLICATION_JSON_VALUE).content("{\"createTimeMillis\": 12313123123123, \"expiryDateMillis\": 12312312313, \"userId\":\"1\", \"productType\": \"coffee\", \"typeDescription\": \"coffee from india\", \"variety\": \"ARobusta\", \"productType\": \"coffee\", \"cuppingScore\": 90.0, \"unitMass\": 10.1 }").contentType(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();
        assertEquals(HttpStatus.OK.value(), response.getStatus());
    }


    @Test
    public void testCreateFish() throws Exception {
        Fish mockFish = new Fish("f12", "u234", "fish", "tuna fish", 12.0, 123131231L, 123123113L, "v123", 1213123123L);
        Mockito.when(mockRepository.save(Mockito.any(Product.class))).thenReturn(mockFish);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/products/create")
                .accept(MediaType.APPLICATION_JSON_VALUE).content("{\"createTimeMillis\": 12313123123123, \"expiryDateMillis\": 12312312313, \"userId\":\"1\", \"productType\": \"fish\", \"typeDescription\": \"coffee from india\", \"vesselId\": \"v123\", \"unitMass\": 10.1 , \"catchDateMillis\": 123131231}").contentType(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();
        assertEquals(HttpStatus.OK.value(), response.getStatus());
    }

    @Test
    public void testCreateProduct_Fails_When_Incomplete() throws Exception {
        Coffee mockCoffee = new Coffee("1", "u123", "coffee", "coffeeDescription", 12.0, 12121L,  12312L, 1.0, "arabica");
        Mockito.when(mockRepository.save(Mockito.any(Product.class))).thenReturn(mockCoffee);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/products/create")
                .accept(MediaType.APPLICATION_JSON_VALUE).content("{ \"productType\": \"coffee\", \"typeDescription\": \"coffee from india\", \"variety\": \"ARobusta\", \"productType\": \"coffee\", \"cuppingScore\": 90.0, \"unitMass\": 10.1 }").contentType(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();
        assertEquals(HttpStatus.BAD_REQUEST.value(), response.getStatus());
    }

    @Test
    public void testCreateProductFails_When_Timestamps_Are_Invalid() throws Exception {
        Coffee mockCoffee = new Coffee("1", "u123", "coffee", "coffeeDescription", 12.0, 12121L,  12312L, 1.0, "arabica");
        Mockito.when(mockRepository.save(Mockito.any(Product.class))).thenReturn(mockCoffee);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/products/create")
                .accept(MediaType.APPLICATION_JSON_VALUE).content("{\"createdDate\": 0, \"expiryDate\": 12312312313, \"userId\":\"1\", \"productType\": \"coffee\", \"typeDescription\": \"coffee from india\", \"variety\": \"ARobusta\", \"productType\": \"coffee\", \"cuppingScore\": 90.0, \"unitMass\": 10.1 }").contentType(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();
        assertEquals(HttpStatus.BAD_REQUEST.value(), response.getStatus());
    }
}
