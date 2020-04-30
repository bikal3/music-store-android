package com.robinson.anyrentalapp;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import com.google.android.gms.maps.model.Dash;
import com.robinson.anyrentalapp.UI.UpdateProfile;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.closeSoftKeyboard;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static androidx.test.espresso.matcher.RootMatchers.withDecorView;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static org.hamcrest.Matchers.not;

@RunWith(AndroidJUnit4.class)
public class UpdateProfileIntrumentTesting {

    @Rule
    public ActivityTestRule<UpdateProfile> testRule = new ActivityTestRule<>(UpdateProfile.class);

    @Test
    public void testUpdateTrip(){
        onView(withId(R.id.et_update_fname)).perform(replaceText(""));
        onView(withId(R.id.et_update_lname)).perform(replaceText(""));
        onView(withId(R.id.et_update_email)).perform(replaceText(""));
        closeSoftKeyboard();
        onView(withId(R.id.et_update_address)).perform(replaceText(""));
        closeSoftKeyboard();
        onView(withId(R.id.et_update_phoneno)).perform(replaceText(""));
        closeSoftKeyboard();
        onView(withId(R.id.et_update_fname)).perform(typeText("Robinson"));
        onView(withId(R.id.et_update_lname)).perform(typeText("Shah"));
        onView(withId(R.id.et_update_email)).perform(typeText("robinsonbshah@gmail.com"));
        closeSoftKeyboard();
        onView(withId(R.id.et_update_address)).perform(typeText("kathmandu"));
        closeSoftKeyboard();
        onView(withId(R.id.et_update_phoneno)).perform(typeText("9849292313"));
        closeSoftKeyboard();
        onView(withId(R.id.btn_update_update)).perform(click());
        onView(withText("Update Successfull"))
                .inRoot(withDecorView(not(testRule.getActivity().getWindow().getDecorView()))).check(matches(isDisplayed()));

    }

}
