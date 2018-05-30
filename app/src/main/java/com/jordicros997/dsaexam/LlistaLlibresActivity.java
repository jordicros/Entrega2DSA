package com.jordicros997.dsaexam;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LlistaLlibresActivity extends AppCompatActivity{

    public Activity activity = this;
    public void resultat(String result) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        AlertDialog dialog = builder.create();
        builder.setMessage(result).setTitle("Info");
        Dialog dialeg = builder.create();
        dialeg.show();

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_llista_llibres);
        final ProgressBar progrBar = (ProgressBar) findViewById(R.id.progressBar2);
        progrBar.setVisibility(View.VISIBLE);
        GithubAPI caller = Singleton.getInstance().getService();
        Call<List<Llibre>> llistLlibres = caller.getLlibres();
        llistLlibres.enqueue(new Callback<List<Llibre>>() {
            @Override
            public void onResponse(Call<List<Llibre>> call, Response<List<Llibre>> response) {
                if(response.isSuccessful()) {
                    List<Llibre> follows = response.body();
                    AdapterBooks adp = new AdapterBooks(activity,follows);
                    ListView llista = activity.findViewById(R.id.list_view);
                    llista.setAdapter(adp);
                    progrBar.setVisibility(View.GONE);
                    llista.setOnItemClickListener(new AdapterView.OnItemClickListener(){
                        @Override
                        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                            Llibre item = (Llibre) adapterView.getItemAtPosition(i);
                            Singleton.getInstance().setSelectedBook(item);
                            Intent intent = new Intent(activity,BookDetailsActivity.class);
                            startActivity(intent);
                        }
                    });

                }
                else{
                    progrBar.setVisibility(View.GONE);
                    resultat("Failed of the request");}
            }


            @Override
            public void onFailure(Call<List<Llibre>> call, Throwable t) {
                progrBar.setVisibility(View.GONE);
                resultat("Failed to get the data");
            }
        });
    }
}
