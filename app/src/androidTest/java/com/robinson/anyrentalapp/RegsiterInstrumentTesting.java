package com.robinson.anyrentalapp;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import com.robinson.anyrentalapp.UI.SignupActivity;
import com.robinson.anyrentalapp.UI.UpdateProfile;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.closeSoftKeyboard;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.RootMatchers.withDecorView;
import static androidx.test.espresso.matcher.ViewMatchers.isChecked;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.not;

@RunWith(AndroidJUnit4.class)
public class RegsiterInstrumentTesting {
    @Rule
    public ActivityTestRule<SignupActivity> testRule = new ActivityTestRule<>(SignupActivity.class);


    @Test
    public void testRegister() {
        onView(withId(R.id.et_fname)).perform(typeText("Instrument"));
        onView(withId(R.id.et_lname)).perform(typeText("Testing"));
        onView(withId(R.id.et_email)).perform(typeText("il@gmail.com"));
        closeSoftKeyboard();
        onView(withId(R.id.el_password)).perform(typeText("password"));
        closeSoftKeyboard();
        onView(withId(R.id.checkBox)).perform(click());
        closeSoftKeyboard();
        onView(withId(R.id.checkBox2)).perform(click());
        closeSoftKeyboard();
        onView(withId(R.id.btnsignup)).perform(click());
        onView(withText("Successfully registered !"))
                .inRoot(withDecorView(not(testRule.getActivity().getWindow().getDecorView()))).check(matches(isDisplayed()));

    }
}
