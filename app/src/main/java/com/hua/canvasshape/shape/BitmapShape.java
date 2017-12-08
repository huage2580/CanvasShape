package com.hua.canvasshape.shape;

import android.graphics.Bitmap;
import android.graphics.Canvas;

/**
 * bitmap图形
 * Created by hua on 2017/12/8.
 */

public class BitmapShape extends ExShape {
    private Bitmap image;
    public BitmapShape(Bitmap image) {
        this.image = image;
    }

    @Override
    void onMeasure(int suggestWidth, int suggestHeight) {
        super.onMeasure(suggestWidth, suggestHeight);
        if (getWidth()==MeasueType.WRAP_PARENT && getHeight() == MeasueType.WRAP_PARENT){
            setWidth(image.getWidth());
            setHeight(image.getHeight());
        }else if (getWidth()==MeasueType.WRAP_PARENT){
            float ratio=(float) getHeight()/(float) image.getHeight();
            setWidth((int) (image.getWidth()*ratio));
        }else if(getHeight() == MeasueType.WRAP_PARENT){
            float ratio=(float) getWidth()/(float) image.getWidth();
            setHeight((int) (image.getHeight()*ratio));
        }
    }

    @Override
    public void onDraw(Canvas canvas) {
        canvas.drawBitmap(image,null,getDstRect(),null);
    }
}
