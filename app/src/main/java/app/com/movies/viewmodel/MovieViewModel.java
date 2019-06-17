package app.com.movies.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import java.util.List;

import javax.inject.Inject;

import app.com.movies.MovieApplication;
import app.com.movies.repo.MovieRepo;
import app.com.movies.response.MovieListDataResponse;

public class MovieViewModel extends AndroidViewModel {

    @Inject
    MovieRepo movieRepo;

    public MovieViewModel(@NonNull Application application) {
        super(application);
        MovieApplication.getApplication().getAppComponent().inject(this);
    }


    public LiveData<List<MovieListDataResponse>> getPopularMovies() {
        return movieRepo.getPopularMovies();
    }

    public LiveData<List<MovieListDataResponse>> getHighestRatedMovies() {
        return movieRepo.getHighestRatedMoviesMovies();
    }

}

