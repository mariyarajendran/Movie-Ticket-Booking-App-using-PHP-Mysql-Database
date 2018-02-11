<?php
define('HOST','localhost');
define('USER','root');
define('PASS','');
define('DB','movie');
 
$con = mysqli_connect(HOST,USER,PASS,DB);
 
$sql = "select * from listviews";
 
$res = mysqli_query($con,$sql);
 
$result = array();
 
while($row = mysqli_fetch_array($res)){
array_push($result,
array('id'=>$row['id'],
'date'=>$row['date'],
'cast'=>$row['cast'],
'music'=>$row['music'],
'genre'=>$row['genre'],
'director'=>$row['director'],
'producer'=>$row['producer'],
'synopsis'=>$row['synopsis'],
'movie'=>$row['movie'],
'image'=>$row['image']
));
}
 
echo json_encode(array("result"=>$result));
 
mysqli_close($con);
 
?>