package com.hua.canvasshape.shape;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

/**
 * 文字
 * Created by hua on 2017/12/7.
 */

public class TextShape extends ExShape{
    Paint mPaint;
    String text;
    float textSize;
    int textColor;
    public TextShape(String text) {
        this.text=text;
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setTextSize(100);
        setSize(MeasueType.WRAP_PARENT,MeasueType.WRAP_PARENT);
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public float getTextSize() {
        return textSize;
    }

    public void setTextSize(float textSize) {
        this.textSize = textSize;
        mPaint.setTextSize(textSize);
    }

    public int getTextColor() {
        return textColor;
    }

    public void setTextColor(int textColor) {
        this.textColor = textColor;
        mPaint.setColor(textColor);
    }

    @Override
    void onMeasure(int suggestWidth, int suggestHeight) {
        Rect rect = new Rect();
        mPaint.getTextBounds(text, 0, text.length(), rect);
        int width = rect.width();//文本的宽度
        int height = rect.height();//文本的高度
        if (getWidth()==MeasueType.WRAP_PARENT){
            setWidth(width);
        }
        if (getHeight()==MeasueType.WRAP_PARENT){
            setHeight(height);
        }
        super.onMeasure(suggestWidth,suggestHeight);
    }

    @Override
    public void onDraw(Canvas canvas) {
        canvas.drawText(text,getDstRect().left,getDstRect().bottom,mPaint);
    }
}
