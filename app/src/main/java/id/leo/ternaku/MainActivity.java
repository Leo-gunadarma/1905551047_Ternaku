package id.leo.ternaku;

import static java.sql.Types.NULL;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.InputType;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import id.leo.ternaku.database.DatabaseHewan;
import id.leo.ternaku.database.TabelHewan;
import id.leo.ternaku.database.TabelProfil;

public class MainActivity extends AppCompatActivity {
    ImageView kelolaHewan,kelolaKandang,kelolaObat,pengaturanProfil;
    private DatabaseHewan database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        database = DatabaseHewan.getDbInstance(this.getApplicationContext());
        kelolaHewan = findViewById(R.id.imageViewKelolaHewan);
        kelolaKandang = findViewById(R.id.imageViewKelolaKandang);
        kelolaObat = findViewById(R.id.imageViewMenuKelolaObat);
        pengaturanProfil = findViewById(R.id.imageViewMenuPengaturanProfil);
        kelolaHewan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                menuKelolaHewan();
            }
        });
        kelolaKandang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                menuKelolaKandang();
            }
        });
        kelolaObat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                menuKelolaObat();
            }
        });
        pengaturanProfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                menuPengaturanProfil();
            }
        });
    }
    public void menuKelolaHewan(){
        Intent intent = new Intent(MainActivity.this, KelolaHewanActivity.class);
        startActivity(intent);
    }
    public void menuKelolaKandang(){
        Intent intent = new Intent(MainActivity.this, KelolaKandangActivity.class);
        startActivity(intent);
    }
    public void menuKelolaObat(){
        Intent intent = new Intent(MainActivity.this, KelolaObatActivity.class);
        startActivity(intent);
    }
    public void menuPengaturanProfil(){
        if(database.daoHewan().checkTableProfil()==NULL){
            AlertDialog.Builder infoMsg = new AlertDialog.Builder(MainActivity.this);
            infoMsg.setTitle("Namakan TernakMu");
            Context context = MainActivity.this;
            LinearLayout layout = new LinearLayout(context);
            layout.setOrientation(LinearLayout.VERTICAL);
            layout.setPadding(20,0,20,0);

            final TextView namaTernak = new TextView(MainActivity.this);
            namaTernak.setText("Nama ternak: ");
            namaTernak.setTextColor(ContextCompat.getColor(this,R.color.tema_aplikasi3));
            layout.addView(namaTernak);
            final EditText inputNama = new EditText(MainActivity.this);
            inputNama.setInputType(InputType.TYPE_CLASS_TEXT);
            layout.addView(inputNama);

            final TextView lokasiTernak = new TextView(MainActivity.this);
            lokasiTernak.setText("Lokasi ternak: ");
            lokasiTernak.setTextColor(ContextCompat.getColor(this,R.color.tema_aplikasi3));
            layout.addView(lokasiTernak);
            final EditText inputLokasi= new EditText(MainActivity.this);
            inputLokasi.setInputType(InputType.TYPE_TEXT_FLAG_MULTI_LINE);
            layout.addView(inputLokasi);

            infoMsg.setView(layout);

            infoMsg.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    if (inputLokasi.getText().toString().trim().length() == 0){
                        Toast.makeText(context, "Lokasi Belum Diisi Ulangi Memasukan Data", Toast.LENGTH_LONG).show();
                    }else if (inputNama.getText().toString().trim().length() == 0){
                        Toast.makeText(context, "Nama Ternak Belum Diisi Ulangi Masukan Data", Toast.LENGTH_LONG).show();
                    }else{
                        String nmaTernak = inputNama.getText().toString();
                        String lkasiTernak = inputLokasi.getText().toString();
                        TabelProfil row = new TabelProfil(nmaTernak,lkasiTernak);
                        database.daoHewan().insertDataProfil(row);
                        Intent intent = new Intent(MainActivity.this, PengaturanProfilActivity.class);
                        startActivity(intent);
                    }
                }
            });
            infoMsg.show();
        }else{
            Intent intent = new Intent(MainActivity.this, PengaturanProfilActivity.class);
            startActivity(intent);
        }

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}