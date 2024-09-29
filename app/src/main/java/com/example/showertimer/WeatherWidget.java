package com.example.showertimer;


import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.os.StrictMode;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link WeatherWidget#newInstance} factory method to
 * create an instance of this fragment.
 */
public class WeatherWidget extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    private TextView tvLocation, tvNowTemp, tvInfo, tvFeelTemp, tvHumidity;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public WeatherWidget() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Fragment2.
     */
    // TODO: Rename and change types and number of parameters
    public static WeatherWidget newInstance(String param1, String param2) {
        WeatherWidget fragment = new WeatherWidget();
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
        View view = inflater.inflate(R.layout.fragment_weather_widget,container, false);
        // Inflate the layout for this fragment
        tvNowTemp = view.findViewById(R.id.tv_nowtemp);
        tvInfo = view.findViewById(R.id.tv_info);

        // 네트워크 정책 설정 (메인 스레드에서 네트워크 작업 허용)
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        // 날씨 정보 가져오기
        getWeatherInfo();

        return view;
    }

    private void getWeatherInfo() {
        try {
            // 네이버 날씨 페이지 URL
            String url = "https://search.naver.com/search.naver?sm=tab_hty.top&where=nexearch&ssc=tab.nx.all&query=%EB%82%B4+%EC%9C%84%EC%B9%98+%EB%82%A0%EC%94%A8";

            // Jsoup을 사용하여 페이지 크롤링
            Document document = Jsoup.connect(url).get();

            // 각각의 데이터를 가져올 CSS 선택자
            String locationSelector = "#main_pack > section.sc_new.cs_weather_new._cs_weather > div > div:nth-child(1) > div.top_wrap > div.title_area._area_panel > h2.title";
            String nowTempSelector = "div.temperature_text > strong";
            String infoSelector = "#main_pack > section.sc_new.cs_weather_new._cs_weather > div > div:nth-child(1) > div.content_wrap > div.open > div:nth-child(1) > div > div.weather_info > div > div._today > div.temperature_info > p > span.weather.before_slash";
            String feelTempSelector = "#main_pack > section.sc_new.cs_weather_new._cs_weather > div > div:nth-child(1) > div.content_wrap > div.open > div:nth-child(1) > div > div.weather_info > div > div._today > div.temperature_info > dl > div:nth-child(1) > dd";
            String humiditySelector = "#main_pack > section.sc_new.cs_weather_new._cs_weather > div > div:nth-child(1) > div.content_wrap > div.open > div:nth-child(1) > div > div.weather_info > div > div._today > div.temperature_info > dl > div:nth-child(2) > dd";

            // 각각의 데이터를 파싱하여 TextView에 설정
            Elements locationElement = document.select(locationSelector);
            Elements nowTempElement = document.select(nowTempSelector);
            Elements infoElement = document.select(infoSelector);
            Elements feelTempElement = document.select(feelTempSelector);
            Elements humidityElement = document.select(humiditySelector);

            // 위치 설정
            tvLocation.setText(locationElement.text()); // 위치 정보 설정

            // 현재 온도 (숫자 부분만 추출하고 소수점 이하 제거)
            String fullNowTemp = nowTempElement.text(); // "현재 온도 22.0°"와 같은 텍스트
            String tempValue = fullNowTemp.replaceAll("[^0-9.]", ""); // 숫자와 '.' 이외의 문자 제거
            String integerTempValue = tempValue.split("\\.")[0]; // 소수점 이하 제거
            tvNowTemp.setText(integerTempValue + "°C"); // 현재 온도 설정

            // 날씨 정보
            tvInfo.setText(infoElement.text()); // 날씨 정보 설정

            // 체감 온도
            tvFeelTemp.setText(feelTempElement.text()); // 체감 온도 설정

            // 습도
            tvHumidity.setText(humidityElement.text()); // 습도 설정

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}