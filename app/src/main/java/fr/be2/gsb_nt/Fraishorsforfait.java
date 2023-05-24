package fr.be2.gsb_nt;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.Calendar;

public class Fraishorsforfait extends MainActivity {

    EditText libelle;
    Button btnAjouter;
    EditText montant;
    TextView date;
    SQLHelper database;
    DatePickerDialog picker;
    Calendar calendrier = Calendar.getInstance(); //on declare une classe d'un calendrier qui existe deja
    int jj=calendrier.get(Calendar.DAY_OF_MONTH);
    int mm=calendrier.get(Calendar.MONTH);
    int aaaa=calendrier.get(Calendar.YEAR);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fraishorsforfait);
        AfficherVisiteur();

        database = new SQLHelper(this);
        libelle = findViewById(R.id.main_edittext_libelle);
        montant = findViewById(R.id.main_edittext_montant);
        date = findViewById(R.id.textview_dateFHF);
        btnAjouter = findViewById(R.id.main_btn_ajouterFHF);
    }

    public void AjouterFHF(View view){
        switch (view.getId()){
            case R.id.main_btn_ajouterFHF:

                if(montant.getText().toString().trim().length() == 0  || libelle.getText().toString().trim().length() == 0  || date.length()==0) { //test si les champs
                    // libelle, montant et date sont renseignes
                    afficherMessage("Erreur!", "Champs vides.");
                    return;
                } else{
                    String libelleHF= libelle.getText().toString();
                    double montantHF = Double.parseDouble(montant.getText().toString());//conversion d'un text
                    // en string et d'un string en double
                    String dateHF=date.getText().toString();
                    database.insertData("Hors forfait", null, dateHF, montantHF, libelleHF);
                    afficherMessage("Succès!", "Informations ajoutées.");
                    return;
                }
        }
    }




    public void ShowCal(View vv){
        picker = new DatePickerDialog(Fraishorsforfait.this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        date.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                        //date qu'on recupere une fois qu'on a selectionne
                    }
                }, aaaa, mm, jj);//date qui s'affiche sur le calendrier
        picker.show();
    }
}