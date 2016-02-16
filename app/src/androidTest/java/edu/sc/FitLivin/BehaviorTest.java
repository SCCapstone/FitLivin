package edu.sc.FitLivin;

/**
 * Created by Owner on 2/2/2016.
 */

import android.support.test.espresso.Espresso;
import android.support.test.espresso.ViewAction;
import android.support.test.espresso.action.ViewActions;
import android.support.test.espresso.assertion.ViewAssertions;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;//test

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.matcher.ViewMatchers.isClickable;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.pressImeActionButton;
import static android.support.test.espresso.action.ViewActions.pressBack;


@RunWith(AndroidJUnit4.class)

public class BehaviorTest {



    @Rule public final ActivityTestRule<MainActivity> behavior = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void ShouldBeAbleToVerifyNameInProfilePage(){
        String na = "randon3";
        Espresso.onView(ViewMatchers.withId(R.id.profileButton)).perform(ViewActions.click());
        onView(withId(R.id.name)).check(ViewAssertions.matches(withText(na)));
    }
    @Test
    public void ShouldBeAbleToVerifyLowCaloriesForMaintain(){
        Integer na = 3230;
        String s = na.toString();
        Espresso.onView(ViewMatchers.withId(R.id.nutritionButton)).perform(ViewActions.click());
        Espresso.onView(ViewMatchers.withId(R.id.maintainButton)).perform(ViewActions.click());
        onView(withId(R.id.lowCalories)).check(ViewAssertions.matches(withText("3230")));
    }

    @Test
    public void CheckToSeeIfButtonWorksOnFitnessProgramFragment() {
        Espresso.onView(withId(R.id.StrengthTrainingButton)).perform(ViewActions.click());
        Espresso.onView(withId(R.id.BodyBuildingButton)).check(matches(isClickable()));
        Espresso.onView(withId(R.id.WeightLossButton)).check(matches(isClickable()));
        Espresso.pressBack();
    }
    @Test
    public void CheckLoginPage(){
        String username = "lawsonz@email.sc.edu";
        String password = "abcdefg";
        Espresso.onView(withId(R.id.login_username_input)).perform(typeText(username));
        Espresso.onView(withId(R.id.login_password_input)).perform(typeText(password));
        Espresso.onView(withId(R.id.email_login_button)).perform(ViewActions.click());

    }
}
