package fr.be2.gsb;

import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.OnNewIntentProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Intent intent;
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
            case R.id.btn_pageconnexion:
                intent = new Intent(MainActivity.this, seconnecter.class);
                break;
        }
        startActivity(intent);
        //Toast.makeText(this, MSG, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
    }
    public void fermer_fenetre(View v){
        this.finish();
    }
}
