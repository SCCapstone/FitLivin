package edu.sc.FitLivin;

import android.app.Application;
import android.test.ApplicationTestCase;
import org.junit.Test;
import static org.junit.Assert.*;
import com.parse.ParseQuery;
import com.parse.ParseUser;

/**
 * <a href="http://d.android.com/tools/testing/testing_android.html">Testing Fundamentals</a>
 */
public class ApplicationTest extends ApplicationTestCase<Application> {
    public ApplicationTest() {
        super(Application.class);
    }
  @Test
    public void test1() throws Exception {
        //final int expected = 1;
       // final int reality = 5;
      //  assertEquals(expected, reality);
        ParseQuery query = ParseQuery.getQuery("ProfileInfo");
        assertEquals("randon3", ParseUser.getCurrentUser().getUsername());
    }
    public void test2() throws Exception {
        //final int expected = 1;
        // final int reality = 5;
        //  assertEquals(expected, reality);
        NutritionCalFragment nut = new NutritionCalFragment();
        Integer test = 100;
        Integer answer = 1150;
        Integer testAnser = nut.MaleWeightLoss(test);
        assertEquals(answer, testAnser);
    }

}