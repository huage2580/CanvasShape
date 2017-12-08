package com.hua.canvasshape.shape;

import android.graphics.Canvas;

/**
 * 类似于FrameLayout
 * Created by hua on 2017/12/7.
 */

public class FrameGroup extends ShapeGroup{

    @Override
    void layoutChild(int x, int y) {
        for (int i=0;i<childs.size();i++){
            ExShape child=childs.get(i);
            ExShapeMargin margin = margins.get(i);
            child.layout(x+margin.marginLeft,y+margin.marginTop);
        }
    }

}
