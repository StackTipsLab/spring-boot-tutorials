package com.stacktips.app;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.CoreMatchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles({"default", "test"})
@AutoConfigureMockMvc
public class OAuth2FlowTest {
    @Autowired
    private MockMvc mockMvc;

    /**
     * This tests if the OAuth handler properly gives out tokens
     */
    @Test
    public void testClientCredentialsFlow() throws Exception {

        mockMvc.perform(post("/oauth/token")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .content("client_id=test-id&scope=write&grant_type=client_credentials&client_secret=test-secret"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("access_token")))
                .andDo(print());

    }

    @Test
    public void testClientCredentialsFlowAuthFail() throws Exception {

        mockMvc.perform(post("/oauth/token")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .content("client_id=test-id&scope=write&grant_type=client_credentials&client_secret=blah")).andDo(print()).andExpect(status().is(401))
                .andDo(print());

    }

    @Test
    public void testClientCredentialsFlowNoClientID() throws Exception {

        mockMvc.perform(post("/oauth/token")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .content("client_id=windows&scope=write&grant_type=client_credentials&client_secret=sucks")).andDo(print()).andExpect(status().is(401))
                .andDo(print());

    }

    @Test
    public void testPostWithoutClientID() throws Exception {

        mockMvc.perform(post("/welcome")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content("This is as bad as Windows is...")).andDo(print()).andExpect(status().is(401))
                .andExpect(content().string(containsString("unauthorized")))
                .andDo(print());

    }

    @Test
    public void testPostWithWrongClientID() throws Exception {

        mockMvc.perform(post("/welcome")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .header("Authorization", "Bearer randomstring")
                        .content("This is as bad as Windows is...")).andDo(print()).andExpect(status().is(401))
                .andExpect(content().string(containsString("invalid_token")))
                .andDo(print());

    }

    @Test
    public void testPostWithClientID() throws Exception {
        String[] accessToken = new String[1];

        mockMvc.perform(post("/oauth/token")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .content("client_id=test-id&scope=write&grant_type=client_credentials&client_secret=test-secret"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("access_token")))
                .andDo(r -> accessToken[0] = r.getResponse().getContentAsString()
                        .replaceAll(".*\"access_token\":\"([^\"]+)\".*", "$1"));

        mockMvc.perform(post("/welcome")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .header("Authorization", "Bearer " + accessToken[0])
                        .content("This is as bad as Windows is...")).andDo(print()).andExpect(status().is(400))
                .andDo(print());
    }
}