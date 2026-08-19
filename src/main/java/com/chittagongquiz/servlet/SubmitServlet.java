package com.chittagongquiz.servlet;

import com.chittagongquiz.dao.QuestionDAO;
import com.chittagongquiz.dao.ResultDAO;
import com.chittagongquiz.model.Result;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;

/**
 * POST /submit
 *  - reads the submitted answers (q1..q10) plus playerName
 *  - compares each answer against the answer key from the DB
 *  - computes the score
 *  - saves {name, score} back to the results table
 *  - forwards to result.jsp
 */
@WebServlet("/submit")
public class SubmitServlet extends HttpServlet {

    private final QuestionDAO questionDAO = new QuestionDAO();
    private final ResultDAO resultDAO = new ResultDAO();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String playerName = req.getParameter("playerName");
        if (playerName == null || playerName.trim().isEmpty()) {
            resp.sendRedirect("index.jsp?error=missing_name");
            return;
        }
        playerName = playerName.trim();

        try {
            Map<Integer, String> answerKey = questionDAO.getAnswerKey();
            int totalQuestions = answerKey.size();
            int score = 0;

            for (Map.Entry<Integer, String> entry : answerKey.entrySet()) {
                int questionId = entry.getKey();
                String correctOption = entry.getValue();
                String submittedOption = req.getParameter("q" + questionId); // e.g. "q1"=A/B/C/D

                if (submittedOption != null && submittedOption.equalsIgnoreCase(correctOption)) {
                    score++;
                }
            }

            // Save name + score back to the database
            Result result = new Result(playerName, score, totalQuestions);
            resultDAO.saveResult(result);

            req.setAttribute("playerName", playerName);
            req.setAttribute("score", score);
            req.setAttribute("total", totalQuestions);
            req.getRequestDispatcher("/WEB-INF/views/result.jsp").forward(req, resp);

        } catch (SQLException e) {
            throw new ServletException("Error scoring quiz / saving result", e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        // Guard against GET requests hitting /submit directly
        resp.sendRedirect("index.jsp");
    }
}
