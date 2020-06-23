package com.rentrust.id.edtrust.siswa.room;

import android.widget.Filter;

import com.rentrust.id.edtrust.model.modelRoomSiswa;

import java.util.ArrayList;

public class CustomFilter extends Filter {

    RoomAdapter adapter;
    ArrayList<modelRoomSiswa> filterList;

    public CustomFilter(ArrayList<modelRoomSiswa> filterList,RoomAdapter adapter)
    {
        this.adapter=adapter;
        this.filterList=filterList;

    }

    //FILTERING OCURS
    @Override
    protected FilterResults performFiltering(CharSequence constraint) {
        FilterResults results=new FilterResults();
        //CHECK CONSTRAINT VALIDITY
        if(constraint != null && constraint.length() > 0)
        {
            //CHANGE TO UPPER
            constraint=constraint.toString().toUpperCase();
            //STORE OUR FILTERED PLAYERS
            ArrayList<modelRoomSiswa> filteredPets=new ArrayList<>();

            for (int i=0;i<filterList.size();i++)
            {
                //CHECK
                if(filterList.get(i).getNama_room().toUpperCase().contains(constraint))
                {
                    //ADD PLAYER TO FILTERED PLAYERS
                    filteredPets.add(filterList.get(i));
                }
            }

            results.count=filteredPets.size();
            results.values=filteredPets;

        }else
        {
            results.count=filterList.size();
            results.values=filterList;
        }

        return results;
    }

    @Override
    protected void publishResults(CharSequence constraint, FilterResults results) {

        adapter.rooms= (ArrayList<modelRoomSiswa>) results.values;

        //REFRESH
        adapter.notifyDataSetChanged();

    }
}
