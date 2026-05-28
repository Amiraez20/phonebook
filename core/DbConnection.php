<?php
class DbConnection {
    private $dbHost = "localhost";
    private $databaseName = "my_phonebook_db";
    private $dbUser = "root";
    private $dbPass = "";
    public $pdoInstance;

    public function connect() {
        $this->pdoInstance = null;

        try {
            $this->pdoInstance = new PDO(
                "mysql:host=" . $this->dbHost . ";dbname=" . $this->databaseName . ";charset=utf8mb4",
                $this->dbUser,
                $this->dbPass
            );
            $this->pdoInstance->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
        } catch (PDOException $e) {
            echo "Erreur de connexion : " . $e->getMessage();
        }

        return $this->pdoInstance;
    }
}
?>
