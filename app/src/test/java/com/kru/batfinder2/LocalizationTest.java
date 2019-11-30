package com.kru.batfinder2;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;

import androidx.test.core.app.ApplicationProvider;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import java.util.Locale;

import static androidx.test.InstrumentationRegistry.getContext;
import static org.junit.Assert.*;

@RunWith(RobolectricTestRunner.class)
@Config(sdk = Build.VERSION_CODES.O_MR1)
public class LocalizationTest {

    private Context context;

    @Before
    public void DoBefore(){
        context = ApplicationProvider.getApplicationContext();
    }

    @Test
    public void defaultLanguageIsEnglish(){
        setLocale("ru", "RU");
        String checkString = "Bats";
        String localString = context.getString(R.string.menu_home);

        assertEquals(checkString, localString);
    }

    @Test
    public void dutchLanguageReturnsDutch(){
        setLocale("nl", "BE");
        String checkString = "Vleermuizen";
        String localString = context.getString(R.string.menu_home);

        assertEquals(checkString, localString);
    }

    private void setLocale(String language, String country) {
        Locale locale = new Locale(language, country);
        // here we update locale for date formatters
        Locale.setDefault(locale);
        // here we update locale for app resources
        Resources res = context.getResources();
        Configuration config = res.getConfiguration();
        config.locale = locale;
        res.updateConfiguration(config, res.getDisplayMetrics());
    }
}