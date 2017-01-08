package com.schibsted.android.chatbot.cucumber.test;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.test.ActivityInstrumentationTestCase2;
import com.schibsted.android.chatbot.R;
import com.schibsted.android.chatbot.view.LoginActivity;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static com.schibsted.android.chatbot.cucumber.test.util.CustomMatcher.matchToolbarTitle;
import static com.schibsted.android.chatbot.cucumber.test.util.CustomMatcher.withAdaptedData;
import static com.schibsted.android.chatbot.cucumber.test.util.CustomMatcher.withName;
import static com.schibsted.android.chatbot.cucumber.test.util.GlobalActions.closeAllActivities;
import static com.schibsted.android.chatbot.cucumber.test.util.GlobalActions.getResourceString;
import static com.schibsted.android.chatbot.cucumber.test.util.GlobalActions.waitInMillisecondsForAppToBeIdle;

import cucumber.api.CucumberOptions;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;


@CucumberOptions(features = "features",tags ="@go")

public class ChatBotBDDSteps extends ActivityInstrumentationTestCase2<LoginActivity> {

    private Activity mActivity;
    private Context mInstrumentationContext;
    private Context mAppContext;

    public ChatBotBDDSteps() {
        super(LoginActivity.class);
    }

    @Before
    public void setUp() throws Exception {
        super.setUp();
        mInstrumentationContext = getInstrumentation().getContext();
        mAppContext = getInstrumentation().getTargetContext();
        mActivity = getActivity();
        assertNotNull(mActivity);
    }

    @After
    public void tearDown() throws Exception {
        //ActivityFinisher.finishOpenActivities();
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getActivity());
        prefs.edit().clear().commit();
        closeAllActivities((Instrumentation) getInstrumentation());
        super.tearDown();
    }



    @Given("^I am on the login main page$")
    public void I_have_a_login_page() {
        assertNotNull(getActivity());
    }

    @Given("^I am on the login main page again$")
    public void I_have_a_login_page_again() {
        assertNotNull(getActivity());
    }

    @When("^I type the username \"([^\"]*)\" in the textfield$")
    public void i_type_string(String typed) {
        onView(withId(R.id.name)).check(matches(isDisplayed())).perform(typeText(typed), closeSoftKeyboard());
    }

    @And("^I click on the login button$")
    public void I_press_login_button() {
        onView(withId(R.id.name)).perform(closeSoftKeyboard());
        onView(withId(R.id.sign_in_button)).check(matches(isDisplayed())).perform(click());
    }

    @Then("^I see the message list$")
    public void I_see_the_message_list() {
        onView(withId(R.id.lvMessages)).check(matches(isDisplayed()));
    }

    @When("^I type the message \"([^\"]*)\" in the messagebox$")
    public void i_type_my_message(String typed) {
        onView(withId(R.id.message_text)).check(matches(isDisplayed())).perform(typeText(typed), closeSoftKeyboard());
    }

    @And("^I click on the button to send the message$")
    public void I_press_the_button_to_send_my_message() {
        onView(withId(R.id.message_text)).perform(closeSoftKeyboard());
        onView(withId(R.id.send_button)).check(matches(isDisplayed())).perform(click());
    }

    @Then("^I should see the message \"([^\"]*)\" in the list$")
    public void i_see_the_message_in_the_list(final String match) {
        waitInMillisecondsForAppToBeIdle(onView(withId(R.id.lvMessages)), matches(isDisplayed()), 3000);
        onView(withId(R.id.lvMessages)).check(matches(withAdaptedData(withName(match))));

    }

    @Then("^I should see \"([^\"]*)\" on the titlebar$")
    public void i_should_see_on_the_display(final String match) {
        waitInMillisecondsForAppToBeIdle(onView(withId(R.id.message_text)), matches(isDisplayed()), 3000);
        matchToolbarTitle(getResourceString(R.string.chat_label)+" "+match);

    }



}
