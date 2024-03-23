package com.example.unitconverter;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.database.Cursor;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatCallback;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

//import com.example.healthcare.Adapter.DoctorAdapter;
//import com.example.healthcare.Model.DoctorModel;
import com.example.unitconverter.Model.ConversionsModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class Dashboard extends AppCompatActivity {
    ImageView iv_save;
    Button btnSave;
    boolean isHistoryAdded;
    EditText From_Unit;
    Spinner spn_convert;
    String kilometer, meter, centiMeter, milliMeter, nanoMeter, Mile, Yard, Foot, Inch;
    TextView km, m, cm, mm, nm, mile, yard, foot, inch, seeHistory;
    ArrayList<ConversionsModel> conversionsModels = new ArrayList<>();
    DBHelper dbHelper = new DBHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        //find ids...
        iv_save = findViewById(R.id.iv_save);
        From_Unit = findViewById(R.id.From_Unit);
        btnSave = findViewById(R.id.btnSave);
        spn_convert = findViewById(R.id.spn_convert);
        km = findViewById(R.id.km);
        m = findViewById(R.id.m);
        cm = findViewById(R.id.cm);
        mm = findViewById(R.id.mm);
        nm = findViewById(R.id.nm);
        mile = findViewById(R.id.mile);
        yard = findViewById(R.id.yard);
        foot = findViewById(R.id.foot);
        inch = findViewById(R.id.inch);
        seeHistory = findViewById(R.id.seeHistory);


        // spinner functionality
        spn_convert.getResources().getColor(R.color.black);
        String[] arr = {"m", "cm", "mm", "nm", "mile", "yard", "foot", "inch"};
        spn_convert.setAdapter(new ArrayAdapter<String>(Dashboard.this, android.R.layout.simple_list_item_1, arr));
        spn_convert.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                update();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        From_Unit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.length() == 0) {
                    km.setText("");
                    m.setText("");
                    cm.setText("");
                    mm.setText("");
                    nm.setText("");
                    mile.setText("");
                    yard.setText("");
                    foot.setText("");
                    inch.setText("");
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
                update();

            }
        });


        iv_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Dashboard.this, Profile.class));
            }
        });


        seeHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Dashboard.this, ViewHistory.class));
            }
        });

    }


    public void update() {
        if (!From_Unit.getText().toString().equals("") && !spn_convert.getSelectedItem().toString().equals("")) {
            double in = Double.parseDouble(From_Unit.getText().toString());
            switch (spn_convert.getSelectedItem().toString()) {
                case "Km":
                    setKm(in);
                    break;
                case "m":
                    setKm(in / 1000);
                    break;
                case "cm":
                    setKm(in / 100000);
                    break;
                case "mm":
                    setKm(in / 1000000);
                    break;
                case "nm":
                    double d = 1000000 * 1000000;
                    setKm(in / d);
                    break;
                case "mile":
                    setKm(in * 1.609);
                    break;
                case "yard":
                    setKm(in / 1094);
                    break;
                case "foot":
                    setKm(in / 3281);
                    break;
                case "inch":
                    setKm(in / 39370);
                    break;
            }
        }


        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isHistoryAdded = dbHelper.addDataToHistory(From_Unit.getText().toString(), kilometer, meter, centiMeter, milliMeter, nanoMeter, Mile, Yard, Foot, Inch);
                if (isHistoryAdded) {
                    Toast.makeText(Dashboard.this, "History Saved", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(Dashboard.this, "Something went wrong", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


    public void setKm(double v) {
        kilometer = String.format("%.3f", v);
        meter = String.format("%.3f", v * 1000);
        centiMeter = String.format("%.3f", v * 100000);
        milliMeter = String.format("%.3f", v * 1000000);
        nanoMeter = String.format(String.valueOf(v * 1000000 * 1000000));
        Mile = String.format("%.3f", v / 1.609);
        Yard = String.format("%.3f", v * 1094);
        Foot = String.format("%.3f", v * 3281);
        Inch = String.format("%.3f", v * 39370);
        km.setText(kilometer);
        m.setText(meter);
        cm.setText(centiMeter);
        mm.setText(milliMeter);
        nm.setText(nanoMeter);
        mile.setText(Mile);
        yard.setText(Yard);
        foot.setText(Foot);
        inch.setText(Inch);
    }
}
