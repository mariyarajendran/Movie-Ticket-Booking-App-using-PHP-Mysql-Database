<?php

//Turn off Error Reporting
error_reporting(0);

	if (isset($_POST['tag']) && $_POST['tag'] != '') 
	{
		// get tag
		$tag = $_POST['tag'];
		
		// Request type is check Login
		$cusname = $_POST['cusname'];
		$id = $_POST['id'];
		$movie = $_POST['movie'];
                                      $show= $_POST['show'];
									   $seatpos= $_POST['pos'];
									  
									  $fakeids=$_POST['fakeids'];
									  $fakeshows=$_POST['fakeshows'];
                                      

		// response Array
		$response = array("tag" => $tag);
		
		// Connecting to mysql database
        $con = mysql_connect("localhost", "root", "") or die(mysql_error()); //server,user,password
 
        // Selecing database
        $db = mysql_select_db("movie") or die(mysql_error()) or die(mysql_error());
		
		if ($tag == 'log') 
		{			
			$result = mysql_query("SELECT * FROM account WHERE username = '$username' AND password ='$password'") or die(mysql_error());
			
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
                                             


                                         else if ($tag == 'create') 
		{	
			$result = mysql_query("INSERT INTO booking(custom_name,movie_id,movie_name,showtime,seatposition) VALUES('$cusname','$id','$movie','$show','$seatpos')");
			
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
		
	/*	else if($tag=='seat'){
		
		$result=mysql_query("select seatposition from booking where movie_id='3' AND showtime='Morning Show'");
		
		$res=array();
		while($row=msql_fetch_array($result)){
		
		array_push($res,
		array('movid'=>row[0],
		'movshow'=>row[1],
		'movseat'=>row[2]
		));
		
		}
		echo json_encode(array("result"=>$res));
		
		}
*/
		
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