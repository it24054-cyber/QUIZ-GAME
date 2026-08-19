<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.chittagongquiz.model.Result" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Leaderboard</title>
    <style>
        body { font-family: Arial, sans-serif; background:#f4f6f8; margin:0; padding:30px; }
        .container { max-width:500px; margin:0 auto; background:#fff; padding:30px; border-radius:10px; box-shadow:0 4px 12px rgba(0,0,0,0.08); }
        h1 { color:#1c4e80; font-size:20px; margin-bottom:20px; }
        table { width:100%; border-collapse:collapse; }
        th, td { text-align:left; padding:8px 6px; border-bottom:1px solid #eee; font-size:14px; }
        th { color:#777; font-size:12px; text-transform:uppercase; }
        a { display:inline-block; margin-top:20px; padding:10px 18px; background:#1c4e80; color:#fff; text-decoration:none; border-radius:6px; font-size:14px; }
    </style>
</head>
<body>
<div class="container">
    <h1>Leaderboard</h1>
    <table>
        <tr><th>#</th><th>Name</th><th>Score</th><th>Time</th></tr>
        <%
            List<Result> results = (List<Result>) request.getAttribute("results");
            int rank = 1;
            for (Result r : results) {
        %>
        <tr>
            <td><%= rank++ %></td>
            <td><%= r.getPlayerName() %></td>
            <td><%= r.getScore() %> / <%= r.getTotalQuestions() %></td>
            <td><%= r.getAttemptTime() %></td>
        </tr>
        <% } %>
    </table>
    <a href="index.jsp">Back to Quiz</a>
</div>
</body>
</html>
