<html>
<head>

<link rel ="stylesheet" href ="index1_styles.css"/>   <!-- Το όνομα του αρχείου .css -->
</head>


<center>
<h3> Welcome Root ! </h3>
</center>

<body background = basic.JPG>  <!-- Το όνομα του αρχείου εικόνας -->


<div class="login-page">
  <div class="form">

<form action = "Create_User.php" method ="POST">

 <button type ="submit" name ="create_user_button"> Create User </button> 
</form>


<form action = "Delete_User.php" method ="POST">

 <button type = "submit" name ="delete_user_button"> Delete User </button> 

</form>


<form action = "Show_results.php" method ="POST">

 <button type = "submit" name ="show_result_button"> Show All Users </button> 
 

</form>


<form action = "User_Problem.php" method ="POST">

<button type ="submit" name = "show_users_problems"> Show User Problem </button>
</form>

<br>
<br>

<?php


$url ="http://$_SERVER[HTTP_HOST]$_SERVER[REQUEST_URI]";
if (strpos($url,"Create_User=success") == true )
	
	{
	echo '<p style ="color:green"> User_Created ! </p>';
	 
	}
else if (strpos ($url,"Delete_User=success")== true)
{
	echo '<p style ="color:green"> User_Deleted ! </p>';
}

?>
   
  </div>
</div>

</body>
</html>


