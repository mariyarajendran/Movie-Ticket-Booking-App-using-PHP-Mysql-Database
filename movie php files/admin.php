<?php
define('HOST','localhost');
define('USER','root');
define('PASS','');
define('DB','movie');
 
$con = mysqli_connect(HOST,USER,PASS,DB);
 
$sql = "select * from booking";
 
$res = mysqli_query($con,$sql);
 
$result = array();
 
while($row = mysqli_fetch_array($res)){
	array_push($result,
		array('id'=>$row['book_id'],
			'cusname'=>$row['custom_name'],
			'movname'=>$row['movie_name'],
			'showtime'=>$row['showtime'],
			'seatposition'=>$row['seatposition']
		)
	);
}
 
echo json_encode(array("result"=>$result));
 
mysqli_close($con);
 
?>