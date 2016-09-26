package net.catzie.compassreference.customViews;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * Created by Catzie on 21/09/2016.
 */
public class CompassBase extends View {

    private String TAG = "CompassBase";
    private Paint mPaint;
    private int myWidth;
    private int myHeight;
    private int radius;



    public CompassBase(Context context) { // view created in code
        super(context);

        initialize();
    }

    public CompassBase(Context context, AttributeSet attrs) { // view created through resource
        super(context, attrs);
        initialize();

    }
    public CompassBase(Context context, AttributeSet attrs, int defaultStyle) { // view created through inflation
        super(context, attrs, defaultStyle);
        initialize();

    }
    private void initialize(){
        // Initialize
        mPaint = new Paint();

    }


    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh){

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



        /**
         * Set height
         */

        int hSpecMode = MeasureSpec.getMode(hMeasureSpec);
        int hSpecSize = MeasureSpec.getSize(hMeasureSpec);
        myHeight = hSpecSize;

        if(hSpecMode == MeasureSpec.EXACTLY){

            myHeight = hSpecSize;

        } else if (hSpecMode == MeasureSpec.AT_MOST){
            // wrap content
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
            // wrap content
        }

        /**
         * Call after determining size of "control"
         * Without calling this, app will crash as soon as view is laid out
         */

        setMeasuredDimension(myWidth, myHeight); //w and h of circle



    }


    @Override
    protected void onDraw(Canvas canvas){

        super.onDraw(canvas);

        // Border Circle

        mPaint.setStyle(Paint.Style.FILL);

        mPaint.setAntiAlias(true);
        mPaint.setColor(Color.LTGRAY);
        radius = getWidth();

        int radHalf = radius / 2;
        canvas.drawCircle(radHalf, radHalf, radHalf, mPaint);

        // Middle Circle
        mPaint.setStyle(Paint.Style.FILL);

        mPaint.setColor(Color.LTGRAY);
        canvas.drawCircle(radHalf, radHalf, (float) radius/15, mPaint);

        // Text

        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setColor(Color.BLACK);
        mPaint.setTextSize(70);
        canvas.drawText("N", radius/2.15f, radius/8, mPaint);
        canvas.drawText("S", radius/2.15f, radius/1.05f, mPaint);
        canvas.drawText("E", radius/1.15f, radius/1.9f, mPaint);
        canvas.drawText("W", radius/17.8f, radius/1.9f, mPaint);



    }

}
