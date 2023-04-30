package fr.be2.gsb_nt;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    Intent intent;
    String controlconnexion;
    private static final String MON_FICHIER = "GSB_PREF_USER";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        secure();
        setContentView(R.layout.activity_main);

    }

    public void click_menu(View v){
        //String MSG = "";
        switch(v.getId()){
            case R.id.main_btn_fraisauforfait:
                //MSG ="bouton frais au forfait";
                intent = new Intent(MainActivity.this, Fraisauforfait.class);
                break;
            case R.id.main_btn_fraishorsforfait:
                //MSG = "Bouton frais hors forfait";
                intent = new Intent(MainActivity.this, Fraishorsforfait.class);
                break;
            case R.id.main_btn_consulterlesfrais:
                //MSG = "Bouton consulter les frais";
                intent = new Intent(MainActivity.this, consulterlesfrais.class);
                break;
            case R.id.main_btn_parametre:
                //MSG = "Paramétre";
                intent = new Intent(MainActivity.this, Parametre.class);
                break;
            case R.id.btn_pagedéconnexion:
                intent = new Intent(MainActivity.this, seconnecter.class);
                break;
        }
        startActivity(intent);
        //Toast.makeText(this, MSG, Toast.LENGTH_SHORT).show();
    }

    public void fermer_fenetre(View v){
        this.finish();
    }
    public void secure(){
        String cvisiteur = getSharedPreferences("GSB_PREF_USER", MODE_PRIVATE).getString("CodeVisiteur","pas authentifie");
        if (cvisiteur.equals("pas authentifie")) {
            Intent intent = new Intent(MainActivity.this,seconnecter.class);
            startActivity(intent);
        }
    }
    /**
     * Affiche un message comportant un titre et un contenu,  via une boite de dialogue
     * @param titre
     * @param message
     */
    public void afficherMessage(String titre, String message){
        AlertDialog.Builder builder=new AlertDialog.Builder(this); //classe qui constuit une boite de dialogue
        builder.setCancelable(true); //pr que la boite de dialogue soit refermable
        builder.setTitle(titre);
        builder.setMessage(message);
        builder.show();

    }
}
