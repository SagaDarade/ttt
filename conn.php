<?php

	$conn = mysqli_connect("localhost",		//server name
							"root",			//user
							"",				//password
							"retrofit1");	//database name

	if(mysqli_connect_errno()) 
		echo"Connection failed. ".mysqli_connect_errno();
?>