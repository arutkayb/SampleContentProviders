package centertableinc.ed.samplecontentproviders;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import centertableinc.ed.samplecontentproviders.data.Movie;
import centertableinc.ed.samplecontentproviders.data.ProviderUtil;

public class MainActivity extends AppCompatActivity {
    Button button_clear, button_save, button_get_saved, button_delete_saved;
    EditText text_movie_id, text_movie_name;

    ProviderUtil providerUtil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initializeView();
        setupButtonClickEvents();

        providerUtil = new ProviderUtil();
    }

    private void initializeView(){
        button_clear = findViewById(R.id.button_clear);
        button_save = findViewById(R.id.button_save);
        button_get_saved = findViewById(R.id.button_get_saved);
        button_delete_saved = findViewById(R.id.button_delete_saved);

        text_movie_id = findViewById(R.id.text_movie_id);
        text_movie_name = findViewById(R.id.text_movie_name);
    }

    private void setupButtonClickEvents(){
        button_clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearTexts();
                Toast.makeText(MainActivity.this, "Cleared but the Movie is NOT deleted from the db", Toast.LENGTH_SHORT).show();
            }
        });

        button_delete_saved.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                providerUtil.deleteMovie(getApplicationContext(), text_movie_id.getText().toString());
                clearTexts();
                Toast.makeText(MainActivity.this, "Movie deleted from the db", Toast.LENGTH_SHORT).show();
            }
        });

        button_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String movieId = text_movie_id.getText().toString();
                String movieName = text_movie_name.getText().toString();

                if(movieId.equals("") || movieName.equals("")) {
                    Toast.makeText(MainActivity.this, "Please fill both 2 movie fields", Toast.LENGTH_SHORT).show();
                }
                else {
                    providerUtil.insertMovie(getApplicationContext(), new Movie(movieId, movieName));
                    Toast.makeText(MainActivity.this, "Movie is inserted to the db", Toast.LENGTH_SHORT).show();
                }
            }
        });

        button_get_saved.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String movieId = text_movie_id.getText().toString();

                if(!movieId.equals("")) {
                    Movie movie = providerUtil.getMovie(getApplicationContext(), movieId);
                    if(movie != null)
                        text_movie_name.setText(movie.getMovieName());
                    else
                        Toast.makeText(MainActivity.this, "Cannot find the movie", Toast.LENGTH_SHORT).show();

                }
            }
        });
    }

    private void clearTexts(){
        text_movie_name.setText("");
        text_movie_id.setText("");
    }
}
