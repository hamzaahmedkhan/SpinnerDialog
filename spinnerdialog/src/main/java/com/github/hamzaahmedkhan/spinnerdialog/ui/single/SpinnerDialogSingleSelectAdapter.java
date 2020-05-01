package com.github.hamzaahmedkhan.spinnerdialog.ui.single;


import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;

import com.github.hamzaahmedkhan.spinnerdialog.R;
import com.github.hamzaahmedkhan.spinnerdialog.callbacks.OnSpinnerItemClickListener;
import com.github.hamzaahmedkhan.spinnerdialog.models.SpinnerModel;

import java.util.ArrayList;

/**
 *
 */
public class SpinnerDialogSingleSelectAdapter extends RecyclerView.Adapter<SpinnerDialogSingleSelectAdapter.ViewHolder> {


    private final OnSpinnerItemClickListener onItemClick;


    private Activity activity;
    private ArrayList<SpinnerModel> arrData;

    public SpinnerDialogSingleSelectAdapter(Activity activity, ArrayList<SpinnerModel> arrayList, OnSpinnerItemClickListener onItemClickListener) {
        this.arrData = arrayList;
        this.activity = activity;
        this.onItemClick = onItemClickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView;
        itemView = LayoutInflater.from(activity)
                .inflate(R.layout.item_spinner_dialog, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int i) {

        final SpinnerModel model = arrData.get(holder.getAdapterPosition());

        holder.txtChoice.setText(model.getText());
        if (model.isSelected()) {
            holder.radioButton.setChecked(true);
        } else {
            holder.radioButton.setChecked(false);
        }
        holder.contParentLayout.setOnClickListener(view -> onItemClick.onItemClick(holder.getAdapterPosition(), model, SpinnerDialogSingleSelectAdapter.this));


    }

    public void addItem(ArrayList<SpinnerModel> homeCategories) {
        this.arrData = homeCategories;
        notifyDataSetChanged();
    }

    public ArrayList<SpinnerModel> getArrData() {
        return arrData;
    }

    @Override
    public int getItemCount() {
        return arrData.size();
    }


    static class ViewHolder extends RecyclerView.ViewHolder {
        RadioButton radioButton;
        TextView txtChoice;
        LinearLayout contParentLayout;

        ViewHolder(@NonNull View itemView) {
            super(itemView);

            radioButton = itemView.findViewById(R.id.radioButton);
            txtChoice = itemView.findViewById(R.id.txtChoice);
            contParentLayout = itemView.findViewById(R.id.contParentLayout);
        }
    }
}
