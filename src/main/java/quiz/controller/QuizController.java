package quiz.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import quiz.entity.Quiz;
import quiz.model.Response;
import quiz.service.QuizService;

import java.util.List;

@RestController
@RequestMapping(QuizController.PATH)
public class QuizController {

    public static final String PATH = "/api/quiz/quiz";
    private static final Logger log = LoggerFactory.getLogger(QuizController.class);
    private final QuizService quizService;

    public QuizController(QuizService quizService) {
        this.quizService = quizService;
    }

    @GetMapping(produces = "application/json;charset=UTF-8")
    public List<Quiz> getQuizzes() {
        log.info("Request to get all quizzes received.");
        return quizService.getAllQuizzes();
    }

    @GetMapping(value = "/active", produces = "application/json;charset=UTF-8")
    public List<Quiz> getActiveQuizzes() {
        log.info("Request to get all active quizzes received.");
        return quizService.getActiveQuizzes();
    }

    @GetMapping(value = "/{id}", produces = "application/json;charset=UTF-8")
    public Quiz getQuiz(@PathVariable(value = "id") Long id) {
        log.info("Request to get the quiz with ID={} received.", id);
        return quizService.getQuiz(id);
    }

    @PostMapping(consumes = "application/json;charset=UTF-8")
    public Quiz saveQuiz(@RequestBody Quiz newQuiz) {
        log.info("Request to save a new quiz received. New quiz: {}", newQuiz);
        return quizService.saveQuiz(newQuiz);
    }

    @PutMapping(value = "/{id}", consumes = "application/json;charset=UTF-8")
    public Quiz updateOrSaveQuiz(@PathVariable("id") Long id, @RequestBody Quiz newQuiz) {
        log.info("Request to update the quiz with ID={} received. Quiz: {}", id, newQuiz);
        return quizService.updateQuiz(id, newQuiz);
    }

    @DeleteMapping(value = "/{id}", produces = "application/json;charset=UTF-8")
    public Response deleteQuiz(@PathVariable("id") Long id) {
        log.info("Request to delete the quiz with ID={} received", id);
        return quizService.deleteQuiz(id);
    }
}
