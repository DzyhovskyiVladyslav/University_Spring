package com.services.db.dao;

import com.services.db.models.Answers;

import java.util.List;

public interface AnswersDAO {
    List<Answers> getAllAnswers();
    void insertAnswers(Answers answers);
}
