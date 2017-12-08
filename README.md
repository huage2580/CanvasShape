# CanvasShape

### use canvas like view&viewgroup 

now support shapeGroup:  
`FrameGroup`,`LinearGroup`,`RelativeGroup`  
now support shape:  
`OvalShape`,`TextShape`,`BitmapShape`  
### for more shape
you can extends class `ExShape`and override `onMeasure` or( ` measureWidth`&`measureHeight`) to support `wrap_content`,but it's not necessary.  
you need to override `ondraw` to draw the shape content;(see how the other shape work);  

### for more shapeGroup

just extends the class `shapegroup` and override `layoutChild`,if you want to support `wrap_content` then override `onMeasure`;(see how `LinearGroup` work);

### how to use

see the class `TestCanvas`  
and this is some code to guide you:  
use `ExShape.debug` to enable/disable draw debug rect;
```java
        LinearGroup group2 = new LinearGroup();
        group2.setOrientation(LinearGroup.VERTICAL);
        group2.setGravity(LinearGroup.CENTER);
        group2.setSize(MeasueType.WRAP_PARENT,MeasueType.WRAP_PARENT);
```

```java
        RelativeGroup group=new RelativeGroup();
        group.addShape(bitmapShape,20);
        group.addRule(bitmapShape,textShape, RelativeGroup.Rule.TO_BELOW);
        group.addRule(bitmapShape,group, RelativeGroup.Rule.CENTER_V);
```
```java
 @Override
    protected void onDraw(Canvas canvas) {
        group.setSize(getWidth(),getHeight());
        group.asRoot(canvas);
    }
``` 
![image](https://github.com/huage2580/CanvasShape/blob/master/capture/device-2017-12-08-100316.png)
