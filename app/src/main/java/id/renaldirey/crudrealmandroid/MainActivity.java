package id.renaldirey.crudrealmandroid;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;
import java.util.ArrayList;
import id.renaldirey.crudrealmandroid.Adapter.DataAdapter;
import id.renaldirey.crudrealmandroid.Helper.DataHelper;
import id.renaldirey.crudrealmandroid.Model.DataModel;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private ArrayList<DataModel> data;

    private RecyclerView recyclerView;
    private DataHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        data = new ArrayList<>();
        helper = new DataHelper(this);
        recyclerView = (RecyclerView) findViewById(R.id.rvdata);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), AddData.class));
                finish();
            }
        });
        showData();
    }

//    Load RecyclerView
    private void showData(){
        try {
            data = helper.show();
            System.out.println("hello: "+helper.show());
        } catch (Exception e) {
            e.printStackTrace();
        }

        DataAdapter adapter = new DataAdapter(data, new DataAdapter.OnItemClickListener(){
            @Override
            public void onClick(DataModel item) {
                Intent intent = new Intent(getApplicationContext(), EditData.class);
                intent.putExtra("id", item.getId());
                intent.putExtra("nama", item.getNama());
                intent.putExtra("email", item.getEmail());
                startActivity(intent);
            }
        });
        recyclerView.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
