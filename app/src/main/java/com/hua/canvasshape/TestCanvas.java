package com.hua.canvasshape;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.hua.canvasshape.shape.BitmapShape;
import com.hua.canvasshape.shape.FrameGroup;
import com.hua.canvasshape.shape.LinearGroup;
import com.hua.canvasshape.shape.MeasueType;
import com.hua.canvasshape.shape.OvalShape;
import com.hua.canvasshape.shape.RelativeGroup;
import com.hua.canvasshape.shape.TextShape;

/**
 * @author hua
 * Created by hua on 2017/12/7.
 */

public class TestCanvas extends View {

    RelativeGroup group;

    public TestCanvas(Context context) {
        super(context);
        init();
    }



    public TestCanvas(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public TestCanvas(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }
    private void init() {
        group=new RelativeGroup();
        OvalShape c2=new OvalShape(Color.RED);
        c2.setSize(200,200);
        OvalShape c4=new OvalShape(Color.MAGENTA);
        c4.setSize(300,300);
        c4.setFill(false);
        OvalShape c3=new OvalShape(Color.GREEN);
        c3.setSize(200,200);
        LinearGroup group1 =new LinearGroup();
        group1.setOrientation(LinearGroup.HORIZONTAL);
        group1.setGravity(LinearGroup.CENTER);
        group1.addShape(c2);
        group1.addShape(c4);
        group1.addShape(c3);
        group1.setSize(MeasueType.WRAP_PARENT,MeasueType.WRAP_PARENT);
        TextShape textShape=new TextShape("hello world");
        LinearGroup group2 = new LinearGroup();
        group2.setOrientation(LinearGroup.VERTICAL);
        group2.setGravity(LinearGroup.CENTER);
        group2.setSize(MeasueType.WRAP_PARENT,MeasueType.WRAP_PARENT);
        TextShape t2=new TextShape("above WTF!");
        t2.setTextColor(Color.GRAY);
        group2.addShape(group1);
        group2.addShape(textShape);
        group.addShape(group2);
        group.addShape(t2);
        group.addRule(group2,group, RelativeGroup.Rule.CENTER_V);
        group.addRule(group2,group, RelativeGroup.Rule.CENTER_H);
        group.addRule(t2,group2, RelativeGroup.Rule.TO_ABOVE);
        group.addRule(t2,group2, RelativeGroup.Rule.ALIGN_RIGHT);
        Bitmap b= BitmapFactory.decodeResource(getContext().getResources(),R.drawable.emoji);
        BitmapShape bitmapShape=new BitmapShape(b);
        bitmapShape.setSize(MeasueType.WRAP_PARENT,MeasueType.WRAP_PARENT);
        group.addShape(bitmapShape,20);
        group.addRule(bitmapShape,textShape, RelativeGroup.Rule.TO_BELOW);
        group.addRule(bitmapShape,group, RelativeGroup.Rule.CENTER_V);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        group.setSize(getWidth(),getHeight());
        group.asRoot(canvas);
    }
}
