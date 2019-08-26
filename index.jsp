<html>
<head>abc</head>
<body>
<form action="BookUpdate" method="get" >
Type of update <select name="type">
<option value="insert">Insert book</option>
<option value="update">Update book</option>
<option value="delete">Delete book</option>
</select>
Book Title to alter: <input type="text" name="title">
Book new title:<input type="text" name="newtitle">
Book new authors: <input type="text" name="author">
Book new publisher: <input type="text" name="pub">
Book new publication year: <input type="number" name="year">
Book new price: <input type="number" name="price">
<input type="submit" value="update book fields">
</form>







</body>
</html>