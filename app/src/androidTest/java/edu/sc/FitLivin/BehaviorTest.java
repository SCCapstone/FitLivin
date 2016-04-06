package edu.sc.FitLivin;

/**
 * Created by Owner on 2/2/2016.
 */

import android.provider.ContactsContract;
import android.provider.MediaStore;
import android.support.test.espresso.Espresso;
import android.support.test.espresso.ViewAction;
import android.support.test.espresso.action.ViewActions;
import android.support.test.espresso.assertion.ViewAssertions;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.text.style.ClickableSpan;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import org.hamcrest.Matcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;//test

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.matcher.ViewMatchers.assertThat;
import static android.support.test.espresso.matcher.ViewMatchers.isChecked;
import static android.support.test.espresso.matcher.ViewMatchers.isClickable;
import static android.support.test.espresso.matcher.ViewMatchers.isDescendantOfA;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withChild;
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
        String na = "randon50";
        Espresso.onView(ViewMatchers.withId(R.id.uName)).perform(ViewActions.click());
        Espresso.onView(withId(R.id.name)).check(ViewAssertions.matches(withText(na)));
    }
    @Test
    public void ShouldBeAbleToVerifyCaloriesForMaintain(){
        Espresso.onView(withId(R.id.home)).perform(ViewActions.click());
        Espresso.onView(withText("Nutrition")).perform(ViewActions.click());
        Espresso.onView(withId(R.id.maintainButton)).perform(ViewActions.click());
        Espresso.onView(withId(R.id.wGainButton)).perform(ViewActions.click());
        Espresso.onView(withId(R.id.wlossButton)).perform(ViewActions.click());
       // Espresso.onView(withId(R.id.highCalories)).check(ViewAssertions.matches(withText("3291")));
    }

    @Test
    public void CheckToSeeIfButtonWorksOnFitnessProgramFragment() {

        Espresso.onView(withId(R.id.fitnessProgramButton)).perform(ViewActions.scrollTo(), click());

    }
  /*  @Test
        public FPSTRTrainingFragment checkStrengthTrainingButton(){
        Espresso.onData(withId(R.id.StrengthTrainingButton)).perform(ViewActions.click())
                .check(matches(isDisplayed()));
        return new FPSTRTrainingFragment();
    }
    @Test
    public FPBodyBuildingFragment checkFPbbButton(){
        Espresso.onData(withId(R.id.BodyBuildingButton)).perform(ViewActions.click());
        return new FPBodyBuildingFragment();
    }
    @Test
    public FPWGTLossFragment checkFPWGTLossFragment(){
        Espresso.onData(withId(R.id.WeightLossButton)).perform(ViewActions.click());
        return new FPWGTLossFragment();

    }


       /* Espresso.onData(withId(R.id.BodyBuildingButton)).check(matches(isClickable()))
                .check(matches(isDisplayed()));
              Espresso.onData(withId(R.id.WeightLossButton)).check(matches(isClickable()))
                .check(matches(isDisplayed()));
        Espresso.pressBack();*/

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
       // Espresso.onData(withId(R.id.forgotPassView)).perform(ViewActions.click())
              //  .check(matches(isDisplayed()));

    }

    @Test
    public void CheckBBday5(){
        Espresso.onView(withId(R.id.home)).perform(ViewActions.click());
        Espresso.onView(withText("Fitness Program")).perform(ViewActions.click());
        Espresso.onView(withId(R.id.BodyBuildingButton)).perform(ViewActions.click());
        Espresso.onView(withId(R.id.day5BodyBuilding)).perform(ViewActions.click());
        Espresso.onView(withId(R.id.home)).perform(ViewActions.click());
        Espresso.onView(withText("Fitness Program")).perform(ViewActions.click());
        Espresso.onView(withId(R.id.BodyBuildingButton)).perform(ViewActions.click());
        Espresso.onView(withId(R.id.day5BodyBuilding)).perform(ViewActions.click());
        Espresso.onView(withId(R.id.militaryPressImage)).perform(ViewActions.scrollTo(), click());
        Espresso.onView(withId(R.id.sideLatImage)).perform(ViewActions.scrollTo(), click());
        Espresso.onView(withId(R.id.shoulderpressImage)).perform(ViewActions.scrollTo(), click());
        Espresso.onView(withId(R.id.completeDay5bb)).perform(ViewActions.scrollTo(), click());

    }
    @Test
    public void CheckBBday4(){
        Espresso.onView(withId(R.id.home)).perform(ViewActions.click());
        Espresso.onView(withText("Fitness Program")).perform(ViewActions.click());
        Espresso.onView(withId(R.id.BodyBuildingButton)).perform(ViewActions.click());
        Espresso.onView(withId(R.id.day4BodyBuilding)).perform(ViewActions.click());
        Espresso.onView(withId(R.id.deadliftImage)).perform(ViewActions.scrollTo(), click());
        Espresso.onView(withId(R.id.pullupImage)).perform(ViewActions.scrollTo(), click());
        Espresso.onView(withId(R.id.dumbbellRowImage)).perform(ViewActions.scrollTo(), click());
        Espresso.onView(withId(R.id.completeDay4bb)).perform(ViewActions.scrollTo(), click());
                    }

    @Test
    public void CheckBBday3(){
        Espresso.onView(withId(R.id.home)).perform(ViewActions.click());
        Espresso.onView(withText("Fitness Program")).perform(ViewActions.click());
        Espresso.onView(withId(R.id.BodyBuildingButton)).perform(ViewActions.click());
        Espresso.onView(withId(R.id.day3BodyBuilding)).perform(ViewActions.click());
        Espresso.onView(withId(R.id.hammerCurlImage)).perform(ViewActions.scrollTo(), click());
        Espresso.onView(withId(R.id.preacherCurlImage)).perform(ViewActions.scrollTo(), click());
        Espresso.onView(withId(R.id.closegrippushupImage)).perform(ViewActions.scrollTo(), click());
        Espresso.onView(withId(R.id.completeDay3bb)).perform(ViewActions.scrollTo(), click());

    }
    @Test
    public void CheckBBday2(){
        Espresso.onView(withId(R.id.home)).perform(ViewActions.click());
        Espresso.onView(withText("Fitness Program")).perform(ViewActions.click());
        Espresso.onView(withId(R.id.BodyBuildingButton)).perform(ViewActions.click());
        Espresso.onView(withId(R.id.day2BodyBuilding)).perform(ViewActions.click());
        Espresso.onView(withId(R.id.benchImage)).perform(ViewActions.scrollTo(), click());
        Espresso.onView(withId(R.id.flyImage)).perform(ViewActions.scrollTo(), click());
        Espresso.onView(withId(R.id.pushupImage)).perform(ViewActions.scrollTo(), click());
        Espresso.onView(withId(R.id.completeDay2bb)).perform(ViewActions.scrollTo(), click());
    }

    @Test
    public void CheckBBday1(){
        Espresso.onView(withId(R.id.home)).perform(ViewActions.click());
        Espresso.onView(withText("Fitness Program")).perform(ViewActions.click());
        Espresso.onView(withId(R.id.BodyBuildingButton)).perform(ViewActions.click());
        Espresso.onView(withId(R.id.day1BodyBuilding)).perform(ViewActions.click());
        Espresso.onView(withId(R.id.squatImage)).perform(ViewActions.scrollTo(), click());
        Espresso.onView(withId(R.id.legExtensionImage)).perform(ViewActions.scrollTo(), click());
        Espresso.onView(withId(R.id.curlImage)).perform(ViewActions.scrollTo(), click());
        Espresso.onView(withId(R.id.completeDay1bb)).perform(ViewActions.scrollTo(), click());
    }
    //need to complete
    @Test
    public void CheckEditProfile(){
        String username = "school@email.sc.edu";
        String gender = "male";
        String password = "abcdefg";
        String phone = "867-5309";


        Espresso.onData(withId(R.id.editnametext)).perform(typeText(username));
        Espresso.onData(withId(R.id.editpasstext)).perform(typeText(password));
        Espresso.onData(withId(R.id.editphone)).perform(typeText(phone));
        Espresso.onData(withId(R.id.savebuttoneditprofile)).perform(ViewActions.click())
                .check(matches(isDisplayed()));
        Espresso.onData(withId(R.id.exiteditprofile)).perform(ViewActions.click())
                .check(matches(isDisplayed()));

    }


  /*  @Test
    public BodyBuildingDayOne bb1Test(){
        Espresso.onData(withId(R.id.day1BodyBuilding)).perform(ViewActions.scrollTo(), click());
        return new BodyBuildingDayOne();
    }
    @Test
    public BodyBuildingDayTwo bb2Test(){
        Espresso.onData(withId(R.id.day2BodyBuilding)).perform(ViewActions.scrollTo(), click());
        return new BodyBuildingDayTwo();
    }
    @Test
    public BodyBuildingDayThree bb3Test(){
        Espresso.onData(withId(R.id.day3BodyBuilding)).perform(ViewActions.scrollTo(), click());
        return new BodyBuildingDayThree();
    }
    @Test
    public BodyBuildingDayFour bb4Test(){
        Espresso.onData(withId(R.id.day4BodyBuilding)).perform(ViewActions.scrollTo(), click());
        return new BodyBuildingDayFour();
    }*/
   /* @Test
    public BodyBuildingDayFive bb5Test(){
        Espresso.onData(withId(R.id.day5BodyBuilding)).perform(ViewActions.scrollTo(), click());
        return new BodyBuildingDayFive();
    }*/

  /*  @Test
    public StrengthDayOne st1Test(){
        Espresso.onData(withId(R.id.day1Strength)).perform(ViewActions.scrollTo(), click());
        return new StrengthDayOne();
    }
    @Test
    public StrengthDayTwo st2Test(){
        Espresso.onData(withId(R.id.day2Strength)).perform(ViewActions.scrollTo(),click());
        return new StrengthDayTwo();
    }
    @Test
    public StrengthDayThree st3Test(){
        Espresso.onData(withId(R.id.day3Strength)).perform(ViewActions.scrollTo(),click());
        return new StrengthDayThree();
    }
    @Test
    public StrengthDayFour st4Test(){
        Espresso.onData(withId(R.id.day4Strength)).perform(ViewActions.scrollTo(),click());
        return new StrengthDayFour();
    }
    @Test
    public StrengthDayFive st5Test(){
        Espresso.onData(withId(R.id.day5Strength)).perform(ViewActions.scrollTo(), click());
        return new StrengthDayFive();
    }*/
    @Test
    public void weightlossCheck(){
        Espresso.onData(withId(R.id.day1WeightLoss)).perform(ViewActions.click())
                .check(matches(isDisplayed()));
        Espresso.onData(withId(R.id.day2WeightLoss)).perform(ViewActions.click())
                .check(matches(isDisplayed()));
        Espresso.onData(withId(R.id.day3WeightLoss)).perform(ViewActions.click())
                .check(matches(isDisplayed()));
        Espresso.onData(withId(R.id.day4WeightLoss)).perform(ViewActions.click())
                .check(matches(isDisplayed()));
        Espresso.onData(withId(R.id.day5WeightLoss)).perform(ViewActions.click())
                .check(matches(isDisplayed()));
        Espresso.onData(withId(R.id.howTo)).perform(ViewActions.click())
                .check(matches(isDisplayed()));
    }
  /*  @Test
    public WeightLossDayOne wl1Test(){
        Espresso.onData(withId(R.id.day1WeightLoss)).perform(ViewActions.scrollTo(), click());
        return new WeightLossDayOne();
    }
    @Test
    public WeightLossDayTwo wl2Test(){
        Espresso.onData(withId(R.id.day2WeightLoss)).perform(ViewActions.scrollTo(),click());
        return new WeightLossDayTwo();
    }
    @Test
    public WeightLossDayThree wl3Test(){
        Espresso.onData(withId(R.id.day3WeightLoss)).perform(ViewActions.scrollTo(),click());
        return new WeightLossDayThree();
    }
    @Test
    public WeightLossDayFour wl4Test(){
        Espresso.onData(withId(R.id.day4WeightLoss)).perform(ViewActions.scrollTo(),click());
        return new WeightLossDayFour();
    }*/
   /* @Test
    public WeightLossDayFive wl5Test(){
        Espresso.onData(withId(R.id.day5WeightLoss)).perform(ViewActions.scrollTo(), click());
        return new WeightLossDayFive();
    }*/
    @Test
    public void goalCheck(){
        Espresso.onData(withId(R.id.setWeight)).perform(ViewActions.click())
                .check(matches(isDisplayed()));
        Espresso.onData(withId(R.id.setWeightG)).perform(ViewActions.click())
                .check(matches(isDisplayed()));
        Espresso.onData(withId(R.id.setBench)).perform(ViewActions.click())
                .check(matches(isDisplayed()));
        Espresso.onData(withId(R.id.setSquat)).perform(ViewActions.click())
                .check(matches(isDisplayed()));
        Espresso.onData(withId(R.id.DeadLiftGoal)).perform(ViewActions.click())
                .check(matches(isDisplayed()));
        Espresso.onData(withId(R.id.setMileTime)).perform(ViewActions.click())
                .check(matches(isDisplayed()));
    }
    @Test
    public void homepageCheck(){
        Espresso.onData(withId(R.id.pointsButton)).perform(ViewActions.scrollTo(),click());
        Espresso.onData(withId(R.id.profileButton)).perform(ViewActions.scrollTo(),click());
        Espresso.onData(withId(R.id.goalButton)).perform(ViewActions.scrollTo(),click());
        Espresso.onData(withId(R.id.trackProgressButton)).perform(ViewActions.scrollTo(), click());
        Espresso.onData(withId(R.id.bmiButton)).perform(ViewActions.scrollTo(), click());
        Espresso.onData(withId(R.id.nutritionButton)).perform(ViewActions.scrollTo(), click());
        Espresso.onData(withId(R.id.fitnessProgramButton)).perform(ViewActions.scrollTo(), click());
        Espresso.onData(withId(R.id.MaxButton)).perform(ViewActions.scrollTo(), click());
        Espresso.onData(withId(R.id.StopWatchButton)).perform(ViewActions.scrollTo(), click());
    }
    /*@Test
    public PointsPageFragment pointsTest(){
        Espresso.onData(withId(R.id.pointsButton)).perform(ViewActions.scrollTo(),click());
        return new PointsPageFragment();
    }
    @Test
    public ProfilePageFragment profileTest(){
        Espresso.onData(withId(R.id.profileButton)).perform(ViewActions.scrollTo(),click());
        return new ProfilePageFragment();
    }
    @Test
    public GoalFragment goalTest(){
        Espresso.onData(withId(R.id.pic)).perform(ViewActions.scrollTo(),click());
        return new GoalFragment();
    }
    @Test
    public TrackProgressFragment trackProgressTest(){
        Espresso.onData(withId(R.id.pic)).perform(ViewActions.scrollTo(),click());
        return new TrackProgressFragment();
    }
    @Test
    public BMICAL_Fragment bmiTest(){
        Espresso.onData(withId(R.id.pic)).perform(ViewActions.scrollTo(),click());
        return new BMICAL_Fragment();
    }
    @Test
    public NutritionCalFragment nutritionTest(){
        Espresso.onData(withId(R.id.pic)).perform(ViewActions.scrollTo(),click());
        return new NutritionCalFragment();
    }
    @Test
    public FitnessProgramFragment fitnessTest(){
        Espresso.onData(withId(R.id.pic)).perform(ViewActions.scrollTo(),click());
        return new FitnessProgramFragment();
    }
    @Test
    public MaxFragment maxTest(){
        Espresso.onData(withId(R.id.pic)).perform(ViewActions.scrollTo(),click());
        return new MaxFragment();
    }*/
    /*@Test
    public StopwatchFragment stopwatchTest(){
        Espresso.onData(withId(R.id.pic)).perform(ViewActions.scrollTo(),click());
        return new StopwatchFragment();
    }*/
    @Test
    public void maxcalcCheck(){
        Espresso.onData(withId(R.id.setBench)).perform(ViewActions.scrollTo(),click());
        Espresso.onData(withId(R.id.setSquat)).perform(ViewActions.scrollTo(),click());
        Espresso.onData(withId(R.id.setDeadLift)).perform(ViewActions.scrollTo(),click());
        Espresso.onData(withId(R.id.setMileTime)).perform(ViewActions.scrollTo(),click());
    }
    @Test
    public void nutritioncalcCheck(){
        Espresso.onData(withId(R.id.maintainButton)).perform(ViewActions.scrollTo(),click());
        Espresso.onData(withId(R.id.wlossButton)).perform(ViewActions.scrollTo(),click());
        Espresso.onData(withId(R.id.wGainButton)).perform(ViewActions.scrollTo(),click());
    }

    @Test
    public void profilepagecheck(){
        Espresso.onData(withId(R.id.pic)).perform(ViewActions.scrollTo(),click());
        Espresso.onData(withId(R.id.editprofilebutton)).perform(ViewActions.scrollTo(),click());
        Espresso.onData(withId(R.id.weightButton)).perform(ViewActions.scrollTo(),click());
    }
  /*  @Test
    public CameraActivity picTest(){
        Espresso.onData(withId(R.id.pic)).perform(ViewActions.scrollTo(),click());
        return new CameraActivity();
    }
    @Test
   public Editprofilefragment ediProfileTest(){
        Espresso.onData(withId(R.id.editprofilebutton)).perform(ViewActions.scrollTo(),click());
        return new Editprofilefragment();
    }*/
     @Test
      public void stopwatchcheck(){
        Espresso.onData(withId(R.id.start)).perform(ViewActions.scrollTo(),click());
        Espresso.onData(withId(R.id.stop)).perform(ViewActions.scrollTo(),click());
        Espresso.onData(withId(R.id.reset)).perform(ViewActions.scrollTo(),click());
    }
    @Test
    public void CheckWLD1(){
        Espresso.onView(withId(R.id.fitnessProgramButton)).perform(ViewActions.scrollTo(), click());
        Espresso.onData(withId(R.id.runningImage)).perform(ViewActions.scrollTo(), click())
                .check(matches(isDisplayed()));
        Espresso.onData(withId(R.id.lungesImage)).perform(ViewActions.scrollTo(), click());
        Espresso.onData(withId(R.id.jumpRopeImage)).perform(ViewActions.scrollTo(), click());
        Espresso.onData(withId(R.id.completeDay1w)).perform(ViewActions.scrollTo(), click());
    }


    @Test
    public void CheckWLD2(){
        Espresso.onView(withId(R.id.fitnessProgramButton)).perform(ViewActions.scrollTo(), click());
        Espresso.onData(withId(R.id.cycleImage)).perform(ViewActions.scrollTo(), click())
                .check(matches(isDisplayed()));
        Espresso.onData(withId(R.id.squatImage)).perform(ViewActions.scrollTo(), click());
        Espresso.onData(withId(R.id.plankImage)).perform(ViewActions.scrollTo(), click());
        Espresso.onData(withId(R.id.completeDay2w)).perform(ViewActions.scrollTo(), click());
    }
    @Test
    public void CheckWLD3(){
        Espresso.onView(withId(R.id.fitnessProgramButton)).perform(ViewActions.scrollTo(), click());
        Espresso.onData(withId(R.id.cycleImage)).perform(ViewActions.scrollTo(), click())
                .check(matches(isDisplayed()));
        Espresso.onData(withId(R.id.pullupImage)).perform(ViewActions.scrollTo(), click());
        Espresso.onData(withId(R.id.burpeesImage)).perform(ViewActions.scrollTo(), click());
        Espresso.onData(withId(R.id.completeDay3w)).perform(ViewActions.scrollTo(), click());
    }
    @Test
    public void CheckWLD4(){
        Espresso.onView(withId(R.id.fitnessProgramButton)).perform(ViewActions.scrollTo(), click());
        Espresso.onData(withId(R.id.jogginImage)).perform(ViewActions.scrollTo(), click())
                .check(matches(isDisplayed()));
        Espresso.onData(withId(R.id.rowImage)).perform(ViewActions.scrollTo(), click());
        Espresso.onData(withId(R.id.cardioImage)).perform(ViewActions.scrollTo(), click());
        Espresso.onData(withId(R.id.completeDay4w)).perform(ViewActions.scrollTo(), click());
    }
    @Test
    public void CheckWLD5(){
        Espresso.onView(withId(R.id.fitnessProgramButton)).perform(ViewActions.scrollTo(), click());
        Espresso.onData(withId(R.id.runningImage)).perform(ViewActions.scrollTo(), click())
                .check(matches(isDisplayed()));
        Espresso.onData(withId(R.id.powerjumpImage)).perform(ViewActions.scrollTo(), click());
        Espresso.onData(withId(R.id.burpeesImage)).perform(ViewActions.scrollTo(), click());
        Espresso.onData(withId(R.id.completeDay5w)).perform(ViewActions.scrollTo(), click());
    }
    @Test
    public void StrengthDay1(){
        Espresso.onView(withId(R.id.home)).perform(ViewActions.click());
        Espresso.onView(withText("Fitness Program")).perform(ViewActions.click());
        Espresso.onView(withId(R.id.StrengthTrainingButton)).perform(ViewActions.click());
        Espresso.onView(withId(R.id.day1Strength)).perform(ViewActions.click());
        Espresso.onView(withId(R.id.closeGripBenchImage)).perform(ViewActions.scrollTo(), click());
        Espresso.onView(withId(R.id.incline)).perform(ViewActions.scrollTo(), click());
        Espresso.onView(withId(R.id.flies)).perform(ViewActions.scrollTo(), click());
        Espresso.onView(withId(R.id.completeDay1s)).perform(ViewActions.scrollTo(), click());
    }
    @Test
    public void StrengthDay2(){
        Espresso.onView(withId(R.id.home)).perform(ViewActions.click());
        Espresso.onView(withText("Fitness Program")).perform(ViewActions.click());
        Espresso.onView(withId(R.id.StrengthTrainingButton)).perform(ViewActions.click());
        Espresso.onView(withId(R.id.day2Strength)).perform(ViewActions.click());
        Espresso.onView(withId(R.id.squatImage)).perform(ViewActions.scrollTo(), click());
        Espresso.onView(withId(R.id.legpresspic)).perform(ViewActions.scrollTo(), click());
        Espresso.onView(withId(R.id.calfpresspic)).perform(ViewActions.scrollTo(), click());
        Espresso.onView(withId(R.id.completeDay2s)).perform(ViewActions.scrollTo(), click());
    }
    @Test
    public void StrengthDay3(){
        Espresso.onView(withId(R.id.home)).perform(ViewActions.click());
        Espresso.onView(withText("Fitness Program")).perform(ViewActions.click());
        Espresso.onView(withId(R.id.StrengthTrainingButton)).perform(ViewActions.click());
        Espresso.onView(withId(R.id.day3Strength)).perform(ViewActions.click());
        Espresso.onView(withId(R.id.deadliftImage)).perform(ViewActions.scrollTo(), click());
        Espresso.onView(withId(R.id.rowImage)).perform(ViewActions.scrollTo(), click());
        Espresso.onView(withId(R.id.shrugpic)).perform(ViewActions.scrollTo(), click());
        Espresso.onView(withId(R.id.completeDay3s)).perform(ViewActions.scrollTo(), click());
    }
    @Test
    public void StrengthDay4(){
        Espresso.onView(withId(R.id.home)).perform(ViewActions.click());
        Espresso.onView(withText("Fitness Program")).perform(ViewActions.click());
        Espresso.onView(withId(R.id.StrengthTrainingButton)).perform(ViewActions.click());
        Espresso.onView(withId(R.id.day4Strength)).perform(ViewActions.click());
        Espresso.onView(withId(R.id.closeGripBenchImage)).perform(ViewActions.scrollTo(), click());
        Espresso.onView(withId(R.id.bbcurl)).perform(ViewActions.scrollTo(), click());
        Espresso.onView(withId(R.id.pushdown)).perform(ViewActions.scrollTo(), click());
        Espresso.onView(withId(R.id.completeDay4s)).perform(ViewActions.scrollTo(), click());
    }
    @Test
    public void StrengthDay5(){
        Espresso.onView(withId(R.id.home)).perform(ViewActions.click());
        Espresso.onView(withText("Fitness Program")).perform(ViewActions.click());
        Espresso.onView(withId(R.id.StrengthTrainingButton)).perform(ViewActions.click());
        Espresso.onView(withId(R.id.day5Strength)).perform(ViewActions.click());
        Espresso.onView(withId(R.id.closeGripBenchImage)).perform(ViewActions.scrollTo(), click());
        Espresso.onView(withId(R.id.squatImage)).perform(ViewActions.scrollTo(), click());
        Espresso.onView(withId(R.id.legpresspic)).perform(ViewActions.scrollTo(), click());
        Espresso.onView(withId(R.id.completeDay5s)).perform(ViewActions.scrollTo(), click());
        Espresso.onView(withId(R.id.howTo)).perform(ViewActions.scrollTo(), click());
    }




}
