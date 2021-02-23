<?php
include "connection.php";

if (isset($_POST['farmerId'])) {
    $famerId = $_POST['famerId'];

    $query = "SELECT * FROM `announcement` WHERE `famerId`='$famerId'";
    $data = mysqli_query($connect, "$query");

    $results = array();
    while ($row = mysqli_fetch_array($result)) {
        $temp = array();
        $temp['id'] = $row[0];
        $temp['date'] = $row[2];
        $temp['subject'] = $row[3];
        $temp['message'] = $row[4];
        $temp['status'] = $row[5];
        array_push($results, $temp);
    }
    echo json_encode($results);
} else echo "please provide farmer id";
