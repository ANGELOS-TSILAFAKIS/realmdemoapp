package com.example.basicprogramming.realmdemoapp.app;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.basicprogramming.realmdemoapp.R;
import com.example.basicprogramming.realmdemoapp.model.LanguageList;

import java.util.UUID;

import es.dmoral.toasty.Toasty;
import io.realm.Realm;
import io.realm.RealmAsyncTask;

public class AddActivity extends AppCompatActivity implements View.OnClickListener {

    private Toolbar toolbar;
    private FloatingActionButton fab;
    private EditText language_name, language_developer, language_description, language_version;
    private Realm realm;
    private RealmAsyncTask realmAsyncTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //register view
        fab = findViewById(R.id.fab);
        language_name = findViewById(R.id.language_name_edit_text);
        language_developer = findViewById(R.id.developed_edit_text);
        language_description = findViewById(R.id.description_edit_text);
        language_version = findViewById(R.id.version_edit_text);

        fab.setOnClickListener(this);
        realm = Realm.getDefaultInstance();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void addLanguageList() {

        String uuid = UUID.randomUUID().toString();
        String lan_name = language_name.getText().toString().trim();
        String lan_developer = language_developer.getText().toString().trim();
        String lan_description = language_description.getText().toString().trim();
        String lan_version = language_version.getText().toString().trim();

        // validation here

        if (lan_name.isEmpty()) {
            Toasty.info(AddActivity.this,
                    "Language name is require",
                    Toast.LENGTH_SHORT).show();
            return;
        }

        if (lan_developer.isEmpty()) {
            Toasty.info(AddActivity.this,
                    "Developer name is require",
                    Toast.LENGTH_SHORT).show();
            return;
        }

        if (lan_description.isEmpty()) {
            Toasty.info(AddActivity.this,
                    "Language description is require",
                    Toast.LENGTH_SHORT).show();
            return;
        }

        if (lan_version.isEmpty()) {
            Toasty.info(AddActivity.this,
                    "Language version is require",
                    Toast.LENGTH_SHORT).show();
            return;
        }

        realmAsyncTask = realm.executeTransactionAsync(

                new Realm.Transaction() {
                    @Override
                    public void execute(Realm realm) {

                        LanguageList languageList = realm.createObject(LanguageList.class, uuid);

                        languageList.setName(lan_name);
                        languageList.setDeveloped(lan_developer);
                        languageList.setDescription(lan_description);
                        languageList.setLatest(lan_version);

                    }
                },
                new Realm.Transaction.OnSuccess() {
                    @Override
                    public void onSuccess() {

                        Toasty.info(AddActivity.this,
                                "Language Insert In Realm Database",
                                Toast.LENGTH_SHORT).show();

                        language_name.setText("");
                        language_developer.setText("");
                        language_description.setText("");
                        language_version.setText("");

                    }
                }
                ,
                new Realm.Transaction.OnError() {
                    @Override
                    public void onError(Throwable error) {

                        Toasty.info(AddActivity.this,
                                "Error in Realm Database",
                                Toast.LENGTH_SHORT).show();
                    }
                }
        );

    }

    @Override
    public void onClick(View v) {
        addLanguageList();
    }

    @Override
    protected void onStop() {
        super.onStop();

        if (realmAsyncTask != null && !realmAsyncTask.isCancelled()) {
            realmAsyncTask.cancel();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        realm.close();
    }
}
