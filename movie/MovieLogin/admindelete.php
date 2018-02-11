<?php

//Turn off Error Reporting
error_reporting(0);

	if (isset($_POST['tag']) && $_POST['tag'] != '') 
	{
		// get tag
		$tag = $_POST['tag'];
		
		// Request type is check Login
		$deleteid = $_POST['deleteid'];
		

		// response Array
		$response = array("tag" => $tag);
		
		// Connecting to mysql database
        $con = mysql_connect("localhost", "root", "") or die(mysql_error()); //server,user,password
 
        // Selecing database
        $db = mysql_select_db("movie") or die(mysql_error()) or die(mysql_error());
		
		if ($tag == 'delete') 
		{			
		
		
			$result = mysql_query("DELETE FROM booking WHERE book_id = '$deleteid'") or die(mysql_error());
			
			if($result){
			$response["result"]="Record Deleted";
				echo json_encode($response);}
				
				else{
					$response["result"]="unable to Deleted";
				echo json_encode($response);
				}
			
		}
                                             


                                         else if ($tag == 'create') 
		{	
			$result = mysql_query("INSERT INTO account(username,password,name,rank,department,phone_number) VALUES('$username','$password','$name','$rank','$department','$phone_number')");
			
			 if ($result) 
			 {
				 $response["result"]="Successfully Inserted.";
				 echo json_encode($response);
			 } 
			 else 
			 {
				// unable to insert
				$response["result"]="Unable To Insert";
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