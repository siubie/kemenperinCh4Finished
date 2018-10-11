package id.primadev.recyclerchucknorris;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import id.primadev.recyclerchucknorris.activities.ListActivity;
import id.primadev.recyclerchucknorris.generators.ServiceGenerator;
import id.primadev.recyclerchucknorris.models.ChuckNorrisQuote;
import id.primadev.recyclerchucknorris.services.ChuckService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    TextView txtQuote;
    ImageView imageQuote;
    ChuckService chuckService;
    Button btnReload;
    Button btnGotoList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtQuote = findViewById(R.id.textView);
        imageQuote = findViewById(R.id.imageView);
        btnReload = findViewById(R.id.button);
        btnGotoList = findViewById(R.id.btnList);

        chuckService = ServiceGenerator.createService(ChuckService.class);
        reloadData();

        btnReload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reloadData();
            }
        });
        btnGotoList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), ListActivity.class);
                startActivity(i);
            }
        });

    }

    private void reloadData(){
        Call<ChuckNorrisQuote> call = chuckService.getQuote();
        call.enqueue(new Callback<ChuckNorrisQuote>() {
            @Override
            public void onResponse(Call<ChuckNorrisQuote> call, Response<ChuckNorrisQuote> response) {
                txtQuote.setText(response.body().getValue());
                Picasso.get().load(response.body().getIconUrl()).into(imageQuote);
            }

            @Override
            public void onFailure(Call<ChuckNorrisQuote> call, Throwable t) {
                txtQuote.setText(t.getMessage());
            }
        });
    }
}
