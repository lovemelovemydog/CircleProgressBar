package com.example.roundprogressbar;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import com.example.circlepregress.R;

public class CircleProgressBar extends View {

    private Paint paint;
    private int circleColor;
    private int circleProgressColor;
    private float circleWidth;
    private int max = 100;
    private int progress = 0;
    private boolean ispointer = false;
    private int pointerColor;
    private float pointerWidth;

    public CircleProgressBar(Context context) {
        this(context, null);
    }

    public CircleProgressBar(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CircleProgressBar(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        paint = new Paint();

        //get values
        TypedArray mTypedArray = context.obtainStyledAttributes(attrs, R.styleable.CircleProgressBar);
        circleColor = mTypedArray.getColor(R.styleable.CircleProgressBar_circleColor, Color.GRAY);
        circleProgressColor = mTypedArray.getColor(R.styleable.CircleProgressBar_circleProgressColor, Color.RED);
        circleWidth = mTypedArray.getDimension(R.styleable.CircleProgressBar_circleWidth, 5);
        max = mTypedArray.getInteger(R.styleable.CircleProgressBar_max, 100);
        ispointer = mTypedArray.getBoolean(R.styleable.CircleProgressBar_ispointer, false);
        pointerColor = mTypedArray.getColor(R.styleable.CircleProgressBar_pointerColor, Color.GRAY);
        pointerWidth = mTypedArray.getDimension(R.styleable.CircleProgressBar_pointerWidth, 3);


        mTypedArray.recycle();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        //drawCircle
        int centre = getWidth() / 2;
        int radius = centre - (int) circleWidth; //get radius
        paint.setColor(circleColor);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(circleWidth);
        paint.setAntiAlias(true);
        canvas.drawCircle(centre, centre, radius, paint);

        //set progress  drawArc
        paint.setStrokeWidth(circleWidth);
        paint.setColor(circleProgressColor);
        RectF oval = new RectF(centre - radius, centre - radius, centre + radius, centre + radius);
        paint.setStyle(Paint.Style.STROKE);
        canvas.drawArc(oval, -90, 360 * progress / max, false, paint);

        if (ispointer) {
            int r = (int) ((float) radius * (2F / 3F));//set a new radius
            paint.setStrokeWidth(pointerWidth);
            paint.setColor(pointerColor);
            RectF rf = new RectF(centre - r, centre - r, centre + r, centre + r);
            paint.setStyle(Paint.Style.STROKE);
            canvas.drawArc(rf, -90, 0, true, paint);  //pointer first not move

            canvas.drawArc(rf, -90 + 360 * progress / max, 0, true, paint);  //pointer second  move by progress
        }
    }


    public synchronized int getMax() {
        return max;
    }


    public synchronized void setMax(int max) {
        if (max < 0) {
            throw new IllegalArgumentException("max not less than 0");
        }
        this.max = max;
    }


    public synchronized int getProgress() {
        return progress;
    }


    public synchronized void setProgress(int progress) {
        if (progress < 0) {
            throw new IllegalArgumentException("progress not less than 0");
        }
        if (progress > max) {
            progress = max;
        }
        if (progress <= max) {
            this.progress = progress;
            postInvalidate();
        }
    }

    public int getCricleColor() {
        return circleColor;
    }

    public void setCricleColor(int cricleColor) {
        this.circleColor = cricleColor;
    }

    public int getCricleProgressColor() {
        return circleProgressColor;
    }

    public void setCricleProgressColor(int cricleProgressColor) {
        this.circleProgressColor = cricleProgressColor;
    }

    public float getRoundWidth() {
        return circleWidth;
    }

    public void setRoundWidth(float roundWidth) {
        this.circleWidth = roundWidth;
    }

    public Paint getPaint() {
        return paint;
    }

    public void setPaint(Paint paint) {
        this.paint = paint;
    }

    public int getCircleColor() {
        return circleColor;
    }

    public void setCircleColor(int circleColor) {
        this.circleColor = circleColor;
    }

    public int getCircleProgressColor() {
        return circleProgressColor;
    }

    public void setCircleProgressColor(int circleProgressColor) {
        this.circleProgressColor = circleProgressColor;
    }

    public float getCircleWidth() {
        return circleWidth;
    }

    public void setCircleWidth(float circleWidth) {
        this.circleWidth = circleWidth;
    }

    public boolean isIspointer() {
        return ispointer;
    }

    public void setIspointer(boolean ispointer) {
        this.ispointer = ispointer;
    }

    public int getPointerColor() {
        return pointerColor;
    }

    public void setPointerColor(int pointerColor) {
        this.pointerColor = pointerColor;
    }

    public float getPointerWidth() {
        return pointerWidth;
    }

    public void setPointerWidth(float pointerWidth) {
        this.pointerWidth = pointerWidth;
    }
}
