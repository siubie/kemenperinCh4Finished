package id.primadev.recyclerchucknorris.services;

import id.primadev.recyclerchucknorris.models.ChuckNorrisQuote;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created on 11/10/18.
 */

public interface ChuckService {
    @GET("jokes/random")
    Call<ChuckNorrisQuote> getQuote();
}
