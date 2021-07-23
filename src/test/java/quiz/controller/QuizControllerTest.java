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
import quiz.controllerAdvice.QuizExceptionHandler;
import quiz.entity.Quiz;
import quiz.model.Response;
import quiz.service.QuizService;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class QuizControllerTest {
    @Mock
    private QuizService quizService;
    @InjectMocks
    private QuizController quizController;

    private MockMvc mockMvc;
    private ObjectMapper mapper;

    private Quiz expectedQuiz;
    private List<Quiz> expectedQuizzes;
    private static final Long ONE = 1L;

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(quizController)
                .setControllerAdvice(new QuizExceptionHandler())
                .setUseSuffixPatternMatch(false)
                .build();

        mapper = new ObjectMapper();

        expectedQuiz = new Quiz();
        expectedQuizzes = new ArrayList<Quiz>() {{
            add(expectedQuiz);
        }};

        when(quizService.getAllQuizzes()).thenReturn(expectedQuizzes);
        when(quizService.getActiveQuizzes()).thenReturn(expectedQuizzes);
        when(quizService.getQuizzesPassedByParticipant(any(Long.class))).thenReturn(new HashSet<>(expectedQuizzes));
        when(quizService.getQuiz(any(Long.class))).thenReturn(expectedQuiz);
        when(quizService.saveQuiz(any(Quiz.class))).thenReturn(expectedQuiz);
        when(quizService.updateQuiz(any(Long.class), any(Quiz.class))).thenReturn(expectedQuiz);
        when(quizService.deleteQuiz(any(Long.class)))
                .thenReturn(new Response(HttpStatus.OK.value(), "Quiz with ID=" + ONE + " has been deleted."));
    }

    @Test
    public void testGetQuizzes() throws Exception {
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders
                .get(QuizController.PATH))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        assertEquals(mapper.writeValueAsString(expectedQuizzes), result.getResponse().getContentAsString());
    }

    @Test
    public void testGetActiveQuizzes() throws Exception {
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders
                .get(QuizController.PATH + "/active"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        assertEquals(mapper.writeValueAsString(expectedQuizzes), result.getResponse().getContentAsString());
    }

    @Test
    public void testGetPassedQuizzes() throws Exception {
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders
                .get(QuizController.PATH + "/passed-quizzes/{participantId}", ONE.toString()))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        assertEquals(mapper.writeValueAsString(new HashSet<>(expectedQuizzes)), result.getResponse().getContentAsString());
    }

    @Test
    public void testGetQuiz() throws Exception {
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders
                .get(QuizController.PATH + "/{id}", ONE.toString()))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        assertEquals(mapper.writeValueAsString(expectedQuiz), result.getResponse().getContentAsString());
    }

    @Test
    public void testSaveQuiz() throws Exception {
        MvcResult result = mockMvc
                .perform(MockMvcRequestBuilders
                        .post(QuizController.PATH)
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content("{}"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        assertEquals(mapper.writeValueAsString(expectedQuiz), result.getResponse().getContentAsString());
    }

    @Test
    public void testUpdateOrSaveQuiz() throws Exception {
        MvcResult result = mockMvc
                .perform(MockMvcRequestBuilders
                        .put(QuizController.PATH + "/{id}", ONE.toString())
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content("{}"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        assertEquals(mapper.writeValueAsString(expectedQuiz), result.getResponse().getContentAsString());
    }

    @Test
    public void testDeleteQuiz() throws Exception {
        MvcResult result = mockMvc
                .perform(MockMvcRequestBuilders
                        .delete(QuizController.PATH + "/{id}", ONE.toString())
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content("{}"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        assertEquals(mapper.writeValueAsString(
                new Response(HttpStatus.OK.value(), "Quiz with ID=" + ONE + " has been deleted.")),
                result.getResponse().getContentAsString());
    }
}
