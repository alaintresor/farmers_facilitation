<?php
include "connection.php";
$farmerId = $_POST['farmerId'];
$newUsername = $_POST['newUsername'];
$oldUsername = $_POST['oldUsername'];
$password = $_POST['password'];

$securityQuery = mysqli_query($connect, "SELECT * FROM `farmer` WHERE `id`='$farmerId' AND `username`='$oldUsername' AND `password`='$password'");

if (mysqli_num_rows($securityQuery)) {
    $query = "UPDATE `farmer` SET `username`='$newUsername' WHERE `id`='$farmerId';";
    $done = mysqli_query($connect, "$query");
    if ($done) {
        echo "Username Changed well";
    } else echo "Error" . mysqli_error($connect);
} else echo "Authorization fail";
