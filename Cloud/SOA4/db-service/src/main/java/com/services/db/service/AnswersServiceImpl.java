package com.services.db.service;

import com.services.db.dao.AnswersDAO;
import com.services.db.models.Answers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnswersServiceImpl implements AnswersService {

    @Autowired
    AnswersDAO answersDAO;

    public List<Answers> getAllAnswers() {

       List<Answers> answers = answersDAO.getAllAnswers();
        return answers;
    }

    @Override
    public void insertAnswers(Answers answers) {
        answersDAO.insertAnswers(answers);
    }
}
