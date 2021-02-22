<?php
include "connection.php";
$farmerId = $_POST['farmerId'];
$query = "SELECT * FROM `harvest` WHERE `farmerId`='$farmerId'";
$result = mysqli_query($connect, "$query");
if (mysqli_num_rows($result)) {
    $results = array();
    while ($row = mysqli_fetch_array($result)) {
        $temp = array();
        $temp['harvest'] = $row[2];
        $temp['date'] = $row[3];
        $temp['quantity'] = $row[4];
        $temp['price'] = $row[5];

        array_push($results, $temp);
    }
    echo json_encode($results);
} else echo "No harvest found";
