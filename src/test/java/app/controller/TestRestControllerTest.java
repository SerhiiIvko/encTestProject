package app.controller;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestRestControllerTest {

    @Autowired
    WebApplicationContext webApplicationContext;

    private MockMvc mvc;

    @Before
    public void setUp() throws Exception {
        mvc = MockMvcBuilders
                .webAppContextSetup(webApplicationContext)
                .build();
    }

    @After
    public void tearDown() throws Exception {
        mvc = null;
    }

    @Test
    public void postEncryptionMessage() throws Exception {
        //GIVEN
        String urlPattern = "/1";
        String content = "{\"id\": 1}";
        RequestBuilder request = MockMvcRequestBuilders
                .post(urlPattern)
                .content(content)
                .contentType(MediaType.APPLICATION_JSON);

        //WHEN
        MvcResult result = mvc
                .perform(request)
                .andExpect(status().isOk())
                .andReturn();
        int expectedStatus = 200;
        int actualStatus = result.getResponse().getStatus();

        //THEN
        assertEquals(expectedStatus, actualStatus);
        assertNotNull(result);
    }
}