package com.rentrust.id.edtrust.siswa.history;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.rentrust.id.edtrust.R;
import com.rentrust.id.edtrust.model.modelHistory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.RecyclerViewAdapter> {

    private Context context;
    private List<modelHistory> histories;
    private HistoryAdapter.ItemClickListener itemClickListener;

    public HistoryAdapter(
            Context context, List<modelHistory> histories,
            HistoryAdapter.ItemClickListener itemClickListener) {

        this.context = context;
        this.histories = histories;
        this.itemClickListener = itemClickListener;

    }

    @NonNull
    @Override
    public HistoryAdapter.RecyclerViewAdapter onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.list_history,
                parent, false);
        return new HistoryAdapter.RecyclerViewAdapter(view, itemClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull HistoryAdapter.RecyclerViewAdapter holder, int position) {
        modelHistory history = histories.get(position);

        holder.tv_judul.setText(history.getJudul());


        String date1 = history.getTgl_history();
        SimpleDateFormat spf1 = new SimpleDateFormat("yyyy-MM-dd");
        Date newDate = null;
        try {
            newDate = spf1.parse(history.getTgl_history());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        spf1 = new SimpleDateFormat("dd MMM yyyy");
        date1 = spf1.format(newDate);
        holder.tv_tanggal.setText(date1 +" "+ history.getJam_history());


        //holder.card_items.setCardBackgroundColor(Color.parseColor(retur.getColor()));
        //holder.card_item.setCardBackgroundColor(Color.parseColor(order.getColor()));
    }

    @Override
    public int getItemCount() {
        return histories.size();
    }

    class RecyclerViewAdapter extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView tv_judul, tv_tanggal;
        CardView card_item;
        HistoryAdapter.ItemClickListener itemClickListener;

        RecyclerViewAdapter(View itemTable, HistoryAdapter.ItemClickListener itemClickListener) {
            super(itemTable);
            this.itemClickListener = itemClickListener;

            tv_judul = itemTable.findViewById(R.id.judul);
            tv_tanggal = itemTable.findViewById(R.id.tglHistory);
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
