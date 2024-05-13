package com.services.db.dao;

import com.services.db.models.Answers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository
public class AnswersDAOImpl extends JdbcDaoSupport implements AnswersDAO {

    @Autowired
    DataSource dataSource;

    @PostConstruct
    private void initialize() {
        setDataSource(dataSource);
    }

    @Override
    public List<Answers> getAllAnswers() {
        String sql = "SELECT * FROM listOfAnswers";
        List<Map<String, Object>> rows = getJdbcTemplate().queryForList(sql);

        List<Answers> result = new ArrayList<>();
        for (Map<String, Object> row : rows) {
            Answers emp = new Answers();
            emp.setY((int) row.get("y"));
            emp.setN((int) row.get("n"));

            result.add(emp);
        }

        return result;
    }

    @Override
    public void insertAnswers(Answers answers) {
        String sql = "INSERT INTO listOfAnswers " +
                "(y, n) VALUES (?, ?)" ;
        getJdbcTemplate().update(sql, new Object[]{
                answers.getY(),
                answers.getN()
        });

    }
}
