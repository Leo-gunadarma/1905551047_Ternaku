package id.leo.ternaku.database;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "tb_profil")
public class TabelProfil {
    @PrimaryKey(autoGenerate = true)
    int id;
    @ColumnInfo(name = "nama_ternak")
    String namaTernak;
    @ColumnInfo(name = "lokasi_ternak")
    String lokasiTernak;

    public TabelProfil(String namaTernak, String lokasiTernak) {
        this.namaTernak = namaTernak;
        this.lokasiTernak = lokasiTernak;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getNamaTernak() {
        return namaTernak;
    }

    public String getLokasiTernak() {
        return lokasiTernak;
    }
}
