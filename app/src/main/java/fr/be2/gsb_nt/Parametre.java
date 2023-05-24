package fr.be2.gsb_nt;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Parametre extends MainActivity{

    private static final String MON_FICHIER = "GSB_PREF_USER";

    EditText Codev;
    EditText Nom;
    EditText Prenom;
    EditText Mail;
    EditText Urlserveur;
    Button Valider;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parametre);
        Mail = findViewById(R.id.main_edittext_emailParametres);
        Codev = findViewById(R.id.main_edittext_codevisiteur);
        Nom = findViewById(R.id.main_edittext_nom);
        Urlserveur = findViewById(R.id.main_edittext_urlserveur);
        Valider = findViewById(R.id.main_btn_modifier_info_v);
        Prenom = findViewById(R.id.main_edittext_prenom);
        recupererInfos();
        AfficherVisiteur();
    }

    private void recupererInfos() {
        Codev.setText(getSharedPreferences("GSB_PREF_USER", MODE_PRIVATE).getString("CodeVisiteur",""));
        Mail.setText(getSharedPreferences("GSB_PREF_USER", MODE_PRIVATE).getString("email",""));
        Nom.setText(getSharedPreferences("GSB_PREF_USER", MODE_PRIVATE).getString("Nom",""));
        Prenom.setText(getSharedPreferences("GSB_PREF_USER", MODE_PRIVATE).getString("Prenom",""));
        Urlserveur.setText(getSharedPreferences("GSB_PREF_USER", MODE_PRIVATE).getString("UrlServeur",""));

    }

    public void Ajouter_info_v(View v){
        SharedPreferences preferences = getSharedPreferences(MON_FICHIER, MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        getSharedPreferences(MON_FICHIER, MODE_PRIVATE)
                .edit()
                .putString("Nom", Nom.getText().toString())
                .putString("Prenom", Prenom.getText().toString())
                .putString("UrlServeur", Urlserveur.getText().toString())
                .apply();

        afficherMessage("Succès!", "Informations ajoutées.");
        return;
    }
}
