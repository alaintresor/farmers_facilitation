<?php
include "connection.php";
$farmerId = $_POST['farmerId'];
$newPassword = $_POST['newPassword'];
$currentPassword = $_POST['currentPassword'];

$securityQuery = mysqli_query($connect, "SELECT * FROM `farmer` WHERE `id`='$farmerId' AND `password`='$currentPassword'");

if (mysqli_num_rows($securityQuery)) {
    $query = "UPDATE `farmer` SET `password`='$newPassword' WHERE `id`='$farmerId';";
    $done = mysqli_query($connect, "$query");
    if ($done) {
        echo "Password Changed well";
    } else echo "Error" . mysqli_error($connect);
} else echo "Authorization fail";
