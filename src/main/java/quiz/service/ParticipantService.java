package quiz.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import quiz.entity.Participant;
import quiz.exception.AttemptToUpdateIdException;
import quiz.repository.ParticipantRepository;

@Service
public class ParticipantService {

    private static final Logger log = LoggerFactory.getLogger(ParticipantService.class);
    private final ParticipantRepository repository;

    public ParticipantService(ParticipantRepository repository) {
        this.repository = repository;
    }

    public Participant saveParticipant(Participant newParticipant) {
        if (newParticipant.getId() != null) {
            throw new AttemptToUpdateIdException();
        }
        log.info("Saving a new participant. The new participant: {}", newParticipant);
        return repository.saveAndFlush(newParticipant);
    }
}
