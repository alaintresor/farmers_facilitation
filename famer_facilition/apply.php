<?php
include "connection.php";

$farmerId = $_POST['farmerId'];
$seed = $_POST['seed'];
//get seed id
$seedId = mysqli_fetch_array(mysqli_query($connect, "SELECT `id` FROM `seeds` WHERE `name`='$seed'"));
$seedQty = $_POST['seedQty'];
$fertilizer = $_POST['fertilizer'];
// get fertilizer id
$fertilizerId = mysqli_fetch_array(mysqli_query($connect, "SELECT `id` FROM `fertilizer` WHERE `name`='$fertilizer'"));
$fertilizerQty = $_POST['fertilizerQty'];
$pesticide = $_POST['pesticide'];
// get pesticide id 
$pesticideId = mysqli_fetch_array(mysqli_query($connect, "SELECT `id` FROM `pesticide` WHERE `name`='$pesticide'"));
$pesticideQty = $_POST['pesticideQty'];
$date = date("d/m/Y");

$query = "INSERT INTO `apply` (`id`, `date`, `farmerId`, `seed_id`, `quantity`, `fertilizer_id`, `fertilizer_quantity`, `pesticide_id`, `pesticide_quantity`) VALUES (NULL, '$date', '$farmerId', '$seedId[0]', '$seedQty', '$fertilizerId[0]', '$fertilizerQty', '$pesticideId[0]', '$pesticideQty');";
$done = mysqli_query($connect, "$query");
if ($done) {
    echo "Your Application submitted";
} else echo "Error:" . mysqli_error($connect);
