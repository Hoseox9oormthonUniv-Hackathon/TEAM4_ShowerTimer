package com.example.showertimer;

import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import java.util.ArrayList;

public class Widget extends AppCompatActivity implements DigitTimerWidget.TimerListener {

    private ViewPager2 viewPager1;
    private ViewPager2 viewPager2;
    private ViewPager2 viewPager3;

    private int count = 0;



    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_widget);

        int toothTime = getIntent().getIntExtra("양치 시간",3);
        int showerTime = getIntent().getIntExtra("샤워 시간",15);
        int shampooTime = getIntent().getIntExtra("샴푸 시간",5);
        int shavingTime = getIntent().getIntExtra("면도 시간",3);
        int cleansingTime = getIntent().getIntExtra("세안 시간",2);

        ArrayList<Integer> timer = new ArrayList<>();

        timer.add(toothTime);
        timer.add(showerTime);
        timer.add(shampooTime);
        timer.add(shavingTime);
        timer.add(cleansingTime);

        ArrayList<Fragment> fragments = new ArrayList<>();

        fragments.add(DigitTimerWidget.newInstance(timer));
        //fragments.add(DigitTimerWidget.newInstance(showerTime));
        //fragments.add(DigitTimerWidget.newInstance(shampooTime));

        ArrayList<Fragment> fragments1 = new ArrayList<>();
        fragments1.add(AnalogClock.newInstance("test","test"));
        fragments1.add(DigitalClockWidget.newInstance("TEST","TEST"));

        ArrayList<Fragment> fragments2 = new ArrayList<>();
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

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        long upKeyTime = 0;
        switch (keyCode) {
            case KeyEvent.KEYCODE_VOLUME_UP:
                if (System.currentTimeMillis() > upKeyTime + 2000) {
                    return true;
                }
        }
        return super.onKeyUp(keyCode, event);
    }

    @Override
    public void onTimerFinished() {
        Log.e("IMHERE","Finish");
    }
}