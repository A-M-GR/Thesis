<html>
<head>
<link rel ="stylesheet" href ="index1_styles.css">  <!-- Το όνομα του αρχείου .css  -->
</head>



<body background = basic.JPG>  <!-- Το όνομα του αρχείου εικόνας -->

<form action = "login.php" method ="POST">

<div class="login-page">
  
  <div class="form">
   
    
      <input type="text" name = "user" placeholder="Username"/>
      <input type="password" name = "password" placeholder="Password"/>
      <button type ="submit" name = "login-submit"> Login</button>
	  
	  </br>
      
     
	 </form>
	 
<?php
$fullUrl = "http://$_SERVER[HTTP_HOST]$_SERVER[REQUEST_URI]";

if(strpos($fullUrl,"login=error") == true)
{
 echo '<p style ="color: red;">
		Login error !
		</p>';
 
}

?>
	 
	 
   
  </div>
</div>

</body>
</html>