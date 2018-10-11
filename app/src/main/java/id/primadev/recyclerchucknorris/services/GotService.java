package id.primadev.recyclerchucknorris.services;

import java.util.List;

import id.primadev.recyclerchucknorris.models.Books;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created on 11/10/18.
 */

public interface GotService {
    @GET("books")
    Call<List<Books>> getBooks();
}
