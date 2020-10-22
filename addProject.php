<?php

	require_once('conn.php');

	$pname = $_GET['p_name'];
	$duration = $_GET['p_dur'];
	$date = $_GET['p_date'];

	// $pname = "GYM 2";
	// $duration="180";
	// $date="2020/01/25";

	$qry = "SELECT pid FROM project WHERE pname = '$pname';";

	$result = mysqli_query($conn,$qry);

	if(mysqli_num_rows($result) > 0){
		$status="exists";
	}
	else{
		$qry = "INSERT INTO project(pname,duration,submit) VALUES ('$pname','$duration','$date');";	
		if(mysqli_query($conn,$qry)){
			$status="ok";
		}
		else{
			$status="failed";
		}
	}
	echo json_encode(array("response"=>$status));

	mysqli_close($conn);
?>