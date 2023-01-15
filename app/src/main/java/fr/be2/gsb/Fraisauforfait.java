package fr.be2.gsb;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class Fraisauforfait extends MainActivity {
    EditText txtQte1;
    Spinner listeforfait1;
    String[] valeurs;
    double montantcalcul;
    TextView mSomme;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fraisauforfait);

        txtQte1=findViewById(R.id.main_edittext_quantiteFraisauforfait);
        listeforfait1=findViewById(R.id.main_spinner_types);
        mSomme=findViewById(R.id.textview_textsomme);
        valeurs= getResources().getStringArray(R.array.MesValeurs);


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
            Integer q1 = Integer.parseInt(String.valueOf("0"+txtQte1.getText()));
            //String f1 = listeForfait1.getSelectedItem().toString();
            int posF1 = listeforfait1.getSelectedItemPosition();
            Float s1 = q1 * Float.parseFloat(valeurs[posF1]);
            mSomme.setText(s1.toString());


        }
    }

);
    }
}