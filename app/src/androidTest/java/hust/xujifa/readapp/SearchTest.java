package hust.xujifa.readapp;

import android.content.Context;
import android.content.Intent;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.Espresso;
import android.support.test.espresso.action.ViewActions;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.runner.AndroidJUnit4;
import android.support.test.uiautomator.By;
import android.support.test.uiautomator.UiDevice;
import android.support.test.uiautomator.Until;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Created by xujifa on 2016/1/25.
 */
@RunWith(AndroidJUnit4.class)
public class SearchTest {

    UiDevice mDevice;
    String PKG="hust.xujifa.readapp";

    @Before
    public void startActivity(){
        mDevice=UiDevice.getInstance(InstrumentationRegistry.getInstrumentation());

        mDevice.pressHome();

        Context context=InstrumentationRegistry.getContext();

        Intent intent=context.getPackageManager().getLaunchIntentForPackage(PKG);

        context.startActivity(intent);

        mDevice.wait(Until.findObject(By.pkg(PKG)),4000);

    }
    @Test
    public void testSearch() throws InterruptedException {

        Espresso.onView(ViewMatchers.withId(R.id.viewpager)).perform(ViewActions.swipeLeft());

        Espresso.onView(ViewMatchers.withId(R.id.fab_search)).perform(ViewActions.click());

        Espresso.onView(ViewMatchers.withContentDescription("search edittext"))
                .perform(ViewActions.replaceText("那些"));
        Espresso.onView(ViewMatchers.withText(R.string.search)).perform(ViewActions.click());

        Thread.sleep(3000);


    }

}
