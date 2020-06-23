package com.rentrust.id.edtrust.siswa.tugas.worker;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.rentrust.id.edtrust.R;
import com.rentrust.id.edtrust.model.modelPGanda;

import java.util.List;

public class WorkerAdapter extends RecyclerView.Adapter<WorkerAdapter.RecyclerViewAdapter> {

    private Context context;
    private List<modelPGanda> gandas;
    private WorkerAdapter.ItemClickListener itemClickListener;

    public WorkerAdapter(
            Context context, List<modelPGanda> gandas,
            WorkerAdapter.ItemClickListener itemClickListener) {

        this.context = context;
        this.gandas = gandas;
        this.itemClickListener = itemClickListener;

    }

    @NonNull
    @Override
    public WorkerAdapter.RecyclerViewAdapter onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.list_nomor,
                parent, false);
        return new WorkerAdapter.RecyclerViewAdapter(view, itemClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull WorkerAdapter.RecyclerViewAdapter holder, int position) {
        modelPGanda ganda = gandas.get(position);

        holder.tv_nomor.setText("No. " + ganda.getNomor());

        //holder.card_items.setCardBackgroundColor(Color.parseColor(retur.getColor()));
        //holder.card_item.setCardBackgroundColor(Color.parseColor(order.getColor()));
    }

    @Override
    public int getItemCount() {
        return gandas.size();
    }

    class RecyclerViewAdapter extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView tv_nomor;
        CardView card_item;
        WorkerAdapter.ItemClickListener itemClickListener;

        RecyclerViewAdapter(View itemTable, WorkerAdapter.ItemClickListener itemClickListener) {
            super(itemTable);
            this.itemClickListener = itemClickListener;

            tv_nomor = itemTable.findViewById(R.id.nomorSoal);
            card_item = itemTable.findViewById(R.id.card_item);

            card_item.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            itemClickListener.onItemClick(v, getAdapterPosition());
        }
    }

    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }

}