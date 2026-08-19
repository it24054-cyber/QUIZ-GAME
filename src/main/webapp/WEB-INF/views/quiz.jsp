<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.chittagongquiz.model.Question" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Quiz - Chittagong District Upazilas</title>
    <style>
        body { font-family: Arial, sans-serif; background:#f4f6f8; margin:0; padding:30px; }
        .container { max-width:700px; margin:0 auto; background:#fff; padding:30px; border-radius:10px; box-shadow:0 4px 12px rgba(0,0,0,0.08); }
        h1 { color:#1c4e80; font-size:20px; }
        .player { color:#555; font-size:14px; margin-bottom:20px; }
        .question { margin-bottom:22px; padding-bottom:16px; border-bottom:1px solid #eee; }
        .question p { font-weight:bold; margin-bottom:8px; }
        .tag { display:inline-block; font-size:11px; background:#e3ecf5; color:#1c4e80; padding:2px 8px; border-radius:10px; margin-bottom:6px; }
        label { display:block; margin:6px 0; font-size:14px; cursor:pointer; }
        button { padding:12px 24px; background:#1c4e80; color:#fff; border:none; border-radius:6px; font-size:15px; cursor:pointer; }
        button:hover { background:#153c63; }
    </style>
</head>
<body>
<div class="container">
    <h1>Chittagong District Upazila Quiz</h1>
    <div class="player">Player: <strong><%= request.getAttribute("playerName") %></strong></div>

    <form action="submit" method="post">
        <input type="hidden" name="playerName" value="<%= request.getAttribute("playerName") %>">

        <%
            List<Question> questions = (List<Question>) request.getAttribute("questions");
            int num = 1;
            for (Question q : questions) {
        %>
            <div class="question">
                <span class="tag"><%= q.getCategory() %></span>
                <p><%= num %>. <%= q.getQuestionText() %></p>

                <label><input type="radio" name="q<%= q.getId() %>" value="A" required> <%= q.getOptionA() %></label>
                <label><input type="radio" name="q<%= q.getId() %>" value="B"> <%= q.getOptionB() %></label>
                <label><input type="radio" name="q<%= q.getId() %>" value="C"> <%= q.getOptionC() %></label>
                <label><input type="radio" name="q<%= q.getId() %>" value="D"> <%= q.getOptionD() %></label>
            </div>
        <%
                num++;
            }
        %>

        <button type="submit">Submit Quiz</button>
    </form>
</div>
</body>
</html>
