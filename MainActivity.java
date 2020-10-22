package com.example.projectsdetails;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainActivity extends AppCompatActivity {


    LinearLayout coordinatorLayout;

    Button bl;
    EditText ed_pname, ed_Dur, ed_date;
    String name, roll, marks;

    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;

    ProjectAdapter projectAdapter;
    List<Project> project;
    Api api;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        api = Connection.getApiCon().create(Api.class);

        ed_pname = findViewById(R.id.name);
        ed_Dur = findViewById(R.id.roll);
        ed_date = findViewById(R.id.marks);
        bl = findViewById(R.id.bload);
        coordinatorLayout = findViewById(R.id.line2);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        registerForContextMenu(bl);
        onLoad();
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
//        menu.setHeaderIcon();
        menu.setHeaderTitle("Select:");
        getMenuInflater().inflate(R.menu.menu1, menu);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.id_del:
                setMessage("Button menu Delete.");
                return true;

            case 121:

                setMessage("Item Deleted !");
                return true;

            case 122:
                setMessage("Nothing to do !");
                return true;
            default:
                return super.onContextItemSelected(item);
        }

    }

    private void setMessage(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    public void onClicked(View view) {
        if (ed_pname.getText().toString().equals("")) {
            ed_pname.setError("Project Name Required !");
            ed_pname.requestFocus();
        } else if (ed_Dur.getText().toString().equals("")) {
            ed_Dur.setError("Enter Duration !");
            ed_Dur.requestFocus();
        } else if (ed_date.getText().toString().equals("")) {
            ed_date.setError("Submitted On ?");
            ed_date.requestFocus();
        } else {
            Toast.makeText(this, "DONE", Toast.LENGTH_SHORT).show();
            System.out.println("DONE");
            name = ed_pname.getText().toString();
            roll = ed_Dur.getText().toString();
            marks = ed_date.getText().toString();

            ProgressDialog pd = new ProgressDialog(this);
            pd.setTitle("Processing..");
            pd.setMessage("Please wait !");
            pd.show();

            Call<Project> call = api.saveProject(name, roll, marks);
            call.enqueue(new Callback<Project>() {

                @Override
                public void onResponse(Call<Project> call, Response<Project> response) {
                    if (response.body() == null)    //System.out.println("Add Response: "+);
                    {
                        Snackbar snackbar = Snackbar
                                .make(coordinatorLayout, "NullPointerException", Snackbar.LENGTH_LONG);
                        snackbar.show();
                    } else {
                        if (response.body().getResp().equals("ok")) {
                            Toast.makeText(MainActivity.this, "RECORD STORED !", Toast.LENGTH_SHORT).show();
                            ed_pname.setText("");
                            ed_Dur.setText("");
                            ed_date.setText("");
                            onLoad();
                        } else if (response.body().getResp().equals("failed")) {
                            Toast.makeText(MainActivity.this, "RECORD NOT STORED !", Toast.LENGTH_SHORT).show();
                        } else if (response.body().getResp().equals("exists")) {
                            Toast.makeText(MainActivity.this, "RECORD ALREADY EXISTS !", Toast.LENGTH_SHORT).show();
                        }
                    }
                }

                @Override
                public void onFailure(Call<Project> call, Throwable t) {

                }
            });
            pd.dismiss();
        }
    }

    public void onLoaded(View view) {
        onLoad();
    }

    public void onLoad() {

        Call<List<Project>> call = api.getProjects();
        call.enqueue(new Callback<List<Project>>() {

            @Override
            public void onResponse(Call<List<Project>> call, Response<List<Project>> response) {
                project = response.body();
                System.out.println("Response Body: " + response.body().toString());
                projectAdapter = new ProjectAdapter(project, MainActivity.this);
                recyclerView.setAdapter(projectAdapter);
            }

            @Override
            public void onFailure(Call<List<Project>> call, Throwable t) {
                Toast.makeText(MainActivity.this,
                        "Unable to fetch...!",
                        Toast.LENGTH_SHORT).show();
            }
        });
    }
}
