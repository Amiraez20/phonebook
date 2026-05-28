<?php
header("Content-Type: application/json");
require_once __DIR__ . '/../managers/ContactManager.php';

$manager = new ContactManager();
$personList = $manager->fetchAllPersons();

echo json_encode($personList);
?>
