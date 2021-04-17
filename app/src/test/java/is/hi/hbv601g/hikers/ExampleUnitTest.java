package is.hi.hbv601g.hikers;

import org.junit.Test;

import static org.junit.Assert.*;

import is.hi.hbv601g.hikers.Entities.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }

    @Test
    // Profile class can create an empty object
    public void subraction_isCorrect() {
        Profile profile = new Profile();
        boolean actualAnswer = profile.isEmpty();
        assertTrue(actualAnswer);
    }
}