package fr.be2.gsb_nt;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.FilterQueryProvider;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

public class consulterlesfrais extends AppCompatActivity {
    private SQLHelper database;
    private SimpleCursorAdapter dataAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consulterlesfrais);
        database = new SQLHelper(this);
        database.open();
        //Générer le ListView a partir de SQLite Database
        displayListView();

    }
    private void displayListView() {

        Cursor cursor = database.fetchAllFrais();

        // Les colonnes que l’on veut lier
        String[] columns = new String[]{
                SQLHelper.DATE_FRAIS,
                SQLHelper.MONTANT,
                SQLHelper.DATE_SAISIE,
                SQLHelper.LIBELLE,
        };

        // Les éléments defnis dans le XML auxquels les données sont liées
        int[] to = new int[]{
                R.id.dateFrais,
                R.id.montant,
                R.id.infoSaisie,
                R.id.libelleFrais,
        };
        //On créer l'adaptateur à l'aide du curseur pointant sur les données souhaitées  ainsi que les informations de mise en page
        dataAdapter = new SimpleCursorAdapter(
                this, R.layout.detail_frais,
                cursor,
                columns,
                to,
                0);

        ListView listView = findViewById(R.id.listView1);
        // Attribuer l’adapter au ListView
        listView.setAdapter(dataAdapter);

        EditText myFilter = (EditText) findViewById(R.id.myFilter);
        myFilter.addTextChangedListener(new TextWatcher() {

            public void afterTextChanged(Editable s) {
            }

            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
            }

            public void onTextChanged(CharSequence s, int start,
                                      int before, int count) {
                dataAdapter.getFilter().filter(s.toString());
            }
        });

        dataAdapter.setFilterQueryProvider(new FilterQueryProvider() {
            public Cursor runQuery(CharSequence constraint) {
                return database.fetchFrais(constraint.toString());
            }
        });
    }

    }
