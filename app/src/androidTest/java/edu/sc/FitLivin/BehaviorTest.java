/******
 * Class 'BehaviorTest'
 *
 * Behavior test for the application
 *
 */

package edu.sc.FitLivin;



import android.os.SystemClock;
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

import com.parse.ParseUser;

import org.hamcrest.Matcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;//test

import java.security.KeyRep;

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
        String na = ParseUser.getCurrentUser().getUsername();
        Espresso.onView(ViewMatchers.withId(R.id.uName)).perform(ViewActions.click());
        Espresso.onView(withId(R.id.name)).check(ViewAssertions.matches(withText(na)));
    }
    @Test
    public void ShouldbeAbleVerifyMeunIconsWork(){
        Espresso.onView(withId(R.id.home)).perform(ViewActions.click());
        Espresso.onView(withText("Profile")).perform(ViewActions.click());
        Espresso.onView(withId(R.id.home)).perform(ViewActions.click());
        Espresso.onView(withText("Points")).perform(ViewActions.click());
        Espresso.onView(withId(R.id.uName)).perform(ViewActions.click());

    }
    @Test
    public void ShouldbeAbleVerifyGoals(){
       
        Espresso.onView(withId(R.id.home)).perform(ViewActions.click());
        Espresso.onView(withText("Goals")).perform(ViewActions.click());
        Espresso.onView(withId(R.id.setWeight)).perform(ViewActions.scrollTo(), click());
        Espresso.onView(withText("SET")).perform(ViewActions.click());
        Espresso.onView(withId(R.id.setWeightG)).perform(ViewActions.scrollTo(), click());
        Espresso.onView(withText("SET")).perform(ViewActions.click());
        Espresso.onView(withId(R.id.setBench)).perform(ViewActions.scrollTo(), click());
        Espresso.onView(withText("SET")).perform(ViewActions.click());
        Espresso.onView(withId(R.id.setSquat)).perform(ViewActions.scrollTo(), click());
        Espresso.onView(withText("SET")).perform(ViewActions.click());
        Espresso.onView(withId(R.id.setDeadLift)).perform(ViewActions.scrollTo(), click());
        Espresso.onView(withText("SET")).perform(ViewActions.click());
        Espresso.onView(withId(R.id.setMileTime)).perform(ViewActions.scrollTo(), click());
        Espresso.onView(withText("SET")).perform(ViewActions.click());

    }

    @Test
    public void ShouldbeAbleVerifyNutrition(){
        Espresso.onView(withId(R.id.home)).perform(ViewActions.click());
        Espresso.onView(withText("Nutrition")).perform(ViewActions.click());
        Espresso.onView(withId(R.id.maintainButton)).perform(ViewActions.click());
        Espresso.onView(withId(R.id.wGainButton)).perform(ViewActions.click());
        Espresso.onView(withId(R.id.wlossButton)).perform(ViewActions.click());
       // Espresso.onView(withId(R.id.highCalories)).check(ViewAssertions.matches(withText("3291")));
    }
    @Test
    public void ShouldbeAbleVerifyTrackProgress(){
        Espresso.onView(withId(R.id.home)).perform(ViewActions.click());
        Espresso.onView(withText("Track Progress")).perform(ViewActions.click());
        Espresso.onView(withId(R.id.spinner_dropdown)).perform(ViewActions.click());
        Espresso.onView(withText("Max Bench")).perform(ViewActions.click());
        Espresso.onView(withId(R.id.spinner_dropdown)).perform(ViewActions.click());
        Espresso.onView(withText("Weight")).perform(ViewActions.click());
        Espresso.onView(withId(R.id.spinner_dropdown)).perform(ViewActions.click());
        Espresso.onView(withText("Max Squats")).perform(ViewActions.click());
        Espresso.onView(withId(R.id.spinner_dropdown)).perform(ViewActions.click());
        Espresso.onView(withText("Max Deadlifts")).perform(ViewActions.click());
        Espresso.onView(withId(R.id.spinner_dropdown)).perform(ViewActions.click());
        Espresso.onView(withText("Max MileTime")).perform(ViewActions.click());

    }
    @Test
    public void ShouldbeAbleVerifyStopwatch(){
        Espresso.onView(withId(R.id.home)).perform(ViewActions.click());
        Espresso.onView(withText("Stop Watch")).perform(ViewActions.click());
        Espresso.onView(withId(R.id.start)).perform(ViewActions.click());
        SystemClock.sleep(8000);
        Espresso.onView(withId(R.id.stop)).perform(ViewActions.click());
        Espresso.onView(withId(R.id.reset)).perform(ViewActions.click());
    }

    @Test
    public void ShouldbeAbleVerifyBBday5(){
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
    public void ShouldbeAbleVerifyBBday4(){
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
    public void ShouldbeAbleVerifyBBday3(){
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
    public void ShouldbeAbleVerifyBBday2(){
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
    public void ShouldbeAbleVerifyBBday1(){
        Espresso.onView(withId(R.id.home)).perform(ViewActions.click());
        Espresso.onView(withText("Fitness Program")).perform(ViewActions.click());
        Espresso.onView(withId(R.id.BodyBuildingButton)).perform(ViewActions.click());
        Espresso.onView(withId(R.id.day1BodyBuilding)).perform(ViewActions.click());
        Espresso.onView(withId(R.id.squatImage)).perform(ViewActions.scrollTo(), click());
        Espresso.onView(withId(R.id.legExtensionImage)).perform(ViewActions.scrollTo(), click());
        Espresso.onView(withId(R.id.curlImage)).perform(ViewActions.scrollTo(), click());
        Espresso.onView(withId(R.id.completeDay1bb)).perform(ViewActions.scrollTo(), click());
    }

    @Test
    public void ShouldbeAbleVerifyWLD1(){
        Espresso.onView(withId(R.id.home)).perform(ViewActions.click());
        Espresso.onView(withText("Fitness Program")).perform(ViewActions.click());
        Espresso.onView(withId(R.id.WeightLossButton)).perform(ViewActions.click());
        Espresso.onView(withId(R.id.day1WeightLoss)).perform(ViewActions.click());
        Espresso.onView(withId(R.id.runningImage)).perform(ViewActions.scrollTo(), click());
        Espresso.onView(withId(R.id.lungesImage)).perform(ViewActions.scrollTo(), click());
        Espresso.onView(withId(R.id.jumpRopeImage)).perform(ViewActions.scrollTo(), click());
        Espresso.onView(withId(R.id.completeDay1w)).perform(ViewActions.scrollTo(), click());
    }


    @Test
    public void ShouldbeAbleVerifyWLD2(){
        Espresso.onView(withId(R.id.home)).perform(ViewActions.click());
        Espresso.onView(withText("Fitness Program")).perform(ViewActions.click());
        Espresso.onView(withId(R.id.WeightLossButton)).perform(ViewActions.click());
        Espresso.onView(withId(R.id.day2WeightLoss)).perform(ViewActions.click());
        Espresso.onView(withId(R.id.cycleImage)).perform(ViewActions.scrollTo(), click());
        Espresso.onView(withId(R.id.squatImage)).perform(ViewActions.scrollTo(), click());
        Espresso.onView(withId(R.id.plankImage)).perform(ViewActions.scrollTo(), click());
        Espresso.onView(withId(R.id.completeDay2w)).perform(ViewActions.scrollTo(), click());
    }
    @Test
    public void ShouldbeAbleVerifyWLD3(){
        Espresso.onView(withId(R.id.home)).perform(ViewActions.click());
        Espresso.onView(withText("Fitness Program")).perform(ViewActions.click());
        Espresso.onView(withId(R.id.WeightLossButton)).perform(ViewActions.click());
        Espresso.onView(withId(R.id.day3WeightLoss)).perform(ViewActions.click());
        Espresso.onView(withId(R.id.cycleImage)).perform(ViewActions.scrollTo(), click());
        Espresso.onView(withId(R.id.pullupImage)).perform(ViewActions.scrollTo(), click());
        Espresso.onView(withId(R.id.burpeesImage)).perform(ViewActions.scrollTo(), click());
        Espresso.onView(withId(R.id.completeDay3w)).perform(ViewActions.scrollTo(), click());
    }
    @Test
    public void ShouldbeAbleVerifyWLD4(){
        Espresso.onView(withId(R.id.home)).perform(ViewActions.click());
        Espresso.onView(withText("Fitness Program")).perform(ViewActions.click());
        Espresso.onView(withId(R.id.WeightLossButton)).perform(ViewActions.click());
        Espresso.onView(withId(R.id.day4WeightLoss)).perform(ViewActions.click());
        Espresso.onView(withId(R.id.jogginImage)).perform(ViewActions.scrollTo(), click());
        Espresso.onView(withId(R.id.rowImage)).perform(ViewActions.scrollTo(), click());
        Espresso.onView(withId(R.id.cardioImage)).perform(ViewActions.scrollTo(), click());
        Espresso.onView(withId(R.id.completeDay4w)).perform(ViewActions.scrollTo(), click());
    }
    @Test
    public void ShouldbeAbleVerifyWLD5(){
        Espresso.onView(withId(R.id.home)).perform(ViewActions.click());
        Espresso.onView(withText("Fitness Program")).perform(ViewActions.click());
        Espresso.onView(withId(R.id.WeightLossButton)).perform(ViewActions.click());
        Espresso.onView(withId(R.id.day5WeightLoss)).perform(ViewActions.click());
        Espresso.onView(withId(R.id.runningImage)).perform(ViewActions.scrollTo(), click());
        Espresso.onView(withId(R.id.powerjumpImage)).perform(ViewActions.scrollTo(), click());
        Espresso.onView(withId(R.id.burpeesImage)).perform(ViewActions.scrollTo(), click());
        Espresso.onView(withId(R.id.completeDay5w)).perform(ViewActions.scrollTo(), click());
    }
    @Test
    public void ShouldbeAbleVerifyStrengthDay1(){
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
    public void ShouldbeAbleVerifyStrengthDay2(){
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
    public void ShouldbeAbleVerifyStrengthDay3(){
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
    public void ShouldbeAbleVerifyStrengthDay4(){
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
    public void ShouldbeAbleVerifyStrengthDay5(){
        Espresso.onView(withId(R.id.home)).perform(ViewActions.click());
        Espresso.onView(withText("Fitness Program")).perform(ViewActions.click());
        Espresso.onView(withId(R.id.StrengthTrainingButton)).perform(ViewActions.click());
        Espresso.onView(withId(R.id.day5Strength)).perform(ViewActions.click());
        Espresso.onView(withId(R.id.closeGripBenchImage)).perform(ViewActions.scrollTo(), click());
        Espresso.onView(withId(R.id.squatImage)).perform(ViewActions.scrollTo(), click());
        Espresso.onView(withId(R.id.legpresspic)).perform(ViewActions.scrollTo(), click());
        Espresso.onView(withId(R.id.completeDay5s)).perform(ViewActions.scrollTo(), click());

    }




}
