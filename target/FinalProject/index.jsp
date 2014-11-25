<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
<head>
    <title>Laptops</title>
    <link rel="stylesheet" href="css/styles.css" />
</head>

<body>

<div class="header">
    <input type="text" id="searchKey"/>
    <button id="btnSearch">Search</button>
    <button id="btnAdd">New Laptop</button>
</div>


<div class="leftArea">
    <ul id="laptopList"></ul>
</div>

<form id="laptopForm">

    <div class="mainArea">

        <label>Id:</label>
        <input id="laptopId" name="id" type="text" disabled />

        <label>Brandname:</label>
        <input type="text" id="brandname" name="brandname" required>

        <label>Processor:</label>
        <input type="text" id="processor" name="processor"/>

        <label>Diagonal:</label>
        <input type="text" id="diagonal" name="diagonal"/>

        <label>RAM:</label>
        <input type="text" id="ram" name="ram"/>

        <label>HDD:</label>
        <input type="text" id="hdd" name="hdd"/>

        <label>OS:</label>
        <input type="text" id="os" name="os"/>

        <button id="btnSave">Save</button>
        <button id="btnDelete">Delete</button>

    </div>

    <div class="rightArea">

        <img id="pic" height="300" width="500"/>

    </div>

</form>

<script src="js/jquery-1.7.1.min.js"></script>
<script src="js/main.js"></script>

</body>
</html>