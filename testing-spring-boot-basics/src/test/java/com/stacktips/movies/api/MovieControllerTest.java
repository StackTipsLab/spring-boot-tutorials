package com.stacktips.movies.api;

import com.stacktips.movies.service.MovieService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(MovieController.class)
class MovieControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MovieService movieService;

    @Test
    void greetingShouldReturnMockResponse() throws Exception {
        List<String> moviesMock = List.of("Sprider Man", "X-Man", "Iron Man");
        when(movieService.getMovies()).thenReturn(moviesMock);

        this.mockMvc.perform(get("/movies"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0]", is("Sprider Man")))
                .andExpect(jsonPath("$[1]", is("X-Man")))
                .andExpect(jsonPath("$[2]", is("Iron Man")));
    }

}
