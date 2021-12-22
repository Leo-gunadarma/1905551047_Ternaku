package id.leo.ternaku;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import id.leo.ternaku.database.DatabaseHewan;
import id.leo.ternaku.database.TabelProfil;

public class PengaturanProfilActivity extends AppCompatActivity {
    private DatabaseHewan database;
    List<TabelProfil> dataListProfil = new ArrayList<>();
    TextView namaTernak, lokasiTernak;
    ImageView edit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pengaturan_profil);
        database = DatabaseHewan.getDbInstance(this);
        dataListProfil = database.daoHewan().getFirstDataProfil();
        namaTernak = findViewById(R.id.textViewNamaTernak);
        lokasiTernak = findViewById(R.id.textViewLokasiTernak);
        edit =findViewById(R.id.imageViewEditTernak);
        namaTernak.setText(""+dataListProfil.get(0).getNamaTernak());
        lokasiTernak.setText(""+dataListProfil.get(0).getLokasiTernak());
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent edit = new Intent(PengaturanProfilActivity.this,EditTernakActivity.class);
                edit.putExtra("id_profil", ""+dataListProfil.get(0).getId());
                edit.putExtra("nama_ternak", dataListProfil.get(0).getNamaTernak());
                edit.putExtra("lokasi_ternak", dataListProfil.get(0).getLokasiTernak());
                startActivity(edit);

            }
        });

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent= new Intent(PengaturanProfilActivity.this , MainActivity.class);
        startActivity(intent);
        finish();
    }
}