package com.example.xiongni.ctripprodetail;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ScrollView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private View testView;
    private View testView02;
    private View anim;
    private ScrollView scrollView;
    private boolean flag = true;
    int testX = 0;
    int testY = 0;
    int test02X = 0;
    int test02Y = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        testView = findViewById(R.id.test_view);
        testView02 = findViewById(R.id.test_view02);
        anim = findViewById(R.id.anim);
       // Log.e("123", testView02.getX+ ", " + testView02.getY() + "onCreate: ----- " + testCenterX + ", " +  testCenterY);
        testView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e("123", "onClick: ---------------------------------- " + testX + ", " +  test02X);
                Log.e("123", "onClick: ********************************** " + testY + ", " +  test02Y);
                if(flag){
                    flag = false;
                    anim.setVisibility(View.VISIBLE);
                    testView.setBackgroundColor(Color.RED);
                    ObjectAnimator anim3 = ObjectAnimator.ofFloat(anim, "x", testX, test02X);
                    ObjectAnimator anim4 = ObjectAnimator.ofFloat(anim, "y", testY, test02Y);
                    ObjectAnimator anim5 = ObjectAnimator.ofFloat(anim, "alpha", 1, 0);

                    AnimatorSet animSet = new AnimatorSet();
                    animSet.play(anim3).with(anim4).with(anim5);
                    animSet.setDuration(400);
                    animSet.start();
                    animSet.addListener(new Animator.AnimatorListener() {//监听动画在不同的时间段所需要完成的操作

                        @Override
                        public void onAnimationStart(Animator animation) {
                        }

                        @Override
                        public void onAnimationRepeat(Animator animation) {
                        }

                        @Override
                        public void onAnimationEnd(Animator animation) {
                            testView02.setBackgroundColor(Color.RED);
                        }

                        @Override
                        public void onAnimationCancel(Animator animation) {
                        }
                    });
                }else{
                    testView.setBackgroundColor(Color.BLUE);
                    testView02.setBackgroundColor(Color.BLUE);
                    flag = true;
                }
            }
        });
        scrollView = (ScrollView) findViewById(R.id.scrollView);
        scrollView.setOnScrollChangeListener(new View.OnScrollChangeListener() {
            @Override
            public void onScrollChange(View view, int i, int i1, int i2, int i3) {
                int[] location1 = new int[2] ;
                testView.getLocationInWindow(location1); //获取在当前窗口内的绝对坐标
                testX = location1[0]; testY = location1[1];
                int[] location2 = new int[2] ;
                testView02.getLocationOnScreen(location2);//获取在整个屏幕内的绝对坐标
                test02X = location2[0]; test02Y = location2[1];
                Log.e("123", location1[0] + ", " + location1[1] + " onWindowFocusChanged: " + location2[0] + ", " + location2[1]);
            }
        });
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        int[] location1 = new int[2] ;
        testView.getLocationInWindow(location1); //获取在当前窗口内的绝对坐标
        testX = location1[0]; testY = location1[1];
        int[] location2 = new int[2] ;
        testView02.getLocationOnScreen(location2);//获取在整个屏幕内的绝对坐标
        test02X = location2[0]; test02Y = location2[1];
        Log.e("123", location1[0] + ", " + location1[1] + " onWindowFocusChanged: " + location2[0] + ", " + location2[1]);
    }

    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

        }
    }

}
