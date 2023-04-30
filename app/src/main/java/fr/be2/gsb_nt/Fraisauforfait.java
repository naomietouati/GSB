package fr.be2.gsb_nt;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.Calendar;

public class Fraisauforfait extends MainActivity {
    EditText txtQte1;
    Spinner listeforfait1;
    Button btnAjouter;
    String[] valeurs;
    TextView mSomme;
    TextView maDate;
    SQLHelper database; //variable qui permet d'acceder à la classe sqlhelperfraisforfait
    DatePickerDialog picker;
    Calendar calendrier = Calendar.getInstance(); //on declare une classe d'un calendrier qui existe deja
    int jj=calendrier.get(Calendar.DAY_OF_MONTH);
    int mm=calendrier.get(Calendar.MONTH);
    int aaaa=calendrier.get(Calendar.YEAR);
    double montantCalcule;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fraisauforfait);
        //on initialise la variable database en instanciant la classe SQLHelper
        database = new SQLHelper(this);
        txtQte1 = findViewById(R.id.main_edittext_quantiteFraisauforfait);
        listeforfait1 = findViewById(R.id.main_spinner_types);
        mSomme = findViewById(R.id.textview_textsomme);
        valeurs = getResources().getStringArray(R.array.MesValeurs);
        btnAjouter = findViewById(R.id.main_btn_ajouterFrais);
        maDate = findViewById(R.id.textview_textsomme);

        txtQte1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                // This is where we'll check the user input
                Integer q1 = Integer.parseInt(String.valueOf("0" + txtQte1.getText()));
                //String f1 = listeForfait1.getSelectedItem().toString();
                int posF1 = listeforfait1.getSelectedItemPosition();
                Float s1 = q1 * Float.parseFloat(valeurs[posF1]);
                mSomme.setText(s1.toString());
            }
        });
    }

        /**
         * Effectue l'action du bouton ajouter, cad vérifie que les champs soient renseiegnés et valides
         * et enregistre le frais dans la bdd en affichant un message de confirmation
         *
         * @param v
         */
        public void MonClick(View v){
            switch (v.getId()) {
                case R.id.main_btn_ajouterFrais:
                    if (txtQte1.getText().toString().trim().length() == 0 || listeforfait1.getSelectedItem().toString().length() == 0
                            || maDate.getText().toString().trim().length() == 0) {
                        //teste si le champ quantite est renseigné ou si le champ type n'est pas vide
                        // et qu'on a selectionne l'une des 4 possibilités et si la date est renseignée
                        afficherMessage("Erreur!", "Champ vide");
                        return;
                    }else if (maDate.getText().toString().trim().length()>10 || maDate.getText().toString().trim().length()<8 ) {
                        //test sur la validité du champ date
                        afficherMessage("Erreur!", "Date invalide");
                        return;
                    } else if (Integer.parseInt(txtQte1.getText().toString())<1){ //teste si la quantite est au moins 1
                        afficherMessage("Erreur!", "Quantité invalide");
                        return;
                    }else {
                        Integer quantite = Integer.parseInt(String.valueOf(txtQte1.getText()));
                        String forfait = listeforfait1.getSelectedItem().toString();
                        String dateForfait = maDate.getText().toString();
                        int posForfait = listeforfait1.getSelectedItemPosition();
                         montantCalcule= quantite * Float.parseFloat(valeurs[posForfait]);
                        if (database.insertData(forfait, quantite, dateForfait, montantCalcule, forfait)) {
                            afficherMessage("Succès", "Valeur ajoutée. " + "Montant= " + montantCalcule +"€");
                            return;
                        }
                    }
                    break;
            }
        }

        public void ShowCal(View vv){
            picker = new DatePickerDialog(Fraisauforfait.this,
                    new DatePickerDialog.OnDateSetListener() {
                        @Override
                        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                            maDate.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                            //date qu'on recupere une fois qu'on a selectionne
                        }
                    }, aaaa, mm, jj);//date qui s'affiche sur le calendrier
            picker.show();
        }
    }

