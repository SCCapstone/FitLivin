package edu.sc.FitLivin;

import android.app.Application;
import android.test.ApplicationTestCase;

import com.parse.ParseQuery;
import com.parse.ParseUser;

import org.junit.Test;

import edu.sc.FitLivin.BMICAL_Fragment;
import edu.sc.FitLivin.NutritionCalFragment;

/**
 * Created by Owner on 2/15/2016.
 */



import android.app.Application;
import android.test.ApplicationTestCase;
import org.junit.Test;
import static org.junit.Assert.*;
import com.parse.ParseQuery;
import com.parse.ParseUser;

/**
 * <a href="http://d.android.com/tools/testing/testing_android.html">Testing Fundamentals</a>
 */
public class UnitTest {
    public UnitTest() {

    }


    @Test
    public void test1() throws Exception {
        //final int expected = 1;
        // final int reality = 5;
        //  assertEquals(expected, reality);
        NutritionCalFragment nut = new NutritionCalFragment();
        Integer test = 100;
        Integer answer = 1150;
        Integer testAnswer = nut.MaleWeightLossL(test);
        assertEquals(answer, testAnswer);
    }
    @Test
    public void test2() throws Exception {

        BMICAL_Fragment BMI = new BMICAL_Fragment();
        float multiplier = 703;

        float weight = 150;
        float height = 72;

        float answer = (weight / (height * height));
        Float ansMul = (float) answer * multiplier;
        Float testAnswer = BMI.calculateBMI(weight, height);
        assertEquals(testAnswer,ansMul,.000000001);
    }


}