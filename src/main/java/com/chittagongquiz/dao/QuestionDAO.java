package com.chittagongquiz.dao;

import com.chittagongquiz.model.Question;
import com.chittagongquiz.util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class QuestionDAO {

    /** Fetch all questions (used to render the quiz form). */
    public List<Question> getAllQuestions() throws SQLException {
        List<Question> list = new ArrayList<>();
        String sql = "SELECT id, question_text, option_a, option_b, option_c, option_d, correct_option, category " +
                     "FROM questions ORDER BY id";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Question q = new Question(
                        rs.getInt("id"),
                        rs.getString("question_text"),
                        rs.getString("option_a"),
                        rs.getString("option_b"),
                        rs.getString("option_c"),
                        rs.getString("option_d"),
                        rs.getString("correct_option"),
                        rs.getString("category")
                );
                list.add(q);
            }
        }
        return list;
    }

    /** Fetch just the correct answers as a map of questionId -> correctOption, used for scoring. */
    public java.util.Map<Integer, String> getAnswerKey() throws SQLException {
        java.util.Map<Integer, String> map = new java.util.HashMap<>();
        String sql = "SELECT id, correct_option FROM questions";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                map.put(rs.getInt("id"), rs.getString("correct_option"));
            }
        }
        return map;
    }
}
