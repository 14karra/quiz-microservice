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
import quiz.controllerAdvice.ParticipantExceptionHandler;
import quiz.entity.Participant;
import quiz.service.ParticipantService;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ParticipantControllerTest {
    @Mock
    private ParticipantService participantService;
    @InjectMocks
    private ParticipantController participantController;

    private MockMvc mockMvc;
    private ObjectMapper mapper;

    private Participant expectedParticipant;

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(participantController)
                .setControllerAdvice(new ParticipantExceptionHandler())
                .setUseSuffixPatternMatch(false)
                .build();

        mapper = new ObjectMapper();
        expectedParticipant = new Participant();

        when(participantService.saveParticipant(any(Participant.class)))
                .thenReturn(expectedParticipant);
    }

    @Test
    public void testSaveParticipantAnswer() throws Exception {
        MvcResult result = mockMvc
                .perform(MockMvcRequestBuilders
                        .post(ParticipantController.PATH)
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content("{}"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        assertEquals(mapper.writeValueAsString(expectedParticipant), result.getResponse().getContentAsString());
    }
}
