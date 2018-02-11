<?php
define('HOST','localhost');
define('USER','root');
define('PASS','');
define('DB','movie');
 
$con = mysqli_connect(HOST,USER,PASS,DB);
 
$sql = "select * from  coming_movies";
 
$res = mysqli_query($con,$sql);
 
$result = array();
 
while($row = mysqli_fetch_array($res)){
array_push($result,
array('date'=>$row[1],
'cast'=>$row[2],
'music'=>$row[3],
'genre'=>$row[4],
'director'=>$row[5],
'producer'=>$row[6],
'synopsis'=>$row[7],
'movie'=>$row[8]
));
}
 
echo json_encode(array("upresult"=>$result));
 
mysqli_close($con);
 
?>