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
import quiz.controllerAdvice.QuestionExceptionHandler;
import quiz.entity.Question;
import quiz.model.Response;
import quiz.service.QuestionService;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class QuestionControllerTest {

    @Mock
    private QuestionService questionService;
    @InjectMocks
    private QuestionController questionController;

    private MockMvc mockMvc;
    private ObjectMapper mapper;

    private Question expectedQuestion;
    private static final Long ONE = 1L;

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(questionController)
                .setControllerAdvice(new QuestionExceptionHandler())
                .setUseSuffixPatternMatch(false)
                .build();

        mapper = new ObjectMapper();

        expectedQuestion = new Question();

        when(questionService.saveQuestion(any(Long.class), any(Question.class)))
                .thenReturn(expectedQuestion);
        when(questionService.updateQuestion(any(Long.class), any(Question.class)))
                .thenReturn(expectedQuestion);
        when(questionService.deleteQuestion(any(Long.class)))
                .thenReturn(new Response(HttpStatus.OK.value(), "Question with ID=" + ONE + " has been deleted."));
    }

    @Test
    public void testSaveQuestion() throws Exception {
        MvcResult result = mockMvc
                .perform(MockMvcRequestBuilders
                        .post(QuestionController.PATH)
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .param("quizId", ONE.toString())
                        .content("{}"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        assertEquals(mapper.writeValueAsString(expectedQuestion), result.getResponse().getContentAsString());
    }

    @Test
    public void tetUpdateOrSaveQuestion() throws Exception {
        MvcResult result = mockMvc
                .perform(MockMvcRequestBuilders
                        .put(QuestionController.PATH + "/{id}", ONE)
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content("{}"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        assertEquals(mapper.writeValueAsString(expectedQuestion), result.getResponse().getContentAsString());
    }

    @Test
    public void testDeleteQuestion() throws Exception {
        MvcResult result = mockMvc
                .perform(MockMvcRequestBuilders
                        .delete(QuestionController.PATH + "/{id}", ONE)
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content("{}"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        assertEquals(mapper.writeValueAsString(
                new Response(HttpStatus.OK.value(), "Question with ID=" + ONE + " has been deleted.")),
                result.getResponse().getContentAsString());
    }
}