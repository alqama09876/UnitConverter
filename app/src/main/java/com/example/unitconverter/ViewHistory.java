package com.example.unitconverter;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.unitconverter.Adapter.ConversionAdapter;
import com.example.unitconverter.Model.ConversionsModel;

import java.util.ArrayList;

public class ViewHistory extends AppCompatActivity {
    RecyclerView rv_history;
    ImageView iv_back;
    ArrayList<ConversionsModel> arrayList = new ArrayList<>();
    DBHelper db = new DBHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_history);
        rv_history = findViewById(R.id.rv_history);
        iv_back = findViewById(R.id.iv_back);

        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        Cursor cursor = db.displayConversionsData();
        while (cursor.moveToNext()) {
            System.out.println("CUR " + cursor.getString(0));
            ConversionsModel conversionsModel = new ConversionsModel(cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getString(5), cursor.getString(6), cursor.getString(7), cursor.getString(8), cursor.getString(9), cursor.getString(10)); // , cursor.getString(2), Float.parseFloat(cursor.getString(3))
            arrayList.add(conversionsModel);
        }
        ConversionAdapter adapter = new ConversionAdapter(arrayList, this, db);
        rv_history.setLayoutManager(new LinearLayoutManager(this));
        rv_history.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }
}