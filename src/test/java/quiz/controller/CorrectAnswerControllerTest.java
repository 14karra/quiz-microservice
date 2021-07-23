package quiz.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import quiz.controllerAdvice.CorrectAnswerExceptionHandler;
import quiz.entity.CorrectAnswer;
import quiz.model.Response;
import quiz.service.CorrectAnswerService;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CorrectAnswerControllerTest {
    @Mock
    private CorrectAnswerService correctAnswerService;
    @InjectMocks
    private CorrectAnswerController correctAnswerController;

    private MockMvc mockMvc;
    private ObjectMapper mapper;

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(correctAnswerController)
                .setControllerAdvice(new CorrectAnswerExceptionHandler())
                .setUseSuffixPatternMatch(false)
                .build();

        mapper = new ObjectMapper();

        when(correctAnswerService.saveCorrectAnswer(any(CorrectAnswer.class)))
                .thenReturn(new Response(HttpStatus.OK.value(), "Successfully saved!"));
    }

    @Test
    public void testSaveCorrectAnswer() throws Exception {
        MvcResult result = mockMvc
                .perform(MockMvcRequestBuilders
                        .post(CorrectAnswerController.PATH)
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content("{}"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        assertEquals(mapper.writeValueAsString(new Response(HttpStatus.OK.value(), "Successfully saved!")),
                result.getResponse().getContentAsString());
    }
}
