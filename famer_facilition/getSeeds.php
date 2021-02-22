<?php
include "connection.php";
$query = "SELECT * FROM `seeds`";
$result = mysqli_query($connect, "$query");
if (mysqli_num_rows($result)) {
    $results = array();
    while ($row = mysqli_fetch_array($result)) {
        $temp = array();
        $temp['id'] = $row[0];
        $temp['name'] = $row[1];
        array_push($results, $temp);
    }
    echo json_encode($results);
} else echo "No seeds found";
