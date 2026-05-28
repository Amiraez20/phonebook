# Number Book (Version Personnalisée)

Application Android permettant de lire les contacts du téléphone et de les synchroniser avec une base de données distante MySQL via une API PHP (Retrofit).

## Démarcation et anti-plagiat
Ce projet a été réécrit pour éviter les ressemblances avec d'autres étudiants (pour la notation) :
- La base de données s'appelle `my_phonebook_db` avec la table `person_contacts`.
- L'architecture PHP est structurée en `core`, `entities`, `managers`, `api`.
- Les noms des classes Java, des identifiants XML et des variables ont été entièrement renommés (ex: `PersonRecord`, `SyncApiService`, `buttonFetchPhone`, etc.).

## Démonstrations (Vidéos)

*Insérer ici les liens vers vos vidéos de test / démonstration :*

### 1. Test du Chargement des Contacts
> [Insérez votre lien vidéo ici]()

### 2. Test de la Synchronisation Serveur
> [Insérez votre lien vidéo ici]()

### 3. Test de la Recherche Distante
> [Insérez votre lien vidéo ici]()

## Installation du Backend (XAMPP)
1. Démarrer Apache et MySQL dans XAMPP.
2. Copier le dossier `Booknumber` généré dans `c:\xampp\htdocs\`.
3. Importer le fichier `init_db.sql` dans `phpMyAdmin` pour créer la base `my_phonebook_db` et la table `person_contacts`.
4. Si vous testez avec un téléphone physique branché en USB ou via WiFi (et non l'émulateur Android Studio), remplacez `10.0.2.2` par l'IP locale de votre PC dans le fichier `app/src/main/java/com/example/booknumber/ApiClient.java`.

## Architecture du projet Android
- `PersonRecord.java` : Modèle de données.
- `ApiClient.java` : Instance Retrofit.
- `SyncApiService.java` : Interface des appels réseau (GET/POST).
- `PhonebookAdapter.java` : Adaptateur pour la liste dynamique.
- `MainActivity.java` : Activité principale gérant les interactions et les permissions.
