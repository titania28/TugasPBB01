package id.sch.smktelkom_mlg.learn.inputdatasiswa.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import id.sch.smktelkom_mlg.learn.inputdatasiswa.R;

/**
 * Created by Titania Putri on 12/10/2016.
 */
public class KotaAdapter extends ArrayAdapter<String> {
    String mProvinsi = "";

    public KotaAdapter(Context context, ArrayList<String> listKota) {
        super(context, R.layout.row_spinner_kota, listKota);
    }

    public void setmProvinsi(String mProvinsi) {
        this.mProvinsi = mProvinsi;
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        return getCustomView(position, convertView, parent);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return getCustomView(position, convertView, parent);
    }

    private View getCustomView(int position, View view, ViewGroup parent) {
        if (view == null)
            view = LayoutInflater.from(getContext())
                    .inflate(R.layout.row_spinner_kota, parent, false);
        TextView tvTitle = (TextView) view.findViewById(R.id.textViewTitle);
        tvTitle.setText(getItem(position).substring(0, 1));
        TextView tvKota = (TextView) view.findViewById(R.id.textViewKota);
        tvKota.setText(getItem(position));
        TextView tvProvinsi = (TextView) view.findViewById(R.id.textViewProvinsi);
        tvProvinsi.setText(mProvinsi);
        return view;
    }
}


