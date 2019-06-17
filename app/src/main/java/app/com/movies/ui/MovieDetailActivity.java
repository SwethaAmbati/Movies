package app.com.movies.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import app.com.movies.R;
import app.com.movies.response.MovieListDataResponse;
import butterknife.BindView;
import butterknife.ButterKnife;

import static app.com.movies.util.MovieConstants.MOVIE_LIST_KEY;

public class MovieDetailActivity extends AppCompatActivity {


    @BindView(R.id.details_title)
    TextView title;
    @BindView(R.id.details_released)
    TextView releasedDate;
    @BindView(R.id.details_overview_summary)
    TextView overviewSummary;
    @BindView(R.id.details_ratings)
    TextView ratings;

    MovieListDataResponse data;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);

        ButterKnife.bind(this);

        Bundle extras = getIntent().getExtras();
        if (extras != null && extras.containsKey(MOVIE_LIST_KEY))
            data = extras.getParcelable(MOVIE_LIST_KEY);

        if (data != null) {
            releasedDate.setText("Released Date: "+data.getReleaseDate());
            title.setText(data.getTitle());
            overviewSummary.setText(data.getOverview());
            ratings.setText("Ratings: " + data.getVoteAverage() + " out of 10");
        }
    }
}
