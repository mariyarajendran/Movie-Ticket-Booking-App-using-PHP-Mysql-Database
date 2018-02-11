<?php
 
 if($_SERVER['REQUEST_METHOD']=='POST'){
 
 $image = $_POST['image'];
  $date = $_POST['date'];
   $cast = $_POST['cast'];
    $music = $_POST['music'];
	 $genre = $_POST['genre'];
	  $director= $_POST['director'];
	   $producer = $_POST['producer'];
	    $synopsis = $_POST['synopsis'];
		 $movie = $_POST['movie'];
 
 require_once('dbconnection.php');
 
 $sql ="SELECT id FROM listviews ORDER BY id ASC";
 
 $res = mysqli_query($con,$sql);
                                            /*cast,music,genre,director,producer,synopsis,movie,*/ /*'$cast','$music','$genre','$director','$producer','$synopsis','$movie',*/
 $id=0;
 
 while($row = mysqli_fetch_array($res)){
 $id = $row['id'];
 }
 
 $path = "uploads/$id.png";
 
 $actualpath = "http://10.0.2.2/MovieLogin/$path";
 
 $sql = "UPDATE  listviews SET image='$actualpath' WHERE id=$id";
 
 if(mysqli_query($con,$sql)){
 file_put_contents($path,base64_decode($image));
 echo "succesfully uploaded";
 }
 
 mysqli_close($con);
 }else{
 echo "Error";
 }