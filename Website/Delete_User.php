<?php

include_once 'Connection.php';

?>

<html>

<head>
<link rel ="stylesheet" href ="index1_styles.css"/> <!-- Το όνομα του αρχείου .css -->
</head>

<body background = basic.JPG>  <!-- Το όνομα του αρχείου εικόνας -->

<form action ="" method ="POST">


<div class="login-page">

  <div class="form">

<input type ="Text" name ="username" placeholder ="Username"/>

<button type ="submit" name ="button_delete"> Delete_User </button>

<br>
<br>

<?php


if( !isset($_POST['button_delete']))
{
exit();
}

if((!empty($_POST['username'])))
{

$Username = $_POST['username'];


$query = "SELECT Username FROM main_table WHERE Username='$Username';";

$result1= mysqli_query($conn,$query);
$resultCheck = mysqli_num_rows($result1);


if($resultCheck >0)
{

$sql ="DELETE FROM main_table WHERE Username = '$Username';";
mysqli_query($conn,$sql);


header("Location: Home.php?Delete_User=success");
}

else
{
echo ' <p style = "color:red"> Username does not exist </p>';
}
}

else
{
	echo ' <p style = "color:red"> No Username Provided </p>';
}

?>
   
  </div>
</div>

</form>

</body>
</html>