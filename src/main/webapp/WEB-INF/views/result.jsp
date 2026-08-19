<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Your Result</title>
    <style>
        body { font-family: Arial, sans-serif; background:#f4f6f8; display:flex; align-items:center; justify-content:center; height:100vh; margin:0; }
        .card { background:#fff; padding:40px; border-radius:10px; box-shadow:0 4px 12px rgba(0,0,0,0.1); width:360px; text-align:center; }
        h1 { color:#1c4e80; font-size:20px; }
        .score { font-size:48px; font-weight:bold; color:#1c4e80; margin:20px 0 6px; }
        .out-of { color:#777; margin-bottom:24px; }
        a { display:inline-block; margin:6px 8px; padding:10px 18px; background:#1c4e80; color:#fff; text-decoration:none; border-radius:6px; font-size:14px; }
        a.secondary { background:#eee; color:#333; }
    </style>
</head>
<body>
    <div class="card">
        <h1>Well done, <%= request.getAttribute("playerName") %>!</h1>
        <div class="score"><%= request.getAttribute("score") %> / <%= request.getAttribute("total") %></div>
        <div class="out-of">Your name and score have been saved.</div>
        <a href="index.jsp">Try Again</a>
        <a class="secondary" href="leaderboard">Leaderboard</a>
    </div>
</body>
</html>
