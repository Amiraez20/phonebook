<?php
header("Content-Type: application/json");
require_once __DIR__ . '/../managers/ContactManager.php';

if ($_SERVER['REQUEST_METHOD'] === 'POST') {
    $payload = json_decode(file_get_contents("php://input"), true);

    if (!isset($payload['full_name']) || !isset($payload['phone_num'])) {
        echo json_encode([
            "success" => false,
            "message" => "Veuillez fournir le nom et le numéro."
        ]);
        exit;
    }

    $manager = new ContactManager();
    $isInserted = $manager->addPerson($payload['full_name'], $payload['phone_num'], "mobile_app");

    echo json_encode([
        "success" => $isInserted,
        "message" => $isInserted ? "Personne enregistrée avec succès." : "Échec de l'enregistrement."
    ]);
}
?>
