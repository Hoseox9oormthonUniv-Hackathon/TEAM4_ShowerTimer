package com.example.showertimer;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import java.util.ArrayList;

public class EndActivity extends AppCompatActivity {

    private ViewPager2 viewPager1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end);


        ArrayList<Fragment> fragments = new ArrayList<>();

        fragments.add(DryTimer.newInstance(300));

        viewPager1 = (ViewPager2) findViewById(R.id.viewPager);
        ViewPager2Adapter viewPager2Adapter = new ViewPager2Adapter(this, fragments);
        viewPager1.setOrientation(ViewPager2.ORIENTATION_VERTICAL);
        viewPager1.setAdapter(viewPager2Adapter);
    }
}