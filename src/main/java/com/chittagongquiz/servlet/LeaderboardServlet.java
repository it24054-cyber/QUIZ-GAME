package com.chittagongquiz.servlet;

import com.chittagongquiz.dao.ResultDAO;
import com.chittagongquiz.model.Result;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

/**
 * GET /leaderboard
 *  - reads all saved (name, score) rows from the results table
 *  - forwards to leaderboard.jsp
 */
@WebServlet("/leaderboard")
public class LeaderboardServlet extends HttpServlet {

    private final ResultDAO resultDAO = new ResultDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        try {
            List<Result> results = resultDAO.getAllResults();
            req.setAttribute("results", results);
            req.getRequestDispatcher("/WEB-INF/views/leaderboard.jsp").forward(req, resp);
        } catch (SQLException e) {
            throw new ServletException("Error loading leaderboard", e);
        }
    }
}
