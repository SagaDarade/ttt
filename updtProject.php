<?php

require_once('conn.php');

$pid = 3;
$sname = "Garnet Metal App";
$rollno="120";
$grade="2020/01/31";

$qry = "UPDATE project SET sname='$sname',
							rollno='$rollno',
							submit='$date'
							WHERE pid=$pid;";

$result = mysqli_query($conn,$qry);

	if($result){
		echo ("Record Updated");
	}
	else{
		echo ("Record could not updated.");
	}

?>