package com.example.booknumber;

import android.Manifest;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private Button buttonFetchPhone, buttonUploadServer, buttonSearchRemote;
    private EditText inputSearchQuery;
    private RecyclerView recyclerPersonsList;
    private PhonebookAdapter phoneAdapter;
    private List<PersonRecord> personRecords = new ArrayList<>();
    private SyncApiService apiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonFetchPhone = findViewById(R.id.buttonFetchPhone);
        buttonUploadServer = findViewById(R.id.buttonUploadServer);
        buttonSearchRemote = findViewById(R.id.buttonSearchRemote);
        inputSearchQuery = findViewById(R.id.inputSearchQuery);
        recyclerPersonsList = findViewById(R.id.recyclerPersonsList);

        recyclerPersonsList.setLayoutManager(new LinearLayoutManager(this));
        phoneAdapter = new PhonebookAdapter(personRecords);
        recyclerPersonsList.setAdapter(phoneAdapter);

        apiService = ApiClient.getInstance().create(SyncApiService.class);

        buttonFetchPhone.setOnClickListener(v -> checkPermissionAndFetch());
        buttonUploadServer.setOnClickListener(v -> pushDataToServer());
        buttonSearchRemote.setOnClickListener(v -> searchRemoteDb());
    }

    private void checkPermissionAndFetch() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS) == PackageManager.PERMISSION_GRANTED) {
            retrievePhoneData();
        } else {
            permissionLauncher.launch(Manifest.permission.READ_CONTACTS);
        }
    }

    private final ActivityResultLauncher<String> permissionLauncher =
            registerForActivityResult(new ActivityResultContracts.RequestPermission(), isGranted -> {
                if (isGranted) {
                    retrievePhoneData();
                } else {
                    Toast.makeText(this, "Autorisation nécessaire pour lire les contacts", Toast.LENGTH_SHORT).show();
                }
            });

    private void retrievePhoneData() {
        personRecords.clear();

        Cursor cursor = getContentResolver().query(
                ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                null, null, null,
                ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME + " ASC"
        );

        if (cursor != null) {
            while (cursor.moveToNext()) {
                String nom = cursor.getString(cursor.getColumnIndexOrThrow(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
                String numero = cursor.getString(cursor.getColumnIndexOrThrow(ContactsContract.CommonDataKinds.Phone.NUMBER));
                personRecords.add(new PersonRecord(nom, numero));
            }
            cursor.close();
        }

        phoneAdapter.refreshList(personRecords);
        Toast.makeText(this, personRecords.size() + " contacts trouvés", Toast.LENGTH_SHORT).show();
    }

    private void pushDataToServer() {
        if (personRecords.isEmpty()) {
            Toast.makeText(this, "Aucun contact à sauvegarder", Toast.LENGTH_SHORT).show();
            return;
        }

        for (PersonRecord record : personRecords) {
            apiService.addPerson(record).enqueue(new Callback<ServerResponse>() {
                @Override
                public void onResponse(@NonNull Call<ServerResponse> call, @NonNull Response<ServerResponse> response) {}

                @Override
                public void onFailure(@NonNull Call<ServerResponse> call, @NonNull Throwable t) {
                    Toast.makeText(MainActivity.this, "Problème de connexion au serveur", Toast.LENGTH_SHORT).show();
                }
            });
        }
        Toast.makeText(this, "Sauvegarde lancée...", Toast.LENGTH_SHORT).show();
    }

    private void searchRemoteDb() {
        String req = inputSearchQuery.getText().toString().trim();

        if (req.isEmpty()) {
            Toast.makeText(this, "Entrez un nom ou un numéro d'abord", Toast.LENGTH_SHORT).show();
            return;
        }

        apiService.searchRemote(req).enqueue(new Callback<List<PersonRecord>>() {
            @Override
            public void onResponse(@NonNull Call<List<PersonRecord>> call, @NonNull Response<List<PersonRecord>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    phoneAdapter.refreshList(response.body());
                    Toast.makeText(MainActivity.this, response.body().size() + " résultats", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<PersonRecord>> call, @NonNull Throwable t) {
                Toast.makeText(MainActivity.this, "Erreur lors de la recherche distante", Toast.LENGTH_SHORT).show();
            }
        });
    }
}