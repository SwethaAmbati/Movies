package app.com.movies.services;

import app.com.movies.response.MovieDataResponse;
import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {


    @GET("3/discover/movie?language=en&sort_by=popularity.desc")
    Observable<MovieDataResponse> popularMovies(@Query("api_key") String apiKey);


    @GET("3/discover/movie?vote_count.gte=500&language=en&sort_by=vote_average.desc")
    Observable<MovieDataResponse> highestRatedMovies(@Query("api_key") String apiKey);
}
