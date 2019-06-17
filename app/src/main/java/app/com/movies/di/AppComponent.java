package app.com.movies.di;

import javax.inject.Singleton;

import app.com.movies.MovieApplication;
import app.com.movies.viewmodel.MovieViewModel;
import dagger.Component;

@Singleton
@Component(modules = {AppModule.class})
public interface AppComponent {

    void inject(MovieViewModel viewModel);

    void inject(MovieApplication movieApplication);
}

