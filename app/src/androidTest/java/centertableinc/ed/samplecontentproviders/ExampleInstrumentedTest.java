package centertableinc.ed.samplecontentproviders;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import centertableinc.ed.samplecontentproviders.data.Movie;
import centertableinc.ed.samplecontentproviders.data.ProviderUtil;

import static org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */

@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    @Test
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();

        assertEquals("centertableinc.ed.samplecontentproviders", appContext.getPackageName());
    }

    @Test
    public void insertMovie(){
        Context appContext = InstrumentationRegistry.getTargetContext();
        ProviderUtil util = new ProviderUtil();

        util.insertMovie(appContext, new Movie("001","TestMovie1"));

        //See if it is inserted successfully
        Movie movie = util.getMovie(appContext, "001");

        assertNotEquals(null, movie);
        assertEquals("001", movie.getMovieId());
    }

    @Test
    public void firstInsertThenDeleteMovie(){
        Context appContext = InstrumentationRegistry.getTargetContext();
        ProviderUtil util = new ProviderUtil();
        Movie testMovie = new Movie("001","TestMovie1");

        util.insertMovie(appContext, testMovie);
        //See if it is inserted successfully
        Movie movie = util.getMovie(appContext, "001");

        assertNotEquals(null, movie);
        assertEquals("001", movie.getMovieId());

        util.deleteMovie(appContext, testMovie);

        //See if it is deleted successfully
        movie = util.getMovie(appContext, "001");

        assertEquals(null, movie);

    }
}
