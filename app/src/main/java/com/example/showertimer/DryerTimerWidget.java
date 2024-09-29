package com.example.showertimer;


import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.Locale;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DigitalClockWidget#newInstance} factory method to
 * create an instance of this fragment.
 */

public class DryerTimerWidget extends Fragment {

    private static final String ARG_TIME = "time"; // 남은 시간을 위한 인자

    private TextView digitalClockTextView;
    private Handler handler;
    private Runnable runnable;

    private ArrayList<Integer> timer1;
    private int count = 0;
    private int remainingTime = 0; // 남은 시간 (초 단위)

    public DryerTimerWidget() {
        // Required empty public constructor
    }

    public static DryerTimerWidget newInstance(ArrayList<Integer> timer) {
        DryerTimerWidget fragment = new DryerTimerWidget();
        Bundle args = new Bundle();
        args.putIntegerArrayList("Timer", timer);
        fragment.setArguments(args);
        return fragment;
    }

    private TimerListener listener;

    // 인터페이스 정의
    public interface TimerListener {
        void onTimerFinished();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        // 액티비티가 인터페이스를 구현하는지 확인
        if (context instanceof TimerListener) {
            listener = (TimerListener) context;
        } else {
            throw new ClassCastException(context.toString() + " must implement TimerListener");
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            //remainingTime = getArguments().getInt(ARG_TIME); // 인자에서 남은 시간 가져오기
            timer1 = getArguments().getIntegerArrayList("Timer");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_digital_timer_widget, container, false);

        digitalClockTextView = view.findViewById(R.id.digitalClockTextView);

        handler = new Handler();

        runnable = new Runnable() {
            @Override
            public void run() {

                if (count < timer1.size()+1) {
                    if(remainingTime <= 0) {
                        remainingTime = timer1.get(count);
                        count++;
                        Log.e("IMHERE","IMHEREee"+remainingTime);
                    }
                    if (remainingTime > 0) {
                        // 남은 시간을 HH:mm 형식으로 변환
                        int minutes = remainingTime / 60;
                        int seconds = remainingTime % 60;
                        String time = String.format(Locale.getDefault(), "%02d:%02d", minutes, seconds);
                        digitalClockTextView.setText(time); // TextView에 시간 설정
                        remainingTime--; // 1초 줄이기
                        Log.e("IMHERE","IMHERE"+remainingTime);
                        handler.postDelayed(this, 1000); // 1초마다 갱신
                    } else {

                        digitalClockTextView.setText("00:00"); // 0 초일 때 표시
                        handler.removeCallbacks(this); // 타이머 중지

                        if (listener != null ) {
                            Log.e("IMHERE","Finish");
                            listener.onTimerFinished(); // 액티비티에 알림
                        }
                    }
                }
            }
        };
        handler.post(runnable); // 첫 번째 실행
        return view;
    }

}
