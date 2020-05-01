package com.github.hamzaahmedkhan.spinnerdialog.ui.multi;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.github.hamzaahmedkhan.spinnerdialog.R;
import com.github.hamzaahmedkhan.spinnerdialog.callbacks.OnSpinnerItemCheckboxClickListener;
import com.github.hamzaahmedkhan.spinnerdialog.models.SpinnerModel;

import java.util.ArrayList;


/**
 */
public class SpinnerDialogMultiSelectAdapter extends RecyclerView.Adapter<SpinnerDialogMultiSelectAdapter.ViewHolder> {

    private final OnSpinnerItemCheckboxClickListener onItemClick;
    private Activity activity;
    private ArrayList<SpinnerModel> arrData;

    public SpinnerDialogMultiSelectAdapter(Activity activity, ArrayList<SpinnerModel> arrayList, OnSpinnerItemCheckboxClickListener onItemClickListener) {
        this.arrData = arrayList;
        this.activity = activity;
        this.onItemClick = onItemClickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView;
        itemView = LayoutInflater.from(activity)
                .inflate(R.layout.item_spinner_dialog_with_checkbox, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int i) {

        final SpinnerModel model = arrData.get(holder.getAdapterPosition());

        holder.txtChoice.setText(model.getText());
        if (model.isSelected()) {
            holder.checkbox.setChecked(true);
        }
        else {
            holder.checkbox.setChecked(false);
        }
        holder.contParentLayout.setOnClickListener(view -> {
            onItemClick.onItemClick(holder.getAdapterPosition(), model, SpinnerDialogMultiSelectAdapter.this);
        });
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
        CheckBox checkbox;
        TextView txtChoice;
        LinearLayout contParentLayout;

        ViewHolder(View view) {
            super(view);
            checkbox = view.findViewById(R.id.txtChoice);
            txtChoice = view.findViewById(R.id.checkbox);
            contParentLayout = view.findViewById(R.id.contParentLayout);
        }
    }
}
