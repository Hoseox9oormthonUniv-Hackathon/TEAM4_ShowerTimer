package com.example.showertimer;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DigitalClockWidget#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DigitalClockWidget extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private TextView digitalClockTextView;
    private Handler handler;
    private Runnable runnable;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public DigitalClockWidget() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DigitalTimerWidget.
     */
    // TODO: Rename and change types and number of parameters
    public static DigitalClockWidget newInstance(String param1, String param2) {
        DigitalClockWidget fragment = new DigitalClockWidget();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
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
        View view = inflater.inflate(R.layout.fragment_digital_timer_widget, container, false);

        digitalClockTextView = view.findViewById(R.id.digitalClockTextView);
        handler = new Handler();

        runnable = new Runnable() {
            @Override
            public void run() {
                // 현재 시간 가져오기
                String currentTime = new SimpleDateFormat("HH:mm", Locale.getDefault()).format(new Date());
                digitalClockTextView.setText(currentTime); // TextView에 시간 설정
                handler.postDelayed(this, 1000); // 1초마다 갱신
            }
        };
        handler.post(runnable); // 첫 번째 실행
        return view;
    }
}