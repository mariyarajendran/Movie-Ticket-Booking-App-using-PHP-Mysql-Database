<?php
 require_once('dbconnection.php');
 
 $sql = "select * from listviews";
 
 $res = mysqli_query($con,$sql);
 
 $result = array();
 
              while($row = mysqli_fetch_array($res)){
                 array_push($result,array(
				       'status'=>$row['status'],
                       'url'=>$row['image'],
					   'movie'=>$row['movie'],
					   'synopsis'=>$row['synopsis'],
					   'producer'=>$row['producer'],
					   'director'=>$row['director'],
					   'music'=>$row['music'],
					   'cast'=>$row['cast'],
					   'id'=>$row['id']
					   ));
					   
					   
                 }
 
 echo json_encode(array("result"=>$result));
 
 mysqli_close($con);