package app.com.movies.ui;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import java.util.List;

import app.com.movies.R;
import app.com.movies.adapter.BaseRecyclerAdapter;
import app.com.movies.adapter.MovieListAdapter;
import app.com.movies.response.MovieListDataResponse;
import app.com.movies.viewmodel.MovieViewModel;
import butterknife.BindView;
import butterknife.ButterKnife;

import static app.com.movies.util.MovieConstants.MOVIE_LIST_KEY;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.movie_list_recycler_view)
    RecyclerView recyclerView;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    private MovieListAdapter adapter;
    MovieViewModel movieViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);



        movieViewModel = ViewModelProviders.of(this).get(MovieViewModel.class);
        fetchMovies( movieViewModel);
    }

    private void fetchMovies( MovieViewModel movieViewModel) {

        movieViewModel.getPopularMovies().observe(this, new Observer<List<MovieListDataResponse>>() {
            @Override
            public void onChanged(@Nullable List<MovieListDataResponse> movieListDataResponses) {
                initAdapter(movieListDataResponses);
            }
        });

    }

    private void initAdapter(List<MovieListDataResponse> movieListData) {

        RecyclerView.LayoutManager horizontalLayoutManagaer = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(horizontalLayoutManagaer);
        adapter = new MovieListAdapter(getApplicationContext());
        recyclerView.setAdapter(adapter);
        adapter.setMovieData(movieListData);
        adapter.setOnItemClickListener(new BaseRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view1, int position, BaseRecyclerAdapter.ViewHolder viewHolder) {
                MovieListDataResponse item = adapter.getMovieItem(position);
                callMovieList(item);
            }
        });

    }

    private void callMovieList(MovieListDataResponse item) {
        Intent intent = new Intent(this, MovieDetailActivity.class);
        intent.putExtra(MOVIE_LIST_KEY, item);
        startActivity(intent);
    }


}
