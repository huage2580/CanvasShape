package com.hua.canvasshape.shape;

import android.graphics.Rect;
import android.util.Log;

import java.util.LinkedHashMap;
import java.util.LinkedList;

/**
 * Created by hua on 2017/12/7.
 */

public class RelativeGroup extends ShapeGroup {
    private static final String TAG = "RelativeGroup";

    LinkedList<Rule> rules = new LinkedList<>();

    @Override
    void layoutChild(int x, int y) {
        for (int i=0;i<childs.size();i++) {
            ExShape child = childs.get(i);
            ExShapeMargin margin = margins.get(i);
            if (!hasRules(child)){
                child.layout(x+margin.marginLeft,y+margin.marginTop);
                continue;
            }
            int tempX=margin.marginLeft;
            int tempY=margin.marginTop;
            for (Rule rule:rules){
                if (rule.self.equals(child)){
                    Rect targetRect = rule.target.getDstRect();
                    Log.i(TAG, "layoutChild: target="+targetRect);
                    switch (rule.ruleType){
                        case Rule.ALIGN_TOP:
                            tempY=margin.marginTop+targetRect.top;
                            break;
                        case Rule.ALIGN_LEFT:
                            tempX = margin.marginLeft+targetRect.left;
                            break;
                        case Rule.ALIGN_RIGHT:
                            tempX = targetRect.right-margin.marginRight-child.getWidth();
                            break;
                        case Rule.ALIGN_BOTTOM:
                            tempY=targetRect.bottom-margin.marginBottom-child.getHeight();
                            break;
                        case Rule.TO_ABOVE:
                            tempY=targetRect.top-child.getHeight()+margin.marginBottom;
                            break;
                        case Rule.TO_BELOW:
                            tempY=targetRect.bottom+margin.marginTop;
                            break;
                        case Rule.TO_LEFT:
                            tempX=targetRect.left-child.getWidth()+margin.marginRight;
                            break;
                        case Rule.TO_RIGHT:
                            tempX=targetRect.right+margin.marginLeft;
                            break;
                        case Rule.CENTER_H:
                            tempY=targetRect.top+(targetRect.height()-child.getHeight())/2;
                            break;
                        case Rule.CENTER_V:
                            tempX=targetRect.left+(targetRect.width()-child.getWidth())/2;
                            break;
                        default:
                            throw new RuntimeException("not support rule:"+rule.ruleType);
                    }
                }
            }
            Log.i(TAG, "layoutChild: "+tempX+" "+tempY);
            child.layout(tempX,tempY);
        }
    }

    private boolean hasRules(ExShape child){
        for (Rule rule:rules){
            if (rule.self.equals(child)){
                return true;
            }
        }
        return false;
    }
    public void addRule(ExShape self,ExShape target,int rule){
        rules.add(new Rule(self,target,rule));
    }

    /**
     * 依赖规则
     */
    public class Rule{
        public static final int ALIGN_TOP=1;
        public static final int ALIGN_RIGHT=2;
        public static final int ALIGN_LEFT=3;
        public static final int ALIGN_BOTTOM=4;
        public static final int TO_RIGHT=5;
        public static final int TO_LEFT=6;
        public static final int TO_ABOVE=7;
        public static final int TO_BELOW=8;
        public static final int CENTER_H=9;
        public static final int CENTER_V=10;
        ExShape self;
        ExShape target;
        int ruleType;

        public Rule(ExShape self, ExShape target, int ruleType) {
            this.self = self;
            this.target = target;
            this.ruleType = ruleType;
        }

        public ExShape getSelf() {
            return self;
        }

        public void setSelf(ExShape self) {
            this.self = self;
        }

        public ExShape getTarget() {
            return target;
        }

        public void setTarget(ExShape target) {
            this.target = target;
        }

        public int getRuleType() {
            return ruleType;
        }

        public void setRuleType(int ruleType) {
            this.ruleType = ruleType;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Rule rule = (Rule) o;

            if (ruleType != rule.ruleType) return false;
            if (!self.equals(rule.self)) return false;
            return target.equals(rule.target);
        }

        @Override
        public int hashCode() {
            int result = self.hashCode();
            result = 31 * result + target.hashCode();
            result = 31 * result + ruleType;
            return result;
        }
    }
}
