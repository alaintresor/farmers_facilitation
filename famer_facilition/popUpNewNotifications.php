<?php
include "connection.php";

if (isset($_POST['farmerId'])) {
    $famerId = $_POST['famerId'];

    $query = "SELECT * FROM `announcement` WHERE `famerId`='$famerId' AND `status`='sent'";
    $data = mysqli_query($connect, "$query");

    $resut = mysqli_num_rows($data);
    echo $resut;
} else echo "please provide farmer id";
