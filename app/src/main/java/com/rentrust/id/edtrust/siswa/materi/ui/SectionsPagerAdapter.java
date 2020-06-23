package com.rentrust.id.edtrust.siswa.materi.ui;

import android.content.Context;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.rentrust.id.edtrust.R;
import com.rentrust.id.edtrust.siswa.materi.FragFILE;
import com.rentrust.id.edtrust.siswa.materi.FragIMG;
import com.rentrust.id.edtrust.siswa.materi.FragVID;

/**
 * A [FragmentPagerAdapter] that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
public class SectionsPagerAdapter extends FragmentPagerAdapter {

    @StringRes
    private static final int[] TAB_TITLES = new int[]{R.string.tab_text_1, R.string.tab_text_2, R.string.tab_text_3};
    private final Context mContext;
    String nama_room;
    int id_guru;

    public SectionsPagerAdapter(Context context, String nama_room, int id_guru, FragmentManager fm) {
        super(fm);
        mContext = context;
        this.nama_room = nama_room;
        this.id_guru = id_guru;

    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        switch (position) {
            case 0:
                fragment = new FragFILE().newInstance(this.nama_room, this.id_guru);
                break;
            case 1:
                fragment = new FragIMG().newInstance(this.nama_room, this.id_guru);
                break;
            case 2:
                fragment = new FragVID().newInstance(this.nama_room, this.id_guru);
                break;
        }
        return fragment;

    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mContext.getResources().getString(TAB_TITLES[position]);
    }

    @Override
    public int getCount() {
        // Show 2 total pages.
        return 3;
    }
}