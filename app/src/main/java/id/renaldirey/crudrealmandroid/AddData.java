package id.renaldirey.crudrealmandroid;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import id.renaldirey.crudrealmandroid.Helper.DataHelper;

public class AddData extends AppCompatActivity {

    @BindView(R.id.inputNama)
    EditText namatxt;
    @BindView(R.id.inputEmail)
    EditText emailtxt;

    private DataHelper dataHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_data);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.simpan)
    void clickSave(){
        dataHelper = new DataHelper(this);

        String nama = namatxt.getText().toString(), email = emailtxt.getText().toString();
        simpan(nama, email);
    }

    private void simpan(String nama, String email){
        dataHelper.add(nama, email);
        finish();

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
