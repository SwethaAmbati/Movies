package app.com.movies.di;

import javax.inject.Singleton;


import app.com.movies.repo.MovieRepo;
import app.com.movies.services.ApiBuilder;
import app.com.movies.services.ApiInterface;
import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {
    ApiInterface apiInterface;

    @Singleton
    @Provides
    public MovieRepo providesMovieRepo(ApiInterface apiInterface) {
        return new MovieRepo(apiInterface);
    }

    @Singleton
    @Provides
    public ApiInterface providesApiInterface() {
        apiInterface = ApiBuilder.buildService(ApiInterface.class);
        return apiInterface;
    }

}

