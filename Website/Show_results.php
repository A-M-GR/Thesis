<?php

include_once 'Connection.php';

?>

<html>

<head>
<link rel ="stylesheet" href ="index1_styles.css"/>  <!-- Το όνομα του αρχείου .css -->

</head>
<body background =" basic.JPG">  <!-- Το όνομα του αρχείου εικόνας -->


<div class="login-page">
  <div class="form">
  <h1> Users</h1>
  
  <br>
  <br>

<?php


$sql ="SELECT * FROM main_table;";
$result = mysqli_query($conn,$sql);
$counter =1;

while ($row = mysqli_fetch_assoc($result))
{
	
	echo '<ol>';
	
	echo "<li value = $counter>".$row['Username']." </li>";  
	
	$counter++;
     
	 echo '</ol>';
}



?>

</div>
</div>
</body>

</html>
