package rent.mail.controller;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.subethamail.wiser.Wiser;
import rent.mail.MailConfig;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static rent.mail.controller.WiserAssertions.assertReceivedMessage;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = MailConfig.class)
@WebAppConfiguration
@TestPropertySource("/mail-test.properties")
public class MailControllerTest {

    private Wiser wiser;

    @Autowired
    private WebApplicationContext wac;
    private MockMvc mockMvc;


    @Before
    public void setUp() throws Exception {
        wiser = new Wiser();
        wiser.setPort(7771);
        wiser.start();
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }

    @After
    public void tearDown() throws Exception {
        wiser.stop();
    }

    @Test
    public void shouldSendEmail() throws Exception {
        // act
        mockMvc.perform(get("/mail"))
                .andExpect(status().isCreated());
        // assert
        assertReceivedMessage(wiser)
                .from("someone@localhost")
                .to("someone@localhost")
                .withSubject("Lorem ipsum")
                .withContent("Lorem ipsum dolor sit amet [...]");
    }
}
