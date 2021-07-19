package quiz.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import quiz.entity.Participant;
import quiz.service.ParticipantService;

@RestController
@RequestMapping(ParticipantController.PATH)
public class ParticipantController {

    public static final String PATH = "/api/quiz/participant";
    private static final Logger log = LoggerFactory.getLogger(ParticipantController.class);
    private final ParticipantService participantService;

    public ParticipantController(ParticipantService participantService) {
        this.participantService = participantService;
    }

    @PostMapping(consumes = "application/json;charset=UTF-8")
    public Participant saveParticipant(@RequestBody Participant newParticipant) {
        log.info("Request to save a new participant received. New participant: {}", newParticipant);
        return participantService.saveParticipant(newParticipant);
    }
}
