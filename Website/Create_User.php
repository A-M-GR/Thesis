<?php

include_once 'Connection.php';

?>


<html>

<head>
<link rel ="stylesheet" href ="index1_styles.css"/> <!-- Name of the .css file -->
</head>



<body background = basic.JPG> <!-- The name of the image -->

<form action ="" method ="POST">



<div class="login-page">
  <div class="form">
  
 <input type ="text" name ="Account_name" placeholder ="Username"/> 
 <input type ="password" name ="First_pass" placeholder ="Password"/>
 <input type = "password" name ="Second_pass" placeholder ="Confirm Password">
 <button type ="submit" name = "create_user_submit"> Create_User </button>

<br>
<br>


<?php


if( !isset($_POST['create_user_submit']))
{
exit();
}


if((!empty($_POST['Account_name'])) && (!empty($_POST['First_pass'])) && (!empty($_POST['Second_pass'])))
{
$Username = $_POST['Account_name'];
$Password1 = $_POST['First_pass'];
$Password2 = $_POST['Second_pass'];


if(strcmp($Password1,$Password2)!=0)
{
echo '<p style = "color:red">Passwords Are Not The Same</p>';
exit();
}


$query = "SELECT Username FROM main_table WHERE Username='$Username';";

$result1= mysqli_query($conn,$query);
$resultCheck = mysqli_num_rows($result1);



if ($resultCheck ==0) 
{

$Password1 = password_hash($Password1,PASSWORD_DEFAULT); // hash function with salt 
$sql ="INSERT INTO main_table(Username,password) VALUES ('$Username','$Password1') ;";
$result = mysqli_query($conn,$sql);


header("Location: Home.php?Create_User=success");
}

else 
{
echo '<p style ="color :red"> User already exists </p>';
}

}

else 
{
	echo '<p style ="color :red"> No Data Provided </p>';
}


?>

</div>
</div>
</form>
</body>


</html>



