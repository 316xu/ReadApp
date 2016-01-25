package hust.xujifa.readapp;

import android.content.Context;
import android.content.Intent;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.Espresso;
import android.support.test.espresso.ViewAction;
import android.support.test.espresso.action.ViewActions;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.runner.AndroidJUnit4;
import android.support.test.uiautomator.By;
import android.support.test.uiautomator.UiDevice;
import android.support.test.uiautomator.UiObject2;
import android.support.test.uiautomator.Until;


import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertNotNull;

/**
 * Created by xujifa on 2016/1/24.
 */
@RunWith(AndroidJUnit4.class)
public class MainTest {
    UiDevice mDevice;
    String PKG="hust.xujifa.readapp";

    @Before
    public void startActivity(){
        mDevice=UiDevice.getInstance(InstrumentationRegistry.getInstrumentation());

        mDevice.pressHome();

        final String pkg_name=mDevice.getLauncherPackageName();

        assertNotNull(pkg_name);


        mDevice.wait(Until.findObject(By.pkg(pkg_name)),5000);

        Context context=InstrumentationRegistry.getContext();

        Intent intent=context.getPackageManager().getLaunchIntentForPackage(PKG);

        context.startActivity(intent);

        mDevice.wait(Until.findObject(By.pkg(PKG)),5000);

    }

    @Test
    public void testfull() throws InterruptedException {
        Espresso.onView(ViewMatchers.withId(R.id.viewpager)).perform(ViewActions.swipeLeft());

        Espresso.onView(ViewMatchers.withId(R.id.class1)).perform(ViewActions.click());

        UiObject2 list=mDevice.wait(Until.findObject(By.res(PKG,"booklist")),5000);

        Assert.assertNotNull(list);

        list.getChildren().get(1).click();




        Thread.sleep(5000);

        Espresso.onView(ViewMatchers.withId(R.id.author)).perform(ViewActions.click());

        Thread.sleep(2000);
    }

}

