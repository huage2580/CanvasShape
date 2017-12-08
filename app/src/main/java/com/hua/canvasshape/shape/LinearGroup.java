package com.hua.canvasshape.shape;


import android.util.Log;

/**
 * 线性布局
 * Created by hua on 2017/12/7.
 */

public class LinearGroup extends ShapeGroup{
    private static final String TAG = "LinearGroup";
    public static final int VERTICAL =2;
    public static final int HORIZONTAL =1;
    public static final int START =1;
    public static final int END =2;
    public static final int CENTER =3;

    int orientation=HORIZONTAL;
    int gravity=START;

    public void setOrientation(int orientation) {
        this.orientation = orientation;
    }

    public void setGravity(int gravity) {
        this.gravity = gravity;
    }


    @Override
    void measureHeight(int suggestHeight) {
        if (getHeight()==MeasueType.WRAP_PARENT && orientation==HORIZONTAL){
            int maxHeight=0;
            for (int i=0;i<childs.size();i++) {
                ExShape child = childs.get(i);
                ExShapeMargin margin = margins.get(i);
               maxHeight=Math.max(child.getHeight()+margin.marginTop+margin.marginBottom,maxHeight);
            }
            setHeight(maxHeight);
        }else if (getHeight()==MeasueType.WRAP_PARENT && orientation==VERTICAL){
            int all=0;
            for (int i=0;i<childs.size();i++) {
                ExShape child = childs.get(i);
                ExShapeMargin margin = margins.get(i);
                all=all+(child.getHeight()+margin.marginTop+margin.marginBottom);
            }
            setHeight(all);
        }
        super.measureHeight(suggestHeight);
    }

    @Override
    void measureWidth(int suggestWidth) {
        if (getWidth()==MeasueType.WRAP_PARENT && orientation == VERTICAL){
            int maxWidth=0;
            for (int i=0;i<childs.size();i++) {
                ExShape child = childs.get(i);
                ExShapeMargin margin = margins.get(i);
                maxWidth=Math.max(child.getWidth()+margin.marginLeft+margin.marginRight,maxWidth);
            }
            setWidth(maxWidth);
        }else if (getWidth()==MeasueType.WRAP_PARENT && orientation == HORIZONTAL){
            int all=0;
            for (int i=0;i<childs.size();i++) {
                ExShape child = childs.get(i);
                ExShapeMargin margin = margins.get(i);
                all=all+(child.getWidth()+margin.marginLeft+margin.marginRight);
            }
            setWidth(all);
        }
        super.measureWidth(suggestWidth);
    }

    @Override
    void layoutChild(int x, int y) {
        int bx=x;
        int by=y;
        for (int i=0;i<childs.size();i++){
            ExShape child=childs.get(i);
            ExShapeMargin margin = margins.get(i);
            int gravityCompensation=0;
            if (gravity==START){
                gravityCompensation=0;
            }else if (gravity==END){
                if (orientation==HORIZONTAL){
                    gravityCompensation = getHeight()-child.getHeight()-margin.marginTop;
                }else {
                    gravityCompensation = getWidth()-child.getWidth() -margin.marginLeft;
                }
            }else if (gravity == CENTER){
                if (orientation==HORIZONTAL){
                    gravityCompensation =(getHeight()-child.getHeight()-margin.marginTop-margin.marginBottom)/2;
                }else {
                    gravityCompensation = (getWidth()-child.getWidth()-margin.marginLeft-margin.marginRight)/2;
                }
            }
            if (orientation == VERTICAL){
                child.layout(margin.marginLeft+bx+gravityCompensation,margin.marginTop+by);
                by+=margin.marginTop+child.getHeight()+margin.marginBottom;
            }else {
                child.layout(margin.marginLeft+bx,margin.marginTop+by+gravityCompensation);
                bx+=margin.marginLeft+child.getWidth()+margin.marginRight;
            }

        }
    }

}
