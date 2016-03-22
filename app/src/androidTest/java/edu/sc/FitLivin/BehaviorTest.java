package edu.sc.FitLivin;

/**
 * Created by Owner on 2/2/2016.
 */

import android.provider.ContactsContract;
import android.provider.MediaStore;
import android.support.test.espresso.Espresso;
import android.support.test.espresso.ViewAction;
import android.support.test.espresso.ViewAssertion;
import android.support.test.espresso.action.ViewActions;
import android.support.test.espresso.assertion.ViewAssertions;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.text.style.ClickableSpan;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;//test

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.matcher.ViewMatchers.assertThat;
import static android.support.test.espresso.matcher.ViewMatchers.isChecked;
import static android.support.test.espresso.matcher.ViewMatchers.isClickable;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
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
        String na = "randon10";
        Espresso.onView(ViewMatchers.withId(R.id.profileButton)).perform(ViewActions.click());
        Espresso.onView(withId(R.id.name)).check(ViewAssertions.matches(withText(na)));
    }
    @Test
    public void ShouldBeAbleToVerifyLowCaloriesForMaintain(){
        Integer na = 3230;
        String s = na.toString();
        Espresso.onData(ViewMatchers.withId(R.id.nutritionButton)).perform(ViewActions.click());
        Espresso.onData(ViewMatchers.withId(R.id.maintainButton)).perform(ViewActions.click());
        Espresso.onData(withId(R.id.lowCalories)).check(ViewAssertions.matches(withText("3230")));
    }

    @Test
    public void CheckToSeeIfButtonWorksOnFitnessProgramFragment() {

        Espresso.onView(withId(R.id.StrengthTrainingButton)).perform(ViewActions.click())
                .check(matches(isDisplayed()));
        Espresso.onData(withId(R.id.BodyBuildingButton)).check(matches(isClickable()))
                .check(matches(isDisplayed()));
              Espresso.onData(withId(R.id.WeightLossButton)).check(matches(isClickable()))
                .check(matches(isDisplayed()));
        Espresso.pressBack();
    }
    @Test
    public void CheckLoginPage(){
        String username = "school@email.sc.edu";
        String password = "abcdefg";
        Espresso.onData(withId(R.id.login_username_input)).perform(typeText(username));
        Espresso.onData(withId(R.id.login_password_input)).perform(typeText(password));
        Espresso.onData(withId(R.id.email_login_button)).perform(ViewActions.click())
                .check(matches(isDisplayed()));

    }

    @Test
    public void CheckSignUp(){
        String email_in = "school@email.sc.edu";
        String username_in = "schoolTime";
        String password_in = "abcdef";
        String retypepasswrd_in = "abcdef";
        String phonenumber_in = "867-5309";
        //need to find button test case

        Espresso.onData(withId(R.id.email_in)).perform(typeText(email_in));
        Espresso.onData(withId(R.id.username_in)).perform(typeText(username_in));
        Espresso.onData(withId(R.id.password_in)).perform(typeText(password_in));
        Espresso.onData(withId(R.id.retypepasswrd_in)).perform(typeText(retypepasswrd_in));
        Espresso.onData(withId(R.id.phonenumber_in)).perform(typeText(phonenumber_in));
        Espresso.onData(withId(R.id.email_signup_button_in)).perform(ViewActions.click()).check(matches(isDisplayed()));


        ;
    }

    @Test
    public void CheckSignUpOrLogin(){
        Espresso.onData(withId(R.id.email_login_button)).perform(ViewActions.click())
        .check(matches(isDisplayed()));
        Espresso.onData(withId(R.id.email_signin_button)).perform(ViewActions.click())
        .check(matches(isDisplayed()));
        Espresso.onData(withId(R.id.forgotPassView)).perform(ViewActions.click())
                .check(matches(isDisplayed()));

    }

    @Test
    public void CheckBBday5(){
        Espresso.onData(withId(R.id.militaryPressImage)).perform(ViewActions.click());
        Espresso.onData(withId(R.id.sideLatImage)).perform(ViewActions.click());
        Espresso.onData(withId(R.id.shoulderpressImage)).perform(ViewActions.click());
       // Espresso.onData(withId(R.id.BBBack)).perform(ViewActions.click())
         //   .check(matches(isDisplayed()));
        Espresso.onData(withId(R.id.completeDay5bb)).perform(ViewActions.click())
                .check(matches(isDisplayed()));
    }
    @Test
    public void CheckBBday4(){
        Espresso.onData(withId(R.id.deadliftImage)).perform(ViewActions.click());
        Espresso.onData(withId(R.id.pullupImage)).perform(ViewActions.click());
        Espresso.onData(withId(R.id.dumbbellRowImage)).perform(ViewActions.click());
      //  Espresso.onData(withId(R.id.BBBack)).perform(ViewActions.click())
            //    .check(matches(isDisplayed()));
        Espresso.onData(withId(R.id.completeDay4bb)).perform(ViewActions.click())
                .check(matches(isDisplayed()));
    }

    @Test
    public void CheckBBday3(){
        Espresso.onData(withId(R.id.hammerCurlImage)).perform(ViewActions.click());
        Espresso.onData(withId(R.id.preacherCurlImage)).perform(ViewActions.click());
        Espresso.onData(withId(R.id.closegrippushupImage)).perform(ViewActions.click());
     //   Espresso.onData(withId(R.id.BBBack)).perform(ViewActions.click())
     //           .check(matches(isDisplayed()));
        Espresso.onData(withId(R.id.completeDay3bb)).perform(ViewActions.click())
                .check(matches(isDisplayed()));
    }
    @Test
    public void CheckBBday2(){
        Espresso.onData(withId(R.id.benchImage)).perform(ViewActions.click());
        Espresso.onData(withId(R.id.flyImage)).perform(ViewActions.click());
        Espresso.onData(withId(R.id.pushupImage)).perform(ViewActions.click());
       // Espresso.onData(withId(R.id.BBBack)).perform(ViewActions.click())
       //         .check(matches(isDisplayed()));
        Espresso.onData(withId(R.id.completeDay2bb)).perform(ViewActions.click())
                .check(matches(isDisplayed()));
    }

    @Test
    public void CheckBBday1(){
        Espresso.onData(withId(R.id.squatImage)).perform(ViewActions.click());
        Espresso.onData(withId(R.id.legExtensionImage)).perform(ViewActions.click());
        Espresso.onData(withId(R.id.curlImage)).perform(ViewActions.click());
     //   Espresso.onData(withId(R.id.BBBack)).perform(ViewActions.click())
     //           .check(matches(isDisplayed()));
        Espresso.onData(withId(R.id.completeDay1bb)).perform(ViewActions.click())
                .check(matches(isDisplayed()));
    }
    //need to complete
    @Test
    public void CheckEditProfile(){
        String username = "school@email.sc.edu";
        String gender = "male";
        String password = "abcdefg";
        String phone = "867-5309";


        Espresso.onData(withId(R.id.editnametext)).perform(typeText(username));
//        Espresso.onData(withId(R.id.editgender)).perform(typeText(gender));
        Espresso.onData(withId(R.id.editpasstext)).perform(typeText(password));
        Espresso.onData(withId(R.id.editphone)).perform(typeText(phone));
        Espresso.onData(withId(R.id.savebuttoneditprofile)).perform(ViewActions.click())
                .check(matches(isDisplayed()));

    }
//Need to finish
    @Test
    public void checkStopWatch(){
        Espresso.onData(withId(R.id.start)).perform(ViewActions.click());
    }

}
