package com.example.showertimer;


import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.View;

import java.util.Calendar;

public class CustomAnalogClock extends View {

    private Paint paint;
    private int centerX, centerY, radius;
    private Handler handler;
    private Runnable runnable;

    public CustomAnalogClock(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setStrokeWidth(8);
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(Color.BLACK);  // 기본 테두리 색상

        // 1초마다 화면을 갱신하는 Handler와 Runnable 설정
        handler = new Handler();
        runnable = new Runnable() {
            @Override
            public void run() {
                invalidate();  // 화면 갱신
                handler.postDelayed(this, 1000);  // 1초 후 다시 실행
            }
        };
        handler.post(runnable);  // 첫 호출
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int width = getWidth();
        int height = getHeight();
        centerX = width / 2;
        centerY = height / 2;
        radius = Math.min(centerX, centerY) - 20;

        // 시계 테두리 그리기
        canvas.drawCircle(centerX, centerY, radius, paint);

        // 현재 시간 가져오기
        Calendar calendar = Calendar.getInstance();
        float hour = calendar.get(Calendar.HOUR_OF_DAY) % 12;
        float minute = calendar.get(Calendar.MINUTE);
        float second = calendar.get(Calendar.SECOND);

        // 시침, 분침, 초침 그리기
        drawHand(canvas, (hour + minute / 60) * 30, radius * 0.5f, Color.BLACK); // 시침
        drawHand(canvas, minute * 6, radius * 0.7f, Color.BLACK); // 분침
        drawHand(canvas, second * 6, radius * 0.9f, Color.RED); // 초침
    }

    private void drawHand(Canvas canvas, double angle, float handLength, int color) {
        paint.setColor(color);
        double radian = Math.toRadians(angle - 90);
        float endX = (float) (centerX + Math.cos(radian) * handLength);
        float endY = (float) (centerY + Math.sin(radian) * handLength);
        canvas.drawLine(centerX, centerY, endX, endY, paint);
    }
}
