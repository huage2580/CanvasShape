package com.hua.canvasshape.shape;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.Log;

/**
 * 基本的图形
 * Created by hua on 2017/12/7.
 */

public class ExShape {
    private static final String TAG = "ExShape";

    public static boolean debug=true;
    private int width=MeasueType.MATCH_PARENT;
    private int height=MeasueType.MATCH_PARENT;
    private Rect dstRect = new Rect();
    private Paint mDebugPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private int color=0;


    void measure(int suggestWidth,int suggestHeight){
        onMeasure(suggestWidth,suggestHeight);
    }

    /**
     * 如果可以同时测量出，也可以直接复写这个
     * @param suggestWidth
     * @param suggestHeight
     */
    void onMeasure(int suggestWidth,int suggestHeight){
        Log.i(TAG, getClass().getSimpleName()+"#onMeasure: "+suggestWidth+","+suggestHeight);
        measureWidth(suggestWidth);
        measureHeight(suggestHeight);
    }

    /**
     * 复写这个
     * @param suggestWidth
     */
    void measureWidth(int suggestWidth){
        if (width>=0){
            return;
        }
        if (width==MeasueType.MATCH_PARENT){
            width = suggestWidth;
        }
    }

    /**
     * 复写这个
     * @param suggestHeight
     */
    void measureHeight(int suggestHeight){
        if (height>=0){
            return;
        }
        if (height==MeasueType.MATCH_PARENT){
            height=suggestHeight;
        }
    }

    /**
     * 这是相对于canvas的位置
     * @param left
     * @param top
     */
    void layout(int left,int top){
        onLayout(left, top);
    }

    private void onLayout(int left,int top){
        dstRect.set(left,top,left+width,top+height);
        Log.i(TAG, getClass().getSimpleName()+"#onLayout: "+dstRect);
    }

    void draw(Canvas canvas){
        if (debug){
            onDrawDebug(canvas);
        }
        onDraw(canvas);
    }

    /**
     * 复写这个，实现自身的内容
     * @param canvas
     */
    public void onDraw(Canvas canvas){

    }

    public void onDrawDebug(Canvas canvas){
        makeDebugColor();
        Log.i(TAG, getClass().getSimpleName()+"#onDrawDebug: "+dstRect);
        canvas.drawRect(dstRect,mDebugPaint);
    }

    private void makeDebugColor() {
        if (color==0){
            mDebugPaint.setStyle(Paint.Style.STROKE);
            mDebugPaint.setStrokeWidth(3);
            color=0xff000000 | hashCode();
            mDebugPaint.setColor(color);
        }
    }

    public static boolean isDebug() {
        return debug;
    }

    public static void setDebug(boolean debug) {
        ExShape.debug = debug;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
        Log.i(TAG, getClass().getSimpleName()+"#setWidth: "+width);
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
        Log.i(TAG, getClass().getSimpleName()+"#setHeight: "+height);
    }

    public Rect getDstRect() {
        return dstRect;
    }

    public void setDstRect(Rect dstRect) {
        this.dstRect = dstRect;
    }

    /**
     * 设置当前shape的大小
     * @param width
     * @param height
     */
    public void setSize(int width,int height){
        this.width=width;
        this.height=height;
    }
}
