<?php

//Turn off Error Reporting
error_reporting(0);

	if (isset($_POST['tag']) && $_POST['tag'] != '') 
	{
		// get tag
		$tag = $_POST['tag'];
		
		// Request type is check Login
		$username = $_POST['username'];
		$password = $_POST['password'];
		


		// response Array
		$response = array("tag" => $tag);
		
		// Connecting to mysql database
        $con = mysql_connect("localhost", "root", "") or die(mysql_error()); //server,user,password
 
        // Selecing database
        $db = mysql_select_db("movielogin") or die(mysql_error()) or die(mysql_error());
		
		if ($tag == 'logins') 
		{			
			$result = mysql_query("SELECT * FROM login WHERE username = '$username' AND password ='$password'") or die(mysql_error());
			
			// check for result 
			$no_of_rows = mysql_num_rows($result);
			
			 if ($no_of_rows > 0) 
			 {
				 $response["result"]="Valid User";
				 echo json_encode($response);
			 } 
			 else 
			 {
				// user not found
				$response["result"]="Username or Password Is Wrong!!!";
				echo json_encode($response);
			 }
				 
		}
		
		else 
		{
			echo "Invalid Request";
		}	
	} 
	else 
	{
    echo "Access Denied";
	}

?>