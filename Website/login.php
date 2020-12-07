<?PHP


if(isset($_POST['login-submit']))
{

$username = $_POST['user'];
$password = $_POST['password'];


if((strcmp($username,'root') == 0) and (strcmp ($password,'user') == 0))
{
	header("Location: Home.php");
	exit();
}


else
{

header ("Location: index1.php?login=error");

}

}






?>