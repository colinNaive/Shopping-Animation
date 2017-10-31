package com.example.xiongni.ctripprodetail.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.example.xiongni.ctripprodetail.R;

import static com.example.xiongni.ctripprodetail.R.id.text;

/**
 * Created by xiongni on 2017/9/25.
 */

public class MyProgress extends View {
    private int vWidth, vHeight;
    private Context context;
    private Paint innerPaint;
    private Point tl,br;
    private Path cornerRectPath, progressRectPath;
    //圆圈的半径
    private float circleRadius;
    //进度条背景颜色
    private int progressColor = Color.BLACK;
    //进度条进度颜色
    private int backgroundColor = Color.BLUE;
    //下载进度
    private int progress = 0;
    //最大进度
    private int max = 100;

    public MyProgress(Context context) {
        super(context);
        init(context);
    }

    public MyProgress(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public MyProgress(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(final Context context) {
        this.context = context;
        innerPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        innerPaint.setStrokeWidth(5);
        innerPaint.setStyle(Paint.Style.FILL);
        cornerRectPath = new Path();
        progressRectPath = new Path();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawLoadingBar(canvas);
    }


    private void initData() {
        circleRadius = vHeight / 2f;
        tl = new Point(0,0);
        br = new Point(vWidth,vHeight);
    }


    /**
     * @param canvas
     */
    private void drawLoadingBar(Canvas canvas) {
        innerPaint.setStyle(Paint.Style.FILL);
        innerPaint.setPathEffect(null);
        //完整矩形左上角，右下角的点
        Point lu = new Point(tl.x + circleRadius, tl.y);
        Point rd = new Point(br.x - circleRadius, br.y);
        float length = rd.x - lu.x;


        //完整进度条长度
        innerPaint.setColor(backgroundColor);
        cornerRectPath.reset();
        cornerRectPath.moveTo(lu.x, lu.y);
        cornerRectPath.lineTo(rd.x, lu.y);
        cornerRectPath.lineTo(rd.x, rd.y);
        cornerRectPath.lineTo(lu.x, rd.y);
        cornerRectPath.close();
        canvas.drawPath(cornerRectPath, innerPaint);
        canvas.drawArc(new RectF(lu.x - circleRadius,lu.y,lu.x + circleRadius,rd.y),90,180, true,innerPaint);
        canvas.drawArc(new RectF(rd.x - circleRadius,lu.y,rd.x + circleRadius,rd.y),-90,180, true,innerPaint);//canvas.drawCircle(rd.x,rd.y - circleRadius,circleRadius,innerPaint);


        //已下载长度
        innerPaint.setColor(progressColor);
        progressRectPath.reset();
        progressRectPath.moveTo(lu.x, lu.y);
        progressRectPath.lineTo(lu.x + (float)progress / max * length, lu.y);
        progressRectPath.lineTo(lu.x + (float)progress / max * length, rd.y);
        progressRectPath.lineTo(lu.x, rd.y);
        progressRectPath.close();
        canvas.drawPath(progressRectPath, innerPaint);
        canvas.drawArc(new RectF(lu.x - circleRadius,lu.y,lu.x + circleRadius,rd.y),90,180, true,innerPaint);
        canvas.drawArc(new RectF(lu.x + (float)progress / max * length - circleRadius,lu.y,lu.x + (float)progress / max * length + circleRadius,rd.y),-90,180, true,innerPaint);//canvas.drawCircle(lu.x + progress * 0.01f * length,rd.y - circleRadius,circleRadius,innerPaint);

    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        vHeight = getMeasuredHeight();
        vWidth = getMeasuredWidth();
        initData();
    }


    /**
     * 设置下载进度
     *
     * @param progress
     */
    public void setProgress(float progress) {
        this.progress = (int) (progress * 10);
        if (progress > max) {//下载完毕
            this.progress = max;
        }
        postInvalidate();
    }

    public int getBackgroundColor() {
        return backgroundColor;
    }

    @Override
    public void setBackgroundColor(int backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    public int getProgressColor() {
        return progressColor;
    }

    public void setProgressColor(int progressColor) {
        this.progressColor = progressColor;
    }

    public int getMax() {
        return max;
    }

    public void setMax(float max) {
        this.max = (int) (max * 10);
    }

    private class Point {
        float x, y;

        public Point(float x, float y) {
            this.x = x;
            this.y = y;
        }
        public Point() {
        }
    }
}
