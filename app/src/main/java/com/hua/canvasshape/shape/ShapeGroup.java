package com.hua.canvasshape.shape;

import android.graphics.Canvas;

import java.util.LinkedList;

/**
 * Created by hua on 2017/12/7.
 */

public abstract class ShapeGroup extends ExShape {
    LinkedList<ExShape> childs = new LinkedList<>();
    LinkedList<ExShapeMargin> margins = new LinkedList<>();
    @Override
    void measure(int suggestWidth, int suggestHeight) {
        dispatchMeasure(suggestWidth,suggestHeight);
        super.measure(suggestWidth, suggestHeight);
    }

    /**
     * 测量child
     * @param suggestWidth group自身的宽度
     * @param suggestHeight group自身的高度
     */
    void dispatchMeasure(int suggestWidth, int suggestHeight){
        for (int i=0;i<childs.size();i++){
            ExShape child=childs.get(i);
            ExShapeMargin margin = margins.get(i);
            child.measure(suggestWidth-margin.marginLeft-margin.marginRight,suggestHeight-margin.marginTop-margin.marginBottom);
        }
    }


    @Override
    void layout(int x,int y) {
        super.layout(x,y);
        layoutChild(x,y);
    }

    /**
     * 布局child
     */
    abstract void layoutChild(int x,int y) ;

    @Override
    void draw(Canvas canvas) {
        super.draw(canvas);
        dispatchDraw(canvas);
    }

    /**
     * 分发测量
     * @param canvas
     */
    void dispatchDraw(Canvas canvas) {
        for (ExShape child:childs){
            child.draw(canvas);
        }
    }

    public void addShape(ExShape child){
        addShape(child,new ExShapeMargin());
    }
    public void addShape(ExShape child,ExShapeMargin margin){
        childs.add(child);
        margins.add(margin);
    }
    public void addShape(ExShape child,int margin){
        childs.add(child);
        margins.add(new ExShapeMargin(margin,margin,margin,margin));
    }

    /**
     * 作为canvas的root布局,绑定画布
     * @param canvas
     */
    public void asRoot(Canvas canvas){
        measure(getWidth(),getHeight());
        layout(0,0);
        draw(canvas);
    }
}
