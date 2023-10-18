package com.example.egzaminacyjnedos;

import static java.security.AccessController.getContext;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Notification;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.security.SignatureSpi;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;

public class MainActivity2 extends AppCompatActivity {

    private ArrayList<Sprawdzian> sprawdziany;
    private ListView listViewSprawdziany;
    private ArrayAdapter<Sprawdzian> adapter;
    Date bazowaData = new Date();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        EditText coEditText = findViewById(R.id.editTextTextPersonName);
        EditText przedmiotEditText = findViewById(R.id.editTextPrzedmiot);
        sprawdziany = new ArrayList<>();
        listViewSprawdziany = findViewById(R.id.listwju);
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, sprawdziany);
        listViewSprawdziany.setAdapter(adapter);
        Button dodaj = findViewById(R.id.dodaj);
        Button data = findViewById(R.id.data);
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        data.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog dpd = new DatePickerDialog(MainActivity2.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthofyear, int dayofmonth) {
                        bazowaData = new Date(year, monthofyear,dayofmonth);
                    }
                }, year, month, day);
                dpd.show();
            }
        });


        dodaj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String co = String.valueOf(coEditText.getText());
                String przedmiot = String.valueOf(przedmiotEditText.getText());
                sprawdziany.add(new Sprawdzian(przedmiot,co, bazowaData));
                Collections.sort(sprawdziany, new Comparator<Sprawdzian>() {
                    public int compare(Sprawdzian spr1, Sprawdzian spr2) {
                        return spr1.getDataWykonania().compareTo(spr2.getDataWykonania());
                    }
                });
                adapter.notifyDataSetChanged();
            }
        });



        listViewSprawdziany.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Sprawdzian sprawdzian = (Sprawdzian)parent.getItemAtPosition(position);
                String przedmiot = sprawdzian.getPrzedmiot();
                String co = sprawdzian.getCoDoZrobienia();
                Date data = sprawdzian.getDataWykonania();
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity2.this);
                builder.setMessage(co + " - " + Sprawdzian.odczytajDate(data))
                        .setTitle(przedmiot);
                builder.create().show();
            }
        });


    }

}


