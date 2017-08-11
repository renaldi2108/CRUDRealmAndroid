package id.renaldirey.crudrealmandroid;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import id.renaldirey.crudrealmandroid.Helper.DataHelper;

public class EditData extends AppCompatActivity {

    @BindView(R.id.inputNama)
    EditText namatxt;
    @BindView(R.id.inputEmail)
    EditText emailtxt;
    @BindView(R.id.hapus)
    Button hapus;

    private DataHelper dataHelper;
    String nama, email;
    int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_data);
        ButterKnife.bind(this);
        hapus.setVisibility(View.VISIBLE);

        dataHelper = new DataHelper(this);

        id = getIntent().getIntExtra("id", 0);
        nama = getIntent().getStringExtra("nama");
        email = getIntent().getStringExtra("email");
    }

    @OnClick(R.id.simpan)
    void clickSave(){
        String nama = namatxt.getText().toString(), email = emailtxt.getText().toString();
        update(nama, email);
    }

    @OnClick(R.id.hapus)
    void clickDelete(){
        dataHelper.delete(id);
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }

    private void update(String nama, String email){
        dataHelper.update(id, nama, email);
        finish();

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
