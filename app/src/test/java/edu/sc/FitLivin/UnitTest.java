/******
 * Class 'UnitTest'
 *
 * Unit test for the application
 *
 */

package edu.sc.FitLivin;

import android.app.Application;
import android.test.ApplicationTestCase;

import com.parse.ParseQuery;
import com.parse.ParseUser;

import org.junit.Test;

import edu.sc.FitLivin.BMICAL_Fragment;
import edu.sc.FitLivin.NutritionCalFragment;
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

    @Test
    public void test3() throws Exception {
        NutritionCalFragment nut = new NutritionCalFragment();
        Integer test = 100;
        Integer answer = 1350;
        Integer testAnswer = nut.MaleWeightLossM(test);
        assertEquals(answer,testAnswer);



    }

    @Test
    public void test4() throws Exception {
        NutritionCalFragment nut = new NutritionCalFragment();
        Integer test = 100;
        Integer answer = 1750;
        Integer testAnswer = nut.MaleWeightLossH(test);
        assertEquals(answer,testAnswer);



    }

    @Test
    public void test5() throws Exception {
        NutritionCalFragment nut = new NutritionCalFragment();
        Integer test = 100;
        Integer answer = 1050;
        Integer testAnswer = nut.FemaleWeightLossL(test);
        assertEquals(answer,testAnswer);



    }


    @Test
    public void test6() throws Exception {
        NutritionCalFragment nut = new NutritionCalFragment();
        Integer test = 100;
        Integer answer = 1150;
        Integer testAnswer = nut.FemaleWeightLossM(test);
        assertEquals(answer,testAnswer);



    }


    @Test
    public void test7() throws Exception {
        NutritionCalFragment nut = new NutritionCalFragment();
        Integer test = 100;
        Integer answer = 1450;
        Integer testAnswer = nut.FemaleWeightLossH(test);
        assertEquals(answer,testAnswer);



    }


    @Test
    public void test8() throws Exception {
        NutritionCalFragment nut = new NutritionCalFragment();
        Integer test = 100;
        Integer answer = 1700;
        Integer testAnswer = nut.MaleWeightMaintainL(test);
        assertEquals(answer,testAnswer);



    }

    @Test
    public void test9() throws Exception {
        NutritionCalFragment nut = new NutritionCalFragment();
        Integer test = 100;
        Integer answer = 1900;
        Integer testAnswer = nut.MaleWeightMaintainM(test);
        assertEquals(answer,testAnswer);



    }

    @Test
    public void test10() throws Exception {
        NutritionCalFragment nut = new NutritionCalFragment();
        Integer test = 100;
        Integer answer = 2300;
        Integer testAnswer = nut.MaleWeightMaintainH(test);
        assertEquals(answer,testAnswer);



    }

    @Test
    public void test11() throws Exception {
        NutritionCalFragment nut = new NutritionCalFragment();
        Integer test = 100;
        Integer answer = 1600;
        Integer testAnswer = nut.FemaleWeightMaintainL(test);
        assertEquals(answer,testAnswer);



    }

    @Test
    public void test12() throws Exception {
        NutritionCalFragment nut = new NutritionCalFragment();
        Integer test = 100;
        Integer answer = 1700;
        Integer testAnswer = nut.FemaleWeightMaintainM(test);
        assertEquals(answer,testAnswer);



    }

    @Test
    public void test13() throws Exception {
        NutritionCalFragment nut = new NutritionCalFragment();
        Integer test = 100;
        Integer answer = 2000;
        Integer testAnswer = nut.FemaleWeightMaintainH(test);
        assertEquals(answer,testAnswer);



    }

    @Test
    public void test14() throws Exception {
        NutritionCalFragment nut = new NutritionCalFragment();
        Integer test = 100;
        Integer answer = 2450;
        Integer testAnswer = nut.MaleWeightGainL(test);
        assertEquals(answer,testAnswer);



    }



    @Test
    public void test15() throws Exception {
        NutritionCalFragment nut = new NutritionCalFragment();
        Integer test = 100;
        Integer answer = 2650;
        Integer testAnswer = nut.MaleWeightGainM(test);
        assertEquals(answer,testAnswer);



    }

    @Test
    public void test16() throws Exception {
        NutritionCalFragment nut = new NutritionCalFragment();
        Integer test = 100;
        Integer answer = 3050;
        Integer testAnswer = nut.MaleWeightGainH(test);
        assertEquals(answer,testAnswer);



    }

    @Test
    public void test17() throws Exception {
        NutritionCalFragment nut = new NutritionCalFragment();
        Integer test = 100;
        Integer answer = 2350;
        Integer testAnswer = nut.FemaleWeightGainL(test);
        assertEquals(answer,testAnswer);



    }

    @Test
    public void test18() throws Exception {
        NutritionCalFragment nut = new NutritionCalFragment();
        Integer test = 100;
        Integer answer = 2450;
        Integer testAnswer = nut.FemaleWeightGainM(test);
        assertEquals(answer,testAnswer);



    }

    @Test
    public void test19() throws Exception {
        NutritionCalFragment nut = new NutritionCalFragment();
        Integer test = 100;
        Integer answer = 2750;
        Integer testAnswer = nut.FemaleWeightGainH(test);
        assertEquals(answer,testAnswer);



    }


}