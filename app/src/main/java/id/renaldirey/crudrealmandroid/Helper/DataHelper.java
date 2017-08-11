package id.renaldirey.crudrealmandroid.Helper;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;
import java.util.ArrayList;
import id.renaldirey.crudrealmandroid.Model.DataModel;
import io.realm.Realm;
import io.realm.RealmResults;
import io.realm.Sort;

public class DataHelper {
    private static final String TAG = "DataHelper";

    private Realm realm;
    private RealmResults<DataModel> realmResult;
    public Context context;
//    public ArrayList<DataModel> data;

    public DataHelper(Context context){
        realm = Realm.getInstance(context);
        this.context = context;
    }

    public void add(String nama, String email){
        DataModel datamodel = new DataModel();
        int id = (int) (System.currentTimeMillis() / 1000);
        datamodel.setId(id);
        datamodel.setNama(nama);
        datamodel.setEmail(email);

        realm.beginTransaction();
        realm.copyToRealm(datamodel);
        realm.commitTransaction();

        showLog("Added " + Integer.toString(id));
        showToast("Data berhasil disimpan");
    }

    public ArrayList<DataModel> show() {
        ArrayList<DataModel> data = new ArrayList<>();
        realmResult = realm.where(DataModel.class).findAll();
        realmResult.sort("id", Sort.DESCENDING);
        if (realmResult.size() > 0) {
            showLog("Size : " + realmResult.size());
            for (int i = 0; i < realmResult.size(); i++) {
                String nama, email;
                int id = realmResult.get(i).getId();
                nama = realmResult.get(i).getNama();
                email = realmResult.get(i).getEmail();
                data.add(new DataModel(id, nama, email));
            }

        } else {
            showLog("Size : 0");
            showToast("Database Kosong!");
        }

        return data;
    }

    public void delete(int id){
        realmResult = realm.where(DataModel.class).equalTo("id", id).findAll();
        realm.beginTransaction();
        realmResult.remove(0);
        realmResult.removeLast();
        realmResult.clear();
        realm.commitTransaction();

        showToast("Hapus data berhasil.");
    }

    public void update(int id, String nama, String email){
        realm.beginTransaction();
        DataModel data = realm.where(DataModel.class).equalTo("id", id).findFirst();
        data.setNama(nama);
        data.setEmail(email);
        realm.commitTransaction();
        showLog("Success!");

        showToast("Berhasil di update");
    }

    private void showLog(String s) {
        Log.d(TAG, s);
    }

    private void showToast(String s) {
        Toast.makeText(context, s, Toast.LENGTH_LONG).show();
    }
}
