<?php
header("Content-Type: application/json");
require_once __DIR__ . '/../managers/ContactManager.php';

if (!isset($_GET['query'])) {
    echo json_encode([]);
    exit;
}

$searchStr = $_GET['query'];

$manager = new ContactManager();
$results = $manager->findPersons($searchStr);

echo json_encode($results);
?>
