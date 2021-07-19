package quiz.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import quiz.entity.PossibleAnswer;
import quiz.model.Response;
import quiz.service.PossibleAnswerService;

@RestController
@RequestMapping(PossibleAnswerController.PATH)
public class PossibleAnswerController {

    public static final String PATH = "/api/quiz/possible-answer";
    private static final Logger log = LoggerFactory.getLogger(PossibleAnswerController.class);
    private final PossibleAnswerService possibleAnswerService;

    public PossibleAnswerController(PossibleAnswerService possibleAnswerService) {
        this.possibleAnswerService = possibleAnswerService;
    }

    @PostMapping(consumes = "application/json;charset=UTF-8")
    public Response savePossibleAnswer(@RequestBody PossibleAnswer newPossibleAnswer) {
        log.info("Request to save a new possible answer received: {}", newPossibleAnswer);
        return possibleAnswerService.savePossibleAnswer(newPossibleAnswer);
    }
}
