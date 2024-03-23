package com.example.unitconverter.Adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

//import com.example.healthcare.Model.DoctorModel;
import com.example.unitconverter.DBHelper;
import com.example.unitconverter.Model.ConversionsModel;
import com.example.unitconverter.R;

import java.util.ArrayList;

public class ConversionAdapter extends RecyclerView.Adapter<ConversionAdapter.ViewHolder> {
    ArrayList<ConversionsModel> arrayList = new ArrayList<>();
    Context context;
    DBHelper dbHelper;
    boolean isDeleted;

    public ConversionAdapter(ArrayList<ConversionsModel> arrayList, Context context, DBHelper dbHelper) {
        this.arrayList = arrayList;
        this.context = context;
        this.dbHelper = new DBHelper(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.history_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Confirmation");
                builder.setMessage("Are you sure you want to delete?");

                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (position != -1) {
                            String userInput = arrayList.get(position).getUserInput();
                            isDeleted = dbHelper.deleteHistory(userInput);
                        }
                        if (isDeleted) {
                            Toast.makeText(context, "Deleted", Toast.LENGTH_SHORT).show();
                            arrayList.remove(position);
                            notifyItemRemoved(position);
                            notifyDataSetChanged();

                        } else {
                            Toast.makeText(context, "Something went wrong", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });


        holder.From_Unit.setText(String.format("%.3f", Double.parseDouble(arrayList.get(position).getUserInput())));
        holder.km.setText(String.format("%.3f", Double.parseDouble(arrayList.get(position).getKm())));
        holder.m.setText(String.format("%.3f", Double.parseDouble(arrayList.get(position).getM())));
        holder.cm.setText(String.format("%.3f", Double.parseDouble(arrayList.get(position).getCm())));
        holder.mm.setText(String.format("%.3f", Double.parseDouble(arrayList.get(position).getMm())));
        holder.nm.setText(String.format("%.3f", Double.parseDouble(arrayList.get(position).getNm())));
        holder.mile.setText(String.format("%.3f", Double.parseDouble(arrayList.get(position).getMile())));
        holder.yard.setText(String.format("%.3f", Double.parseDouble(arrayList.get(position).getYard())));
        holder.foot.setText(String.format("%.3f", Double.parseDouble(arrayList.get(position).getFoot())));
        holder.inch.setText(String.format("%.3f", Double.parseDouble(arrayList.get(position).getInch())));
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        EditText From_Unit;
        ImageView btn_delete;
        TextView km, m, cm, mm, nm, mile, yard, foot, inch;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            From_Unit = itemView.findViewById(R.id.From_Unit);
            btn_delete = itemView.findViewById(R.id.btn_delete);
            km = itemView.findViewById(R.id.km);
            m = itemView.findViewById(R.id.m);
            cm = itemView.findViewById(R.id.cm);
            mm = itemView.findViewById(R.id.mm);
            nm = itemView.findViewById(R.id.nm);
            mile = itemView.findViewById(R.id.mile);
            yard = itemView.findViewById(R.id.yard);
            foot = itemView.findViewById(R.id.foot);
            inch = itemView.findViewById(R.id.inch);
        }
    }
}
