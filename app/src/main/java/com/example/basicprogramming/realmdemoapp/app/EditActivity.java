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

import es.dmoral.toasty.Toasty;
import io.realm.Realm;
import io.realm.RealmResults;

public class EditActivity extends AppCompatActivity implements View.OnClickListener {

    private Toolbar toolbar;
    private FloatingActionButton fab;

    private EditText nameEditText, developerEditText,
            descriptionEditText, versionEditText;
    private Realm realm;
    Bundle bundle;
    int position;
    private LanguageList languageList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        //use bundle

        bundle = getIntent().getExtras();
        if (bundle != null)
            position = bundle.getInt("position");

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //register view
        fab = findViewById(R.id.fab);
        nameEditText = findViewById(R.id.edit_language_name_edit_text);
        developerEditText = findViewById(R.id.edit_developed_edit_text);
        descriptionEditText = findViewById(R.id.edit_description_edit_text);
        versionEditText = findViewById(R.id.edit_version_edit_text);

        fab.setOnClickListener(this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        realm = Realm.getDefaultInstance();
        RealmResults<LanguageList> realmResults = realm.where(LanguageList.class).findAll();
        languageList = realmResults.get(position);
        setLanguageData(languageList);
    }

    private void setLanguageData(LanguageList languageData) {

        nameEditText.setText(languageData.getName());
        developerEditText.setText(languageData.getDeveloped());
        descriptionEditText.setText(languageData.getDescription());
        versionEditText.setText(languageData.getLatest());

    }

    @Override
    public void onClick(View view) {

        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {

                languageList.setName(nameEditText.getText().toString().trim());
                languageList.setDeveloped(developerEditText.getText().toString().trim());
                languageList.setDescription(descriptionEditText.getText().toString().trim());
                languageList.setLatest(versionEditText.getText().toString().trim());
                Toasty.success(EditActivity.this,
                        "Update language list success",
                        Toast.LENGTH_SHORT).show();
            }
        });


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        realm.close();
    }
}
