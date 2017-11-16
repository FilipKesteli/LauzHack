package com.kesteli.filip.lauzhack.login;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.kesteli.filip.lauzhack.MainActivity;
import com.kesteli.filip.lauzhack.POJOMain;
import com.kesteli.filip.lauzhack.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SignUpActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private EditText etInputEmail, etInputPassword, etIme, etPrezime;
    private Button btnSignIn, btnSignUp, btnResetPassword;
    private ProgressBar progressBar;
    private Spinner spinner;

    private SharedPreferences sharedPreferences;
    private Map<String, Integer> instrukcijeMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        Toast.makeText(SignUpActivity.this, "Ovo je SignUpActivity", Toast.LENGTH_SHORT).show();

        initViews();
        setupListeners();
        setupSpinner();
    }

    private void initViews() {
        btnSignUp = (Button) findViewById(R.id.sign_up_button);
        btnSignIn = (Button) findViewById(R.id.sign_in_button);
        etInputEmail = (EditText) findViewById(R.id.email);
        etInputPassword = (EditText) findViewById(R.id.password);
        etIme = (EditText) findViewById(R.id.etIme);
        etPrezime = (EditText) findViewById(R.id.etPrezime);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        btnResetPassword = (Button) findViewById(R.id.btn_reset_password);
        spinner = (Spinner) findViewById(R.id.spinner);
    }

    private void setupListeners() {
        btnResetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SignUpActivity.this, ResetPasswordActivity.class));
            }
        });

        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignUpActivity.this, ResetPasswordActivity.class);
                startActivity(intent);
            }
        });

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SignUpActivity.this, MainActivity.class));
                sharedPreferences = getSharedPreferences(POJOMain.KEY_MOJ_LOGIN_SHARED_PREFERENCES, Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                String ime = etIme.getText().toString();
                String prezime = etPrezime.getText().toString();
                String email = etInputEmail.getText().toString().trim();
                String password = etInputPassword.getText().toString().trim();
                editor.putString(POJOMain.KEY_ID_JA, ime);
                editor.putString(POJOMain.KEY_IME_JA, ime);
                editor.putString(POJOMain.KEY_PREZIME_JA, prezime);
                editor.putString(POJOMain.KEY_EMAIL_JA, email);
                editor.commit();

                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(getApplicationContext(), "Enter email address!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(password)) {
                    Toast.makeText(getApplicationContext(), "Enter password!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (password.length() < 6) {
                    Toast.makeText(getApplicationContext(), "Password too short, enter minimum 6 characters!", Toast.LENGTH_SHORT).show();
                    return;
                }

                progressBar.setVisibility(View.VISIBLE);
                //create user
                /*auth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(SignUpActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                Toast.makeText(SignUpActivity.this, "createUserWithEmail:onComplete:" + task.isSuccessful(), Toast.LENGTH_SHORT).show();
                                progressBar.setVisibility(View.GONE);
                                // If sign in fails, display a message to the user. If sign in succeeds
                                // the auth state listener will be notified and logic to handle the
                                // signed in user can be handled in the listener.
                                if (!task.isSuccessful()) {
                                    Toast.makeText(SignUpActivity.this, "Authentication failed." + task.getException(),
                                            Toast.LENGTH_SHORT).show();
                                } else {
                                    startActivity(new Intent(SignUpActivity.this, MainActivity.class));
                                    finish();
                                }
                            }
                        });*/
            }
        });
    }

    private void setupSpinner() {
        // Spinner click listener
        spinner.setOnItemSelectedListener(this);

        // Spinner Drop down elements
        List<String> categories = new ArrayList<String>();
        categories.add("Fizika");
        categories.add("Matematika");
        categories.add("Kemija");
        categories.add("Android Instrukcije");
        categories.add("Web Instrucije");

        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        spinner.setAdapter(dataAdapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        progressBar.setVisibility(View.GONE);
    }

    /**
     * Metoda koja je zapravo listener za spinner
     */
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        // On selecting a spinner item
        String item = parent.getItemAtPosition(position).toString();
        // Showing selected spinner item
        sharedPreferences = getSharedPreferences(POJOMain.KEY_MOJ_LOGIN_SHARED_PREFERENCES, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(POJOMain.KEY_INSTRUKCIJE_JA, item);
        editor.commit();
        Toast.makeText(parent.getContext(), "Selected: " + item, Toast.LENGTH_LONG).show();
    }

    /**
     * Takoder spinner metoda
     */
    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}





