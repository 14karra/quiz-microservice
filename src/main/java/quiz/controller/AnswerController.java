package quiz.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import quiz.entity.Answer;
import quiz.service.AnswerService;

import java.util.List;

@RestController
@RequestMapping(AnswerController.PATH)
public class AnswerController {

    public static final String PATH = "/api/quiz/answer";
    private static final Logger log = LoggerFactory.getLogger(AnswerController.class);
    private final AnswerService answerService;

    public AnswerController(AnswerService answerService) {
        this.answerService = answerService;
    }

    @GetMapping(produces = "application/json;charset=UTF-8")
    public List<Answer> getAllAnswers() {
        log.info("Request to get all answers received.");
        return answerService.getAllAnswers();
    }

    @PostMapping(consumes = "application/json;charset=UTF-8")
    public Answer saveAnswer(@RequestBody Answer newAnswer) {
        log.info("Request to save a new answer received. New answer: {}", newAnswer);
        return answerService.saveAnswer(newAnswer);
    }
}
