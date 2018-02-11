<?php

//Turn off Error Reporting
error_reporting(0);

	if (isset($_POST['tags']) && $_POST['tags'] != '') 
	{
		// get tag
		$tag = $_POST['tags'];
		
		// Request type is check Login
		//$deleteid = $_POST['deleteid'];
		

		// response Array
		$response = array("tags" => $tag);
		
		// Connecting to mysql database
        $con = mysql_connect("localhost", "root", "") or die(mysql_error()); //server,user,password
 
        // Selecing database
        $db = mysql_select_db("movie") or die(mysql_error()) or die(mysql_error());
		
		//$response["result"]="$tag";
			//	echo json_encode($response);
			
			
			if($tag == 'deletee'){
		
		
					
			$result = mysql_query("DELETE   FROM temp") or die(mysql_error());
			
		$response["result"]="$tag";
				echo json_encode($response);
		
		
			               
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