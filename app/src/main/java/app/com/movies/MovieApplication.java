package app.com.movies;

import android.app.Application;

import app.com.movies.di.AppComponent;
import app.com.movies.di.AppModule;
import app.com.movies.di.DaggerAppComponent;

public class MovieApplication extends Application {

    private static MovieApplication application;
    private AppComponent appComponent;

    public static MovieApplication getApplication() {
        return application;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        application = this;
        appComponent = DaggerAppComponent
                .builder()
                .appModule(new AppModule())
                .build();
        appComponent.inject(this);
    }

    public AppComponent getAppComponent() {
        return appComponent;
    }
}
