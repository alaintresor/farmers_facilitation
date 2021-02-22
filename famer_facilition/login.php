<?php
include "connection.php";
$username = $_POST['username'];
$password = $_POST['password'];
$query = "SELECT * FROM `farmer` WHERE `username`='$username' AND `password`='$password'";
$result = mysqli_query($connect, "$query");
if (mysqli_num_rows($result)) {
    $userInfor = mysqli_fetch_array($result);
    $userId = $userInfor[0];
    echo $userId;
} else {
    echo "Wrong Username or Password";
}
