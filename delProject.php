<?php

require_once('conn.php');

$pid = 4;
$pname = "Garnet Metal App";
$duration="120";
$date="2020/01/31";

$qry = "DELETE FROM student WHERE pid=$pid;";

$result = mysqli_query($conn,$qry);

	if($result){
		echo ("Record Deleted");
	}
	else{
		echo ("Record could not delete.");
	}

?>