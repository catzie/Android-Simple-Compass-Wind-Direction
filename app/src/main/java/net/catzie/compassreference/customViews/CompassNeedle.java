package net.catzie.compassreference.customViews;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * Created by Catzie on 24/09/2016.
 */
public class CompassNeedle extends View {

    private String TAG = "CompassNeedle";
    private Paint mPaint;
    private Paint mPaintInnerCircle;
    private Paint mPaintPointer;
    private int myWidth;
    private int myHeight;
    private int radius;
    private Bitmap bitmapCanvasInnerCircle;
    private Canvas canvasInnerCircle;


    public CompassNeedle(Context context) { // view created in code
        super(context);

        initialize();
    }

    public CompassNeedle(Context context, AttributeSet attrs) { // view created through resource
        super(context, attrs);
        initialize();

    }
    public CompassNeedle(Context context, AttributeSet attrs, int defaultStyle) { // view created through inflation
        super(context, attrs, defaultStyle);
        initialize();

    }
    private void initialize(){
        // Initialize
        mPaint = new Paint();
//        canvasInnerCircle = new Canvas (bitmapCanvasInnerCircle);
    }


    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh){
        Log.v(TAG, "onSizeChanged: w=" + w + ", h=" + h + ", oldW=" + oldw + ", oldH=" + oldh);
        super.onSizeChanged(w, h, oldw, oldh);
    }

    /**
     * The base View class draws a borderless empty 100px x 100 px box
     * To change that, we override the onMeasure handler
     * which allows us to indicate the view size
     * -
     * onMeasure is called as your view's parent is laying out its children
     * -
     * wMeasureSpec & hMeasureSpec: how much space is available and
     * whether the view will be given that much space, or at most that much space
     */
    @Override
    protected void onMeasure(int wMeasureSpec, int hMeasureSpec){

        Log.v(TAG, "onMeasure RAWs: wMeasureSpec=" + wMeasureSpec + ", hMeasureSpec" + wMeasureSpec);

        /**
         * Set height
         */

        int hSpecMode = MeasureSpec.getMode(hMeasureSpec);
        int hSpecSize = MeasureSpec.getSize(hMeasureSpec);
        myHeight = hSpecSize;

        // if mode is EXACTLY, the view will be placed into an area of exactly that size
        // EXACTLY is value passed if layout specified specific size of view is asked to fill parent

        if(hSpecMode == MeasureSpec.EXACTLY){

            // best practice to simply return hSpecSize (value passed in),
            // unless that value is below your view's minimum size in which case you should
            //        return min value and rely on parent layout to crop or scroll as needed

            myHeight = hSpecSize;

        }

        // AT_MOST indicates your view can define own siz eup to size given
        // typically for views set to wrap content

        else if (hSpecMode == MeasureSpec.AT_MOST){

//            myHeight = Math.min(desiredHeight, hSpecSize);

        }

        /**
         * Set width
         */

        int wSpecMode = MeasureSpec.getMode(wMeasureSpec);
        int wSpecSize = MeasureSpec.getSize(wMeasureSpec);
        myWidth = wSpecSize;
        if(wSpecMode == MeasureSpec.EXACTLY) {
            myWidth = wSpecSize;
        }
        else if (wSpecMode == MeasureSpec.AT_MOST){
//            myWidth = Math.min(desiredWidth, wSpecSize);
        }

        /**
         * Call after determining size of "control"
         * Without calling this, app will crash as soon as view is laid out
         */

        setMeasuredDimension(myWidth, myHeight); //w and h of circle

        Log.d(TAG, "onMeasre, myWidth="+myWidth+", myHeight="+myHeight);

    }


    /**
     * Draw our own custom view content
     * -
     * Android Canvas is standard canvas API.
     * Uses a painter's algo (what you paint covers anything beneath it)
     * -
     * The android.graphics framework divides drawing into two areas: canvas (what to draw)
     * and paint (how to draw)
     * -
     * Canvas and Paint classes offer variety of brushes and help us draw
     * and fill lines, boxes, circles and text with colors/patterns/gradient/images
     * and offers moving, rotation and resizing of canvas
     * -
     * The specifics of what you draw are different for every view,
     * but one thing that's consistent is the resource constraint device you're drawing on,
     * and that the onDraw method will be called every time the screen is refreshed
     * potentially many times a second
     * -
     * Any object created within onDraw including Pain objects will be created and
     * destroyed at an alarming frequency. Object creation and descrution can be expensive on Android,
     * potentially affecting smoothness of UI  when garbage colleciton is initiated
     * Solution: move the scope of any object used within the onDraw loop into the class scope
     */

    @Override
    protected void onDraw(Canvas canvas){

        // create custom control that can display  wind speed & direction
        // when done, add to layout using full package name and class name in xml like:
        // <com.myapp.MyView android:height="..." android:width="..." />

        super.onDraw(canvas);

        // Border Circle

        radius = getWidth();

        int radHalf = radius / 2;
/*
        mPaint.setStyle(Paint.Style.FILL);

        mPaint.setAntiAlias(true);
        mPaint.setColor(Color.LTGRAY);
        canvas.drawCircle(radHalf, radHalf, radHalf, mPaint);*/

        // Inner Circle
        mPaint.setStyle(Paint.Style.FILL);

        mPaint.setColor(Color.DKGRAY);

        canvas.drawCircle(radHalf, radHalf, radHalf, mPaint);

        // Middle Circle
        mPaint.setStyle(Paint.Style.FILL);

        mPaint.setColor(Color.LTGRAY);
        canvas.drawCircle(radHalf, radHalf, (float) radius/15, mPaint);

        // Text
/*
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setColor(Color.BLACK);
        mPaint.setTextSize(45);
        canvas.drawText("N", radius/2.1f, radius/8, mPaint);
        canvas.drawText("S", radius/2.1f, radius/1.05f, mPaint);
        canvas.drawText("E", radius/1.15f, radius/1.9f, mPaint);
        canvas.drawText("W", radius/25, radius/1.9f, mPaint);*/

        // Pointer

//        mPaint.setStrokeWidth(4);
        mPaint.setColor(Color.GREEN);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setAntiAlias(true);
        mPaint.setStrokeWidth(10);

        Point a = new Point(radHalf, 0);
//        Point b = new Point(0, 200);
        Point c = new Point(radHalf, radHalf);

        Path path = new Path();
        path.setFillType(Path.FillType.EVEN_ODD);
//        path.lineTo(b.x, b.y);
        path.moveTo(a.x, a.y);
        path.lineTo(c.x, c.y);
        path.close();

//        canvas.rotate(45);
        canvas.drawPath(path, mPaint);


        Log.d(TAG, "radius=" + radius);

    }

}
