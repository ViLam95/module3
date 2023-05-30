<%--
  Created by IntelliJ IDEA.
  User: viquoclam
  Date: 30/05/2023
  Time: 08:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Create Category</title>
</head>
<body>
<div class="container">
    <h1 style="text-align: center">Form create category</h1>
    <form style="width: 600px; margin: auto" method="post">
        <div class="mb-3">
            <label for="id" class="form-label">Id</label>
            <input type="text" class="form-control" name="id" id="id" placeholder="Enter Id">
        </div>
        <div class="mb-3">
            <label for="name" class="form-label">Name</label>
            <input type="text" class="form-control" name="name" id="name" placeholder="Enter Name">
        </div>
        <div class="mb-3">
            <button class="btn btn-primary" type="submit">Create</button>
            <a class="btn btn-secondary" href="/categories">Back to home</a>
        </div>
    </form>
</div>
</body>
</html>
