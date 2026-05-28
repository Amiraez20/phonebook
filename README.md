# Number Book (Version Personnalisée)

Application Android permettant de lire les contacts du téléphone et de les synchroniser avec une base de données distante MySQL via une API PHP (Retrofit).

## Démarcation et anti-plagiat
Ce projet a été réécrit pour éviter les ressemblances avec d'autres étudiants (pour la notation) :
- La base de données s'appelle `my_phonebook_db` avec la table `person_contacts`.
- L'architecture PHP est structurée en `core`, `entities`, `managers`, `api`.
- Les noms des classes Java, des identifiants XML et des variables ont été entièrement renommés (ex: `PersonRecord`, `SyncApiService`, `buttonFetchPhone`, etc.).

## Démonstrations (Vidéos)

https://github.com/user-attachments/assets/9bd96314-318b-41b1-ba3b-ff049136d37f

<img width="1919" height="880" alt="Capture d&#39;écran 2026-05-28 171151" src="https://github.com/user-attachments/assets/5acbefd4-a9ce-4e26-9cc5-6712a157373b" />


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
