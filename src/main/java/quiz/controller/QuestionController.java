package quiz.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import quiz.entity.Question;
import quiz.model.Response;
import quiz.service.QuestionService;

@RestController
@RequestMapping(QuestionController.PATH)
public class QuestionController {

    public static final String PATH = "/api/quiz/question";
    private static final Logger log = LoggerFactory.getLogger(QuestionController.class);
    private final QuestionService questionService;

    public QuestionController(QuestionService questionService) {
        this.questionService = questionService;
    }

    @PostMapping(consumes = "application/json;charset=UTF-8")
    public Question saveQuestion(@RequestParam("quizId") Long quizId, @RequestBody Question newQuestion) {
        log.info("Request to save a new question for quiz with ID={}. New question: {}", quizId, newQuestion);
        return questionService.saveQuestion(quizId, newQuestion);
    }

    @PutMapping(value = "/{id}", consumes = "application/json;charset=UTF-8")
    public Question updateOrSaveQuestion(@PathVariable("id") Long id, @RequestBody Question newQuestion) {
        log.info("Request to update the question with ID={} received. Question: {}", id, newQuestion);
        return questionService.updateQuestion(id, newQuestion);
    }

    @DeleteMapping(value = "/{id}", produces = "application/json;charset=UTF-8")
    public Response deleteQuestion(@PathVariable("id") Long id) {
        log.info("Request to delete the question with ID={} received", id);
        return questionService.deleteQuestion(id);
    }
}
