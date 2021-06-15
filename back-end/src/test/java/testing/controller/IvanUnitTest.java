package testing.controller;

import app.Main;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import java.nio.charset.StandardCharsets;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Main.class)
public class IvanUnitTest {
    private static final String URL_PREFIX = "/api";
    private final MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype(), StandardCharsets.UTF_8);


    /* Postavljanje klasa za test */
    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    @WithMockUser(roles = {"PHARMACIST"})
    public void testFindAllAppointmentsByPharmacistEmail() throws Exception{
        mockMvc
                .perform(get(URL_PREFIX+"/pharmacist/appointments/farm@gmail.com"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(contentType))
                .andExpect(jsonPath("$",hasSize(3)))
                .andExpect(jsonPath("$.[*].patient.email").value(hasItem("ika@gmail.com")));
    }

    @Test
    public void testGetPharmacistByEmail() throws Exception{
        mockMvc
                .perform(get(URL_PREFIX+"/pharmacist/pharm/farm@gmail.com"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(contentType))
                .andExpect(jsonPath("$.email").value("farm@gmail.com"));
    }
    @Test
    @Transactional
    @Rollback(true)
    @WithMockUser(roles = {"PHARMACIST"})
    public void testUpdateOrder()throws Exception{
        mockMvc
                .perform(put(URL_PREFIX+"/medicine/update/3/abc"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").value("OK"));
    }

}
