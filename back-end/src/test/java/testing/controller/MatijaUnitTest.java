package testing.controller;

import app.Main;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Main.class)
public class MatijaUnitTest {
    /* Konstante za upotrebu */
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


    /* Testovi */

    /*  Test funkcije GetPricings za MedicineController
        Funkcija treba da vrati sve cenovnike koji su postojali za lek 'lek1'
     */
    @Test
    public void testGetPricingsByPharmacy() throws Exception{
        mockMvc
                .perform(get(URL_PREFIX + "/medicine/pricings/abc"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(contentType))
                .andExpect(jsonPath("$", hasSize(3)))
                .andExpect(jsonPath("$.[*].medicineDTO.code").value(hasItem("lek1")));
    }

    /*  Test funkcije DeletePharmacistEmployement u PharmacistController
        Funkcija treba da pronadje farmaceuta i ukloni mu zaposlenje
     */
    @Test
    @Transactional
    @Rollback(true)
    public void testDeletePharmacistEmployment() throws Exception {
        mockMvc
                .perform(delete(URL_PREFIX + "/pharmacist/delete/employment/farm@gmail.com"))
                .andExpect(status().isOk());
    }

    /*  Test funkije GetPharmacyVacationRequest u VaccationController
        Funkija treba da nadje sve STANDBY zahteve za odmorima u apoteci
     */
    @Test
    public void testGetPharmacyVacationRequests() throws Exception {
        mockMvc
                .perform(get(URL_PREFIX + "/vaccation/pharmacist/requests/abc"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(contentType))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$.[*].medicalWorkerId").value(hasItem("farm@gmail.com")));
    }
}
