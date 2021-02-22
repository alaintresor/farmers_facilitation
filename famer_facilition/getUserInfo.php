<?php
include "connection.php";
$farmerId = $_POST['farmerId'];
$query = "SELECT * FROM `farmer` WHERE `id`='$farmerId'";
$result = mysqli_query($connect, "$query");

$results = array();
while ($row = mysqli_fetch_array($result)) {
    $temp = array();
    $temp['farmerName'] = "$row[1] $row[2]";
    $temp['district'] = $row[6];
    $temp['sector'] = $row[7];
    $temp['village'] = $row[8];
    $temp['cell'] = $row[9];
    $temp['phone'] = $row[11];

    array_push($results, $temp);
    // echo $row[1];
}
echo json_encode($results);
