package com.example.showertimer;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import java.util.ArrayList;

public class Widget extends AppCompatActivity {

    private ViewPager2 viewPager1;
    private ViewPager2 viewPager2;
    private ViewPager2 viewPager3;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_widget);

        ArrayList<Fragment> fragments = new ArrayList<>();
        fragments.add(TimerWidget.newInstance("test","test"));
        fragments.add(WeatherWidget.newInstance("test","test"));
        fragments.add(CalendarWidget.newInstance("TEST","TEST"));

        ArrayList<Fragment> fragments1 = new ArrayList<>();
        fragments1.add(TimerWidget.newInstance("test","test"));
        fragments1.add(WeatherWidget.newInstance("test","test"));
        fragments1.add(CalendarWidget.newInstance("TEST","TEST"));

        ArrayList<Fragment> fragments2 = new ArrayList<>();
        fragments2.add(TimerWidget.newInstance("test","test"));
        fragments2.add(WeatherWidget.newInstance("test","test"));
        fragments2.add(CalendarWidget.newInstance("TEST","TEST"));

        viewPager1 = (ViewPager2) findViewById(R.id.viewPager);
        ViewPager2Adapter viewPager2Adapter = new ViewPager2Adapter(this, fragments);
        viewPager1.setOrientation(ViewPager2.ORIENTATION_VERTICAL);
        viewPager1.setAdapter(viewPager2Adapter);

        viewPager2 = (ViewPager2) findViewById(R.id.viewPager2);
        ViewPager2Adapter viewPager3Adapter = new ViewPager2Adapter(this, fragments1);
        viewPager2.setOrientation(ViewPager2.ORIENTATION_VERTICAL);
        viewPager2.setAdapter(viewPager3Adapter);

        viewPager3 = (ViewPager2) findViewById(R.id.viewPager3);
        ViewPager2Adapter viewPager4Adapter = new ViewPager2Adapter(this, fragments2);
        viewPager3.setOrientation(ViewPager2.ORIENTATION_VERTICAL);
        viewPager3.setAdapter(viewPager4Adapter);

    }

}