package fr.be2.gsb;

import androidx.appcompat.app.AppCompatActivity;

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
    Integer codealeatoire;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seconnecter);
        email = findViewById(R.id.main_edittext_emailSeconnecter);
        layoutverification = findViewById(R.id.main_linearlayout_verification);
        codevisiteur = findViewById(R.id.main_edittext_codevisiteur);
    }
    public void codealeatoire(View v){
        Random r = new Random();
        int min = 1000;
        int max = 9999;
        Integer a = r.nextInt((max - min) + 1) + min;
        Toast.makeText(this,"code="+a.toString(), Toast.LENGTH_SHORT).show();
        layoutverification.setVisibility(View.VISIBLE);
    }
    public void verification(View v){
        String codeAleatoireStr = codealeatoire.toString();
        String codeVerifStr = verifcode.toString();
        if (codeAleatoireStr.equals(codeVerifStr)){
            Toast.makeText(this, "Connexion réussie", Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(this, "Erreur", Toast.LENGTH_SHORT).show();
        }
    }
}