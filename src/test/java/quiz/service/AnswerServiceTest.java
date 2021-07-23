package quiz.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import quiz.entity.Answer;
import quiz.exception.AttemptToUpdateIdException;
import quiz.repository.AnswerRepository;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class AnswerServiceTest {

    @Mock
    private AnswerRepository answerRepository;
    @InjectMocks
    private AnswerService answerService;

    private Answer expectedAnswer;
    private List<Answer> expectedAnswers;

    @Before
    public void setUp() {
        expectedAnswer = EntityBuilder.buildAnswer();
        expectedAnswers = new ArrayList<Answer>() {{
            add(expectedAnswer);
        }};

        when(answerRepository.saveAndFlush(expectedAnswer)).thenReturn(expectedAnswer);
        when(answerRepository.findAll()).thenReturn(expectedAnswers);
        when(answerRepository.findParticipantAnswers(1L, 1L)).thenReturn(new HashSet<>(expectedAnswers));
    }

    @Test
    public void testSaveAnswer() {
        Answer testAnswer = answerService.saveAnswer(expectedAnswer);
        assertEquals(expectedAnswer, testAnswer);
    }

    @Test
    public void testSaveAnswerWithId() {
        Answer answerWithId = new Answer();
        answerWithId.setId(1L);

        assertThrows(AttemptToUpdateIdException.class, () -> answerService.saveAnswer(answerWithId));
    }

    @Test
    public void testGetAllAnswers() {
        List<Answer> testAnswers = answerService.getAllAnswers();
        assertEquals(expectedAnswers, testAnswers);
    }

    @Test
    public void testGetParticipantAnswers() {
        Set<Answer> testAnswers = answerService.getParticipantAnswers(1L, 1L);
        assertEquals(new HashSet<>(expectedAnswers), testAnswers);
    }

    @Test
    public void testDeleteAnswer() {
        assertDoesNotThrow(() -> answerService.deleteAnswer(1L));
    }
}
