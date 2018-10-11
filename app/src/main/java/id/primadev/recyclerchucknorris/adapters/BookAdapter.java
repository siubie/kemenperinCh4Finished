package id.primadev.recyclerchucknorris.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import id.primadev.recyclerchucknorris.R;
import id.primadev.recyclerchucknorris.models.Books;

/**
 * Created on 11/10/18.
 */

public class BookAdapter extends RecyclerView.Adapter<BookAdapter.BookViewHolder>{
    private Context ctx;
    private List<Books> booksList;

    public BookAdapter(Context ctx, List<Books> booksList) {
        this.ctx = ctx;
        this.booksList = booksList;
    }

    public BookAdapter(List<Books> booksList) {
        this.booksList = booksList;
    }

    @NonNull
    @Override
    public BookViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater
                .from(viewGroup.getContext())
                .inflate(R.layout.item_book,viewGroup,false);
        BookViewHolder vh = new BookViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull BookViewHolder bookViewHolder, int i) {
        Books itemBooks = booksList.get(i);
        bookViewHolder.txtJudul.setText(itemBooks.getName());
    }

    @Override
    public int getItemCount() {
        return booksList.size();
    }

    public class BookViewHolder extends RecyclerView.ViewHolder {
        public TextView txtJudul;
        public BookViewHolder(@NonNull View itemView) {
            super(itemView);
            txtJudul = itemView.findViewById(R.id.bookTitle);
        }
    }
}
