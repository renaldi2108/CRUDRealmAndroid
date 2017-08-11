package id.renaldirey.crudrealmandroid.Model;

import io.realm.RealmObject;

public class DataModel extends RealmObject {
    private int id;
    private String nama;
    private String email;

//    Default Contructor DataModel
    public DataModel() {
        super();
    }

//    Constructor with Requirement Parameter DataModel
    public DataModel(int id, String nama, String email) {
        super();
        this.id = id;
        this.nama = nama;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
