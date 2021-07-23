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
import quiz.controllerAdvice.ParticipantAnswerExceptionHandler;
import quiz.entity.ParticipantAnswer;
import quiz.entity.QuestionType;
import quiz.model.Response;
import quiz.service.ParticipantAnswerService;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ParticipantAnswerControllerTest {
    @Mock
    private ParticipantAnswerService participantAnswerService;
    @InjectMocks
    private ParticipantAnswerController participantAnswerController;

    private MockMvc mockMvc;
    private ObjectMapper mapper;
    private static final Random RANDOM = new Random();

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(participantAnswerController)
                .setControllerAdvice(new ParticipantAnswerExceptionHandler())
                .setUseSuffixPatternMatch(false)
                .build();

        mapper = new ObjectMapper();

        when(participantAnswerService.saveParticipantAnswer(any(QuestionType.class), any(ParticipantAnswer.class)))
                .thenReturn(new Response(HttpStatus.OK.value(), "Successfully saved!"));
    }

    @Test
    public void testSaveParticipantAnswer() throws Exception {
        final List<QuestionType> questionTypes = Collections.unmodifiableList(Arrays.asList(QuestionType.values()));

        MvcResult result = mockMvc
                .perform(MockMvcRequestBuilders
                        .post(ParticipantAnswerController.PATH + "/{questionType}",
                                questionTypes.get(RANDOM.nextInt(questionTypes.size())))
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content("{}"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        assertEquals(mapper.writeValueAsString(new Response(HttpStatus.OK.value(), "Successfully saved!")),
                result.getResponse().getContentAsString());
    }
}
