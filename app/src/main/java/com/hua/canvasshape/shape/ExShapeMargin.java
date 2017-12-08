package com.hua.canvasshape.shape;

/**
 * Created by hua on 2017/12/7.
 */

public class ExShapeMargin {
    int marginTop=0;
    int marginLeft=0;
    int marginBottom=0;
    int marginRight=0;

    public ExShapeMargin() {
    }

    public ExShapeMargin(int marginTop, int marginLeft, int marginBottom, int marginRight) {
        this.marginTop = marginTop;
        this.marginLeft = marginLeft;
        this.marginBottom = marginBottom;
        this.marginRight = marginRight;
    }

    public int getMarginTop() {
        return marginTop;
    }

    public void setMarginTop(int marginTop) {
        this.marginTop = marginTop;
    }

    public int getMarginLeft() {
        return marginLeft;
    }

    public void setMarginLeft(int marginLeft) {
        this.marginLeft = marginLeft;
    }

    public int getMarginBottom() {
        return marginBottom;
    }

    public void setMarginBottom(int marginBottom) {
        this.marginBottom = marginBottom;
    }

    public int getMarginRight() {
        return marginRight;
    }

    public void setMarginRight(int marginRight) {
        this.marginRight = marginRight;
    }
}
