package com.chittagongquiz.dao;

import com.chittagongquiz.model.Result;
import com.chittagongquiz.util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ResultDAO {

    /** Save a completed quiz attempt (name + score) back to the database. */
    public void saveResult(Result result) throws SQLException {
        String sql = "INSERT INTO results (player_name, score, total_questions) VALUES (?, ?, ?)";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, result.getPlayerName());
            ps.setInt(2, result.getScore());
            ps.setInt(3, result.getTotalQuestions());
            ps.executeUpdate();

            try (ResultSet keys = ps.getGeneratedKeys()) {
                if (keys.next()) {
                    result.setId(keys.getInt(1));
                }
            }
        }
    }

    /** Optional: leaderboard / history view, ordered by best score then most recent. */
    public List<Result> getAllResults() throws SQLException {
        List<Result> list = new ArrayList<>();
        String sql = "SELECT id, player_name, score, total_questions, attempt_time " +
                     "FROM results ORDER BY score DESC, attempt_time DESC";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Result r = new Result();
                r.setId(rs.getInt("id"));
                r.setPlayerName(rs.getString("player_name"));
                r.setScore(rs.getInt("score"));
                r.setTotalQuestions(rs.getInt("total_questions"));
                r.setAttemptTime(rs.getTimestamp("attempt_time"));
                list.add(r);
            }
        }
        return list;
    }
}
