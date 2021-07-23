package quiz.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import quiz.controllerAdvice.AnswerExceptionHandler;
import quiz.entity.Answer;
import quiz.service.AnswerService;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class AnswerControllerTest {
    @Mock
    private AnswerService answerService;
    @InjectMocks
    private AnswerController answerController;

    private MockMvc mockMvc;
    private ObjectMapper mapper;

    private Answer expectedAnswer;
    private List<Answer> expectedAnswers;

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(answerController)
                .setControllerAdvice(new AnswerExceptionHandler())
                .setUseSuffixPatternMatch(false)
                .build();

        mapper = new ObjectMapper();

        expectedAnswer = new Answer();
        expectedAnswers = new ArrayList<Answer>() {{
            add(expectedAnswer);
        }};

        when(answerService.getAllAnswers()).thenReturn(expectedAnswers);
        when(answerService.saveAnswer(any(Answer.class))).thenReturn(expectedAnswer);
    }

    @Test
    public void testGetAllAnswers() throws Exception {
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders
                .get(AnswerController.PATH))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        assertEquals(mapper.writeValueAsString(expectedAnswers), result.getResponse().getContentAsString());
    }

    @Test
    public void testPostAnswer() throws Exception {
        MvcResult result = mockMvc
                .perform(MockMvcRequestBuilders
                        .post(AnswerController.PATH)
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content("{}"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        assertEquals(mapper.writeValueAsString(expectedAnswer), result.getResponse().getContentAsString());
    }
}
