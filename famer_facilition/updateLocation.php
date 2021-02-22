<?php
include "connection.php";
$farmerId = $_POST['farmerId'];
$district = $_POST['district'];
$sector = $_POST['sector'];
$village = $_POST['village'];
$cell = $_POST['cell'];

$query = "UPDATE `farmer` SET `district`='$district',`sector`='$sector',`village`='$village',`cell`='$cell' WHERE `id`='$farmerId';";
$done = mysqli_query($connect, "$query");
if ($done) {
    echo "Data updated well done";
} else echo "Error" . mysqli_error($connect);
