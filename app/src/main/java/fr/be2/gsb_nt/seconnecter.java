package fr.be2.gsb_nt;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.Random;

public class seconnecter extends AppCompatActivity {
    EditText codevisiteur;
    EditText email;
    LinearLayout layoutverification;
    EditText verifcode;
    Integer codeAleatoire;
    Intent intent;
    private static final String MON_FICHIER = "GSB_PREF_USER";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seconnecter);
        email = findViewById(R.id.main_edittext_emailSeconnecter);
        layoutverification = findViewById(R.id.main_linearlayout_verification);
        codevisiteur = findViewById(R.id.main_edittext_codevisiteur);
        verifcode = findViewById(R.id.main_edittext_saisiecodealeatoire);
    }
    public void codealeatoire(View v){
        Random r = new Random();
        int min = 1000;
        int max = 9999;
        codeAleatoire = r.nextInt((max - min) + 1) + min;
        Toast.makeText(this,"code="+codeAleatoire.toString(), Toast.LENGTH_SHORT).show();
        layoutverification.setVisibility(View.VISIBLE);

    }
    public void verification(View v){
        String codeAleatoireStr = codeAleatoire.toString();
        String codeVerifStr = verifcode.getText().toString();
        if (codeAleatoireStr.equals(codeVerifStr)){
            Toast.makeText(this, "Connexion réussie", Toast.LENGTH_SHORT).show();
            getSharedPreferences(MON_FICHIER, MODE_PRIVATE)
                    .edit()
                    .putString("CodeVisiteur", codevisiteur.getText().toString())
                    .putString("email", email.getText().toString())
                    .apply();
            Intent intent = new Intent(seconnecter.this,MainActivity.class);
            startActivity(intent);
        }
        else{
            Toast.makeText(this, "Erreur", Toast.LENGTH_SHORT).show();
        }
    }
}
