<?php

//Turn off Error Reporting
error_reporting(0);

	if (isset($_POST['tag']) && $_POST['tag'] != '') 
	{
		// get tag
		
		$tag = $_POST['tag'];
		
		// Request type is check Login
		
		$cusname=$_POST['cusname'];
		$id = $_POST['id'];
		$show = $_POST['show'];
		

// response Array
		$response = array("tag" => $tag);
		
		// Connecting to mysql database
        $con = mysql_connect("localhost", "root", "") or die(mysql_error()); //server,user,password
 
        // Selecing database
        $db = mysql_select_db("movie") or die(mysql_error()) or die(mysql_error());
                           

          if($tag=='next'){	

			 
			$res = mysql_query("INSERT INTO temp (movie_id,showtime) values ('$id','$show')") or die(mysql_error());
			
			   if ($res) 
			 {
				 $response["result"]="Inserted";
				 echo json_encode($response);
			 } 
			 else 
			 {
				// unable to insert
				$response["result"]="Unable To Insert";
				echo json_encode($response);
			 }
			
			
			}
			
			
			
			
			
			
		
			
	} 
	else 
	{
    echo "Access Denied";
	}

?>