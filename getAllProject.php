<?php

require "conn.php";

$qry = "SELECT * FROM project;";

$result = mysqli_query($conn,$qry);

$rows = array();

	while($r = mysqli_fetch_assoc($result))	{
		$rows[] = $r;
	}

	echo json_encode($rows);
mysqli_close($conn);

?>
