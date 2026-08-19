package com.chittagongquiz.servlet;

import com.chittagongquiz.dao.QuestionDAO;
import com.chittagongquiz.model.Question;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

/**
 * GET /quiz?name=Someone
 *  - captures the player's name (posted from index.jsp)
 *  - loads the 10 MCQs from the database
 *  - forwards to quiz.jsp for rendering
 */
@WebServlet("/quiz")
public class QuizServlet extends HttpServlet {

    private final QuestionDAO questionDAO = new QuestionDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String playerName = req.getParameter("name");
        if (playerName == null || playerName.trim().isEmpty()) {
            resp.sendRedirect("index.jsp?error=missing_name");
            return;
        }

        try {
            List<Question> questions = questionDAO.getAllQuestions();
            req.setAttribute("questions", questions);
            req.setAttribute("playerName", playerName.trim());
            req.getRequestDispatcher("/WEB-INF/views/quiz.jsp").forward(req, resp);
        } catch (SQLException e) {
            throw new ServletException("Error loading questions from database", e);
        }
    }
}
