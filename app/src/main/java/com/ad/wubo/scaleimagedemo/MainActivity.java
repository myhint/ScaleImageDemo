package com.ad.wubo.scaleimagedemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

/**
 * 目标：实现图片的放大和缩小
 */
public class MainActivity extends AppCompatActivity {

    private ImageView img;
    private FrameLayout fl;
    private float lastDistance = -1;
    private float currentDistance;
    private float offsetX;
    private float offsetY;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        img = (ImageView) findViewById(R.id.image);
        fl = (FrameLayout) findViewById(R.id.id_frameLayout);

        fl.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                switch (motionEvent.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        System.out.println("action down");
                        break;
                    case MotionEvent.ACTION_MOVE:
                        System.out.println("action move");


                        if (motionEvent.getPointerCount()>=2){

                            offsetX = motionEvent.getX(0) - motionEvent.getX(1);
                            offsetY = motionEvent.getY(0) - motionEvent.getY(1);
                            currentDistance = (float) Math.sqrt(offsetX*offsetX+offsetY*offsetY);

                            if (lastDistance<0){
                                lastDistance = currentDistance;
                            }else {
                                if ((currentDistance-lastDistance)>5){
                                    System.out.println("放大");
                                    lastDistance = currentDistance;

                                    FrameLayout.LayoutParams lp = (FrameLayout.LayoutParams) img.getLayoutParams();
                                    lp.width = (int) (1.2f*img.getWidth());
                                    lp.height = (int) (1.2f*img.getHeight());
                                    img.setLayoutParams(lp);

                                }else if (lastDistance-currentDistance>5){
                                    System.out.println("缩小");
                                    lastDistance = currentDistance;

                                    FrameLayout.LayoutParams lp = (FrameLayout.LayoutParams) img.getLayoutParams();
                                    lp.width = (int) (0.8f*img.getWidth());
                                    lp.height = (int) (0.8f*img.getHeight());
                                    img.setLayoutParams(lp);

                                }
                            }

                        }else {

                        }

                        break;
                    case MotionEvent.ACTION_UP:
                        System.out.println("action up");
                        break;
                    default:
                        break;
                }
                return true;   //保证触摸动作的连续性，若为false，ACTION_MOVE和ACTION_UP则没有反应
            }
        });

    }
}
