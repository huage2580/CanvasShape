package com.hua.canvasshape.shape;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;

/**
 * Created by hua on 2017/12/7.
 */

public class OvalShape extends ExShape {
    Paint mPaint;
    RectF mRectF;
    boolean fill=true;
    float strokeWidth=2;
    public OvalShape(int color) {
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setStrokeWidth(strokeWidth);
        mPaint.setColor(color);
        mRectF=new RectF();
    }

    @Override
    public void onDraw(Canvas canvas) {
        mRectF.set(getDstRect());
        canvas.drawOval(mRectF,mPaint);
    }

    public void setFill(boolean fill) {
        this.fill = fill;
        mPaint.setStyle(fill?Paint.Style.FILL: Paint.Style.STROKE);
    }

    public void setStrokeWidth(float strokeWidth) {
        this.strokeWidth = strokeWidth;
        mPaint.setStrokeWidth(strokeWidth);
    }
}
