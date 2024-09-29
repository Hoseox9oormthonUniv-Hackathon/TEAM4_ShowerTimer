package com.example.showertimer;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Locale;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DryTimer#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DryTimer extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    private TextView dryClockTime;
    private Handler handler;
    private int remainingTime = 0;
    private Runnable runnable;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public DryTimer() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DryTimer.
     */
    // TODO: Rename and change types and number of parameters
    public static DryTimer newInstance(int time) {
        DryTimer fragment = new DryTimer();
        Bundle args = new Bundle();
        args.putInt("Time",time);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_dry_timer, container, false);

        dryClockTime = view.findViewById(R.id.dryTimerText);

        handler = new Handler();

        runnable = new Runnable() {
            @Override
            public void run() {
                if (remainingTime > 0) {
                            // 남은 시간을 HH:mm 형식으로 변환
                    int minutes = remainingTime / 60;
                    int seconds = remainingTime % 60;
                    String time = String.format(Locale.getDefault(), "%02d:%02d", minutes, seconds);
                    dryClockTime.setText(time); // TextView에 시간 설정
                    remainingTime--; // 1초 줄이기
                    handler.postDelayed(this, 1000); // 1초마다 갱신
                } else {
                    dryClockTime.setText("00:00"); // 0 초일 때 표시
                    handler.removeCallbacks(this); // 타이머 중지
                }
            }
        };
        handler.post(runnable); // 첫 번째 실행
        return view;
    }
}