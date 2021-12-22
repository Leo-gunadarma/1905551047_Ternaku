package id.leo.ternaku;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import id.leo.ternaku.database.DatabaseHewan;
import id.leo.ternaku.database.TabelObat;
import id.leo.ternaku.database.TabelProfil;

public class EditTernakActivity extends AppCompatActivity {
    DatabaseHewan database;
    EditText namaTernak,lokasiTernak;
    Button simpan;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_ternak);

        database = DatabaseHewan.getDbInstance(this.getApplicationContext());
        namaTernak =findViewById(R.id.editTextNamaTernakEdit);
        lokasiTernak = findViewById(R.id.editTextLokasiTernak);
        Intent hasil = getIntent();
        int id_profil = Integer.parseInt(hasil.getStringExtra("id_profil"));
        namaTernak.setText(hasil.getStringExtra("nama_ternak"));
        lokasiTernak.setText(hasil.getStringExtra("lokasi_ternak"));

        simpan =findViewById(R.id.buttonSimpan);
        simpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (namaTernak.getText().toString().trim().length() == 0){
                    //method trim digunakan untuk menghapus spasi
                    Toast toast = Toast.makeText(EditTernakActivity.this, "Nama Ternak Belum diisi", Toast.LENGTH_LONG);
                    toast.show();
                    return;
                }
                if (lokasiTernak.getText().toString().trim().length() == 0){
                    Toast toast = Toast.makeText(EditTernakActivity.this, "Lokasi Ternak Belum diisi", Toast.LENGTH_LONG);
                    toast.show();
                    return;
                }
                String nmaTernak = namaTernak.getText().toString();
                String lkasiTernak = lokasiTernak.getText().toString();
                TabelProfil row = new TabelProfil(nmaTernak,lkasiTernak);
                row.setId(id_profil);
                database.daoHewan().updateProfil(row);
                Intent intent = new Intent(EditTernakActivity.this, PengaturanProfilActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(EditTernakActivity.this, PengaturanProfilActivity.class);
        startActivity(intent);
        finish();
    }
}