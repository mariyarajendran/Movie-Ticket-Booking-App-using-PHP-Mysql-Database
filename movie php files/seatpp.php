<?php

session_start();

define('HOST','localhost');
define('USER','root');
define('PASS','');
define('DB','movie');
 
$con = mysqli_connect(HOST,USER,PASS,DB);


					 $response['error'] = 1;	
	session_start();
		
		$fid=$_SESSION['id'];
		$fshow=$_SESSION['show'];
					 


$sql = "select seatposition from booking where movie_id='3' AND showtime='Morning Show'";

$res = mysqli_query($con,$sql);
 
 
 	 
							 
 
                             $result = array();
							 $response['selected_seat'] = array();
                      while($row = mysqli_fetch_array($res)) {
                          $mark=explode(':', $row['seatposition']);
						  
                           foreach($mark as $out) {
							
							 $result['selected'] = $out;
							 
							  array_push($response['selected_seat'],$result);
				
							 }
							 
							
							 
							 
							/* array_push($result,
                array('rollno'=>$row[1]));*/
							 
							 
							}
                      
                                       
							
							  $response['error'] = "0";
							  echo json_encode($response); 
						
							   
							
mysqli_close($con);
 
?>