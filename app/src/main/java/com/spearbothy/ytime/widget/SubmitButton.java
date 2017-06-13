package com.spearbothy.ytime.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;

import com.spearbothy.ytime.R;


/**
 * Created by mahao on 17-6-2.
 */

public class SubmitButton extends AppCompatTextView {

    // 半圆角
    public static final int TYPE_CIRCLE = 0;

    // 直角
    public static final int TYPE_RECT = 1;

    private int mColor;

    private int mCurrentColor = mColor;

    private int mPressColor;

    private int mDisableColor;

    private int mWidth;

    private int mHeight;

    private Paint mPaint;

    private int mType = TYPE_CIRCLE;

    public SubmitButton(Context context) {
        this(context, null);
    }

    public SubmitButton(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SubmitButton(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        super.setBackground(null);
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.SubmitButton);
        mType = a.getInt(R.styleable.SubmitButton_type, 0);
        a.recycle();
        setEnabled(true);
        init();
    }

    private void init() {
        setLayerType(View.LAYER_TYPE_SOFTWARE, null);

        mWidth = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,294,getResources().getDisplayMetrics());
        mHeight =(int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,42,getResources().getDisplayMetrics());

        mColor = getResources().getColor(R.color.submit_button_color);
        mPressColor = getResources().getColor(R.color.submit_button_press);
        mDisableColor = getResources().getColor(R.color.submit_button_disable);

        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.FILL);

        if (isEnabled()) {
            mCurrentColor = mColor;
        } else {
            mCurrentColor = mDisableColor;
        }
    }

    @Override
    public void setEnabled(boolean enabled) {
        super.setEnabled(enabled);
        mCurrentColor = enabled ? mColor : mDisableColor;
        invalidate();
    }

    @Override
    public void setBackground(Drawable background) {}

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (isEnabled()) {
            if (event.getAction() == MotionEvent.ACTION_DOWN) {
                mCurrentColor = mPressColor;
                invalidate();
            } else if (event.getAction() == MotionEvent.ACTION_UP || event.getAction() == MotionEvent.ACTION_CANCEL) {
                mCurrentColor = mColor;
                invalidate();
            }
        }
        return super.onTouchEvent(event);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        if (heightMode == MeasureSpec.EXACTLY) {
            mHeight = MeasureSpec.getSize(heightMeasureSpec);
        }
        int widthMode = MeasureSpec.getMode(heightMeasureSpec);
        if (widthMode == MeasureSpec.EXACTLY) {
            mWidth = MeasureSpec.getSize(widthMeasureSpec);
        }

        super.onMeasure(MeasureSpec.makeMeasureSpec(mWidth, MeasureSpec.EXACTLY),
                MeasureSpec.makeMeasureSpec(mHeight, MeasureSpec.EXACTLY));
    }

    @Override
    protected void onDraw(Canvas canvas) {
        mPaint.setColor(mCurrentColor);
        canvas.save();
        if (mType == TYPE_CIRCLE) {
            drawCircleRect(canvas);
        } else if (mType == TYPE_RECT) {
            drawRect(canvas);
        }
        canvas.restore();
        super.onDraw(canvas);
    }

    private void drawRect(Canvas canvas) {
        canvas.drawRect(0, 0, mWidth, mHeight, mPaint);
    }

    private void drawCircleRect(Canvas canvas) {
        float radius = mHeight / 2f;
        Path path = new Path();
        path.moveTo(radius, 0);
        path.lineTo(mWidth - radius, 0);
        RectF rectF = new RectF(mWidth - 2 * radius, 0, mWidth, mHeight);
        path.arcTo(rectF, 270, 180);
        path.lineTo(radius, mHeight);
        rectF = new RectF(0, 0, 2 * radius, mHeight);
        path.arcTo(rectF, 90, 180);
        canvas.drawPath(path, mPaint);
    }
}
