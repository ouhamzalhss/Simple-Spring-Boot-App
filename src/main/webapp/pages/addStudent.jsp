<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<h3>Add Student</h3>
    <form action="addStudent">
    	<input type="number" name="id" placeholder="id">
    	<input type="text" name="name" placeholder="name">
    	<input type="number" name="marks" placeholder="marks">
    	<input type="submit" name="validate">
    </form>
    
    <h3>Get Student</h3>
      <form action="getStudent">
    	<input type="number" name="id" placeholder="id">
    	<input type="submit" name="validate">
    </form>
     Student: ${student.id }, ${student.name }, ${student.marks }
    
</body>
</html>