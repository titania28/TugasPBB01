package id.sch.smktelkom_mlg.learn.inputdatasiswa;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;

import id.sch.smktelkom_mlg.learn.inputdatasiswa.adapter.KotaAdapter;

public class MainActivity extends AppCompatActivity {
    EditText etNama, etTahun, etAlamat;
    RadioGroup rg;
    CheckBox x, xi, xii;
    Spinner spProvinsi, spKota;
    Button bOk;
    TextView tvHasil;
    String[][] arKota = {{"Jakarta Barat", "Jakarta Pusat", "Jakarta Selatan",
            "Jakarta Timur", "Jakarta Utara"},
            {"Bandung", "Cirebon", "Bekasi"}, {"Semarang",
            "Magelang", "Surakarta"},
            {"Surabaya", "Malang", "Blitar"}, {"Depansar"}};
    ArrayList<String> listKota = new ArrayList<>();
    KotaAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etNama = (EditText) findViewById(R.id.editTextNama);
        etTahun = (EditText) findViewById(R.id.editTextTahun);
        etAlamat = (EditText) findViewById(R.id.editTextAlamat);
        rg = (RadioGroup) findViewById(R.id.radioGroup);
        x = (CheckBox) findViewById(R.id.checkBoxX);
        xi = (CheckBox) findViewById(R.id.checkBoxXI);
        xii = (CheckBox) findViewById(R.id.checkBoxXI);
        spProvinsi = (Spinner) findViewById(R.id.spinnerProv);
        spKota = (Spinner) findViewById(R.id.spinnerKota);
        bOk = (Button) findViewById(R.id.buttonOK);

        adapter = new KotaAdapter(this, listKota);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spKota.setAdapter(adapter);
        tvHasil = (TextView) findViewById(R.id.textViewHasil);
        bOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doProcess();
            }
        });

        spProvinsi.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                listKota.clear();
                listKota.addAll(Arrays.asList(arKota[pos]));
                adapter.setmProvinsi((String) spProvinsi.getItemAtPosition(pos));
                adapter.notifyDataSetChanged();
                spKota.setSelection(0);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void doProcess() {
        if (isValid()) {
            String nama = etNama.getText().toString();
            int tahun = Integer.parseInt(etTahun.getText().toString());
            int usia = 2016 - tahun;
            String alamat = etAlamat.getText().toString();
            String hasil = null;
            if (rg.getCheckedRadioButtonId() != -1) {
                RadioButton rb = (RadioButton)
                        findViewById(rg.getCheckedRadioButtonId());
                hasil = rb.getText().toString();
            }
            String hsl = "Anda Menempati Kelas : ";
            int startlen = hsl.length();
            if (x.isChecked()) hsl += x.getText() + "\n";
            if (xi.isChecked()) hsl += xi.getText() + "\n";
            if (xii.isChecked()) hsl += xii.getText() + "\n";

            if (hsl.length() == startlen) hsl += "Tidak ada pilihan";
            tvHasil.setText(" Data Siswa " + "\n Nama Siswa :" + nama + "\n Lahir Tahun : " + tahun + "\n berusia : " + usia
                    + " \n Jurusan " + hasil + "\n" + hsl
                    + "\n Alamat : \n" + " Provinsi :" + spProvinsi.getSelectedItem().toString() +
                    "\n Kota : " + spKota.getSelectedItem().toString() + "\n alamat :" + alamat
            );
        }
    }

    private boolean isValid() {
        boolean valid = true;

        String nama = etNama.getText().toString();
        String tahun = etTahun.getText().toString();

        if (nama.isEmpty()) {
            etNama.setError("Nama Belum Diisi");
            valid = false;
        } else if (nama.length() < 3) {
            etNama.setError("Nama minimal 3 karakter");
            valid = false;
        } else {
            etNama.setError(null);
        }

        if (tahun.isEmpty()) {
            etTahun.setError("Tahun Belum Diisi");
            valid = false;
        } else if (tahun.length() < 3) {
            etTahun.setError("Format Tahun Kelahiran bukan yyy");
            valid = false;
        } else {
            etTahun.setError(null);
        }
        return valid;
    }

}
