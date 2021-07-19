package quiz.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import quiz.entity.CorrectAnswer;
import quiz.model.Response;
import quiz.service.CorrectAnswerService;

@RestController
@RequestMapping(CorrectAnswerController.PATH)
public class CorrectAnswerController {

    public static final String PATH = "/api/quiz/correct-answer";
    private static final Logger log = LoggerFactory.getLogger(CorrectAnswerController.class);
    private final CorrectAnswerService correctAnswerService;

    public CorrectAnswerController(CorrectAnswerService correctAnswerService) {
        this.correctAnswerService = correctAnswerService;
    }

    @PostMapping(consumes = "application/json;charset=UTF-8")
    public Response saveCorrectAnswer(@RequestBody CorrectAnswer newCorrectAnswer) {
        log.info("Request to save a new correct answer received: {}", newCorrectAnswer);
        return correctAnswerService.saveCorrectAnswer(newCorrectAnswer);
    }
}
