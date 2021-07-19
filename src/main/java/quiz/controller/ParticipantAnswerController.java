package quiz.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import quiz.entity.ParticipantAnswer;
import quiz.entity.QuestionType;
import quiz.model.Response;
import quiz.service.ParticipantAnswerService;

@RestController
@RequestMapping(ParticipantAnswerController.PATH)
public class ParticipantAnswerController {

    public static final String PATH = "/api/quiz/participant-answer";
    private static final Logger log = LoggerFactory.getLogger(ParticipantAnswerController.class);
    private final ParticipantAnswerService participantAnswerService;

    public ParticipantAnswerController(ParticipantAnswerService participantAnswerService) {
        this.participantAnswerService = participantAnswerService;
    }

    @PostMapping(value = "/{questionType}", consumes = "application/json;charset=UTF-8")
    public Response saveParticipantAnswer(@PathVariable QuestionType questionType,
                                          @RequestBody ParticipantAnswer participantAnswer) {
        log.info("Request to save a new participant answer for a {} received: {}", questionType, participantAnswer);
        return participantAnswerService.saveParticipantAnswer(questionType, participantAnswer);
    }
}
