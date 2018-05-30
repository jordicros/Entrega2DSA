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
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BookDetailsActivity extends AppCompatActivity{
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
        setContentView(R.layout.activity_book_details);
        final ProgressBar progrBar = (ProgressBar) findViewById(R.id.progressBar);
        progrBar.setVisibility(View.VISIBLE);
        GithubAPI caller = Singleton.getInstance().getService();
        Llibre ll = Singleton.getInstance().getSelectedBook();
        Call<Llibre> call = caller.getBookDetails(ll._id);
        call.enqueue(new Callback<Llibre>() {
            @Override
            public void onResponse(Call<Llibre> call, Response<Llibre> response) {
                if (response.isSuccessful()) {
                    Llibre ll = (Llibre) response.body();
                    ImageView img = findViewById(R.id.imageView2);
                    Picasso.get().load(ll.image).into(img);
                    TextView titol = findViewById(R.id.textTitol);
                    titol.setText(ll.title);
                    TextView autor = findViewById(R.id.textAutor);
                    autor.setText(ll.author);
                    TextView rating = findViewById(R.id.textRating);
                    String nota = Integer.toString(ll.rating);
                    rating.setText("Nota: "+nota);
                    TextView descr = findViewById(R.id.textDescripcio);
                    descr.setText(ll.description);
                    AdapterComments adp = new AdapterComments(activity, ll.comments);
                    ListView llista = activity.findViewById(R.id.listView);
                    llista.setAdapter(adp);
                    progrBar.setVisibility(View.GONE);

                } else {
                    resultat("Fail on the request");
                    progrBar.setVisibility(View.GONE);
                }
            }

            @Override
            public void onFailure(Call<Llibre> call, Throwable t) {
                resultat("Fail getting the data");
            }
        });

    }

}
