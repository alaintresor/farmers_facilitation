<?php
include "connection.php";
$farmerId = $_POST['farmerId'];
$phone = $_POST['phone'];

$query = "UPDATE `farmer` SET `phone`='$phone' WHERE `id`='$farmerId';";
$done = mysqli_query($connect, "$query");
if ($done) {
    echo "Data updated well done";
} else echo "Error" . mysqli_error($connect);
