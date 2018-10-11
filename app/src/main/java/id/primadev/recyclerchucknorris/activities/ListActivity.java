package id.primadev.recyclerchucknorris.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import id.primadev.recyclerchucknorris.R;
import id.primadev.recyclerchucknorris.adapters.BookAdapter;
import id.primadev.recyclerchucknorris.generators.ServiceFireGenerator;
import id.primadev.recyclerchucknorris.generators.ServiceGenerator;
import id.primadev.recyclerchucknorris.models.Books;
import id.primadev.recyclerchucknorris.services.GotService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListActivity extends AppCompatActivity {
    public GotService gotService;
    public Button btnReload;
    public RecyclerView recyclerView;
    public BookAdapter bookAdapter;
    public List<Books> booksList = new ArrayList<>();
    public RecyclerView.LayoutManager mLayoutManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        bookAdapter = new BookAdapter(booksList);
        //service generator
        gotService = ServiceFireGenerator.createService(GotService.class);
        reloadData();
        //recyclerview
        mLayoutManager = new LinearLayoutManager(this);
        recyclerView = findViewById(R.id.rvBook);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(bookAdapter);
        //btn reload
        btnReload = findViewById(R.id.btnReloadList);
        btnReload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reloadData();
            }
        });

    }

    private void reloadData() {
        //service generator
        Call<List<Books>> call = gotService.getBooks();
        call.enqueue(new Callback<List<Books>>() {
            @Override
            public void onResponse(Call<List<Books>> call, Response<List<Books>> response) {
                booksList.clear();
                booksList.addAll(response.body());
                bookAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<Books>> call, Throwable t) {
                Toast
                .makeText(getApplicationContext(),"Gagal Load Data",Toast.LENGTH_LONG)
                .show();
            }
        });
    }
}
