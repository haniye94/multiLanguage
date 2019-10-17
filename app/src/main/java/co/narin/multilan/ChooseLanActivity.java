package co.narin.multilan;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;


public class ChooseLanActivity extends AppCompatActivity {
    Spinner spinner;
    Locale myLocale;
    String currentLanguage, currentLang;
    public boolean isfirstTime = true;
    public SharedPreferences preferences;
    public SharedPreferences firstTime;
    Intent intent;
    boolean isbtnChangeLanClick;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_lan);


        intent = getIntent();
        isbtnChangeLanClick = intent.getBooleanExtra("btnChangeLanClick", false);
        firstTime = PreferenceManager.getDefaultSharedPreferences(this);
        isfirstTime = firstTime.getBoolean("firstTime", true);
        preferences = PreferenceManager.getDefaultSharedPreferences(this);
        currentLanguage = preferences.getString("lang", "en");
        Locale locale = new Locale(currentLanguage);
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        getBaseContext().getResources().updateConfiguration(config,
                getBaseContext().getResources().getDisplayMetrics());


//        currentLanguage = getIntent().getStringExtra(currentLang);

        if (isfirstTime) {
            spinner = (Spinner) findViewById(R.id.spinner);

            List<String> list = new ArrayList<String>();

            list.add("Select language");
            list.add("English");
            list.add("Turkish");
            list.add("Français");
            list.add("Hindi");

            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, list);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinner.setAdapter(adapter);

            spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                    switch (position) {

                        case 0:
                            break;
                        case 1:
                            setLocale("en");
                            preferences.edit().putString("lang", "en").commit();
                            Start();
                            break;
                        case 2:
                            setLocale("tr");
                            preferences.edit().putString("lang", "tr").commit();
                            Start();
                            break;
                        case 3:
                            setLocale("fr");
                            preferences.edit().putString("lang", "fr").commit();
                            Start();
                            break;
                        case 4:
                            setLocale("hi");
                            preferences.edit().putString("lang", "hi").commit();
                            Start();
                            break;
                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {
                }
            });
        } else {
            if (isbtnChangeLanClick) {
                firstTime.edit().putBoolean("firstTime", true).commit();
                finish();
                startActivity(getIntent());
            } else
                Start();
        }
    }

    public void Start() {
        startActivity(new Intent(ChooseLanActivity.this, MainActivity.class));
        firstTime.edit().putBoolean("firstTime", false).commit();
        finish();
    }

    public void setLocale(String localeName) {
        if (!localeName.equals(currentLanguage)) {
            myLocale = new Locale(localeName);
            Resources res = getResources();
            DisplayMetrics dm = res.getDisplayMetrics();
            Configuration conf = res.getConfiguration();
            conf.locale = myLocale;
            res.updateConfiguration(conf, dm);
            Intent refresh = new Intent(this, ChooseLanActivity.class);
            refresh.putExtra(currentLang, localeName);
            startActivity(refresh);
        } else {
            Toast.makeText(ChooseLanActivity.this, "Language already selected!", Toast.LENGTH_SHORT).show();
        }
    }

    //
//    public static Locale getLocale(Context context){
//        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
//
//        String lang = sharedPreferences.getString("language", "en");
//        switch (lang) {
//            case "English":
//                lang = "en";
//                break;
//            case "Spanish":
//                lang = "es";
//                break;
//        }
//        return new Locale(lang);
//    }
    public void onBackPressed() {
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        finish();
        System.exit(0);
    }
}