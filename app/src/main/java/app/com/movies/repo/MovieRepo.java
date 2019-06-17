package app.com.movies.repo;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import app.com.movies.BuildConfig;
import app.com.movies.response.MovieDataResponse;
import app.com.movies.response.MovieListDataResponse;
import app.com.movies.services.ApiInterface;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

@Singleton
public class MovieRepo {

    @Inject
    ApiInterface apiInterface;

    /**
     *  Message service is now passed into Movie repository as a dependency and is provided by the module
     *  rather than initiating it here directly so as to reduce the load on this class.
     * @param apiInterface
     */
    @Inject
    public MovieRepo(ApiInterface apiInterface) {
        this.apiInterface = apiInterface;
    }

    public LiveData<List<MovieListDataResponse>> getPopularMovies() {
        final MutableLiveData<List<MovieListDataResponse>> data = new MutableLiveData<>();
        apiInterface.popularMovies(BuildConfig.API_KEY)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new io.reactivex.Observer<MovieDataResponse>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(MovieDataResponse movieDataResponse) {
                        if (movieDataResponse != null)
                            data.setValue(movieDataResponse.getMovieResults());
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
        return data;
    }

    public LiveData<List<MovieListDataResponse>> getHighestRatedMoviesMovies() {
        final MutableLiveData<List<MovieListDataResponse>> data = new MutableLiveData<>();
        apiInterface.highestRatedMovies(BuildConfig.API_KEY)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<MovieDataResponse>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(MovieDataResponse movieDataResponse) {
                        if (movieDataResponse != null)
                            data.setValue(movieDataResponse.getMovieResults());
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
        return data;
    }

}
