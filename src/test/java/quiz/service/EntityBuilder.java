package quiz.service;

import quiz.entity.Answer;

abstract class EntityBuilder {

    static Answer buildAnswer() {
        return new Answer.Builder()
                .text("")
                .build();
    }
}
