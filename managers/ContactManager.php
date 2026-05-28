<?php
require_once __DIR__ . '/../core/DbConnection.php';

class ContactManager {
    private $db;
    private $tableName = "person_contacts";

    public function __construct() {
        $dbConn = new DbConnection();
        $this->db = $dbConn->connect();
    }

    public function addPerson($fullName, $phoneNum, $origin = "mobile_app") {
        $query = "INSERT INTO " . $this->tableName . " (full_name, phone_num, origin)
                VALUES (:fullName, :phoneNum, :origin)";
        $statement = $this->db->prepare($query);
        return $statement->execute([
            ':fullName' => $fullName,
            ':phoneNum' => $phoneNum,
            ':origin' => $origin
        ]);
    }

    public function fetchAllPersons() {
        $query = "SELECT * FROM " . $this->tableName . " ORDER BY full_name ASC";
        $statement = $this->db->prepare($query);
        $statement->execute();
        return $statement->fetchAll(PDO::FETCH_ASSOC);
    }

    public function findPersons($searchQuery) {
        $query = "SELECT * FROM " . $this->tableName . "
                WHERE full_name LIKE :searchQuery OR phone_num LIKE :searchQuery
                ORDER BY full_name ASC";
        $statement = $this->db->prepare($query);
        $statement->execute([
            ':searchQuery' => '%' . $searchQuery . '%'
        ]);
        return $statement->fetchAll(PDO::FETCH_ASSOC);
    }
}
?>
