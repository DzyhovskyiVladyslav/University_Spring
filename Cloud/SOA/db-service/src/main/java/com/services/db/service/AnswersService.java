package com.services.db.service;

import com.services.db.models.Answers;

import java.util.List;

public interface AnswersService {

    List<Answers> getAllAnswers();
    void insertAnswers(Answers answers);

}
