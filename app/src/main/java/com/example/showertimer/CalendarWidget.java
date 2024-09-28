package com.example.showertimer;

import android.Manifest;
import android.content.ContentResolver;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Bundle;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.provider.CalendarContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CalendarWidget#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CalendarWidget extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private TextView tvSchedule;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public CalendarWidget() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Fragment3.
     */
    // TODO: Rename and change types and number of parameters
    public static CalendarWidget newInstance(String param1, String param2) {
        CalendarWidget fragment = new CalendarWidget();
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
        View view = inflater.inflate(R.layout.fragment_calendar_widget, container, false);
        // TextView 연결
        tvSchedule = view.findViewById(R.id.tv_schedule);

        // 캘린더 읽기 권한 확인
        if (ContextCompat.checkSelfPermission(getContext(), android.Manifest.permission.READ_CALENDAR) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.READ_CALENDAR}, 100);
        } else {
            // 권한이 허용된 경우, 일정을 불러옵니다.
            loadTodayEvents();
        }
        // Inflate the layout for this fragment
        return view;
    }

    // 권한 요청 결과 처리
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 100 && grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            loadTodayEvents();
        } else {
            Toast.makeText(getContext(), "캘린더 접근 권한이 필요합니다.", Toast.LENGTH_SHORT).show();
        }
    }


    // 오늘의 일정을 불러오는 메서드
    private void loadTodayEvents() {
        ContentResolver contentResolver = getActivity().getContentResolver();

        // 오늘의 시작과 끝 시간 설정
        Calendar beginTime = Calendar.getInstance();
        beginTime.set(Calendar.HOUR_OF_DAY, 0);
        beginTime.set(Calendar.MINUTE, 0);
        beginTime.set(Calendar.SECOND, 0);
        long startMillis = beginTime.getTimeInMillis();

        Calendar endTime = Calendar.getInstance();
        endTime.set(Calendar.HOUR_OF_DAY, 23);
        endTime.set(Calendar.MINUTE, 59);
        endTime.set(Calendar.SECOND, 59);
        long endMillis = endTime.getTimeInMillis();

        // 캘린더에서 오늘의 일정 쿼리
        Cursor cursor = contentResolver.query(
                CalendarContract.Events.CONTENT_URI,
                new String[]{CalendarContract.Events.TITLE, CalendarContract.Events.DTSTART, CalendarContract.Events.DTEND},
                CalendarContract.Events.DTSTART + " >= ? AND " + CalendarContract.Events.DTSTART + " <= ?",
                new String[]{String.valueOf(startMillis), String.valueOf(endMillis)},
                CalendarContract.Events.DTSTART + " ASC");

        if (cursor != null && cursor.moveToFirst()) {
            StringBuilder eventList = new StringBuilder();
            SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm");

            do {
                String title = cursor.getString(cursor.getColumnIndex(CalendarContract.Events.TITLE));
                long startTime = cursor.getLong(cursor.getColumnIndex(CalendarContract.Events.DTSTART));
                long endTimeValue = cursor.getLong(cursor.getColumnIndex(CalendarContract.Events.DTEND));

                Date startDate = new Date(startTime);
                Date endDate = new Date(endTimeValue);

                String eventDetails = "Event: " + title + "\nStart: " + dateFormat.format(startDate) + "\nEnd: " + dateFormat.format(endDate) + "\n\n";
                eventList.append(eventDetails);
            } while (cursor.moveToNext());

            tvSchedule.setText(eventList.toString());
            cursor.close();
        } else {
            tvSchedule.setText("오늘 일정이 없습니다.");
        }
    }
}