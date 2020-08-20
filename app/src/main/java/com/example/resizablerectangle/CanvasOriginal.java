package com.example.resizablerectangle;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

public class CanvasOriginal extends View {

    private PathListener listener;
    public static Path path = new Path();
    private Paint paint = new Paint();
    private boolean isCanvasSet;

    public CanvasOriginal(Context context) {
        super(context);
    }

    public CanvasOriginal(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public CanvasOriginal(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    public void setCanvas() {
        paint.setAntiAlias(true);
        paint.setColor(Color.WHITE);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeJoin(Paint.Join.ROUND);
        paint.setStrokeWidth(5f);
        isCanvasSet = true;
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float pointX = event.getX();
        float pointY = event.getY();

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                path.moveTo(pointX, pointY);
                listener.onCopy(path);
                return true;
            case MotionEvent.ACTION_MOVE:
                path.lineTo(pointX, pointY);
                listener.onCopy(path);
                break;
            default:
                return false;
        }

        return false;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (!isCanvasSet)
            setCanvas();

        canvas.drawColor(Color.parseColor("#252525"));
        canvas.drawPath(path, paint);
        listener.onCopy(path);
        postInvalidate();
    }

    public interface PathListener {
        void onCopy(Path path);
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        try {
            listener = (PathListener) getContext();
        } catch (ClassCastException e) {
            throw new ClassCastException(getContext().toString() + "must implement BottomSheetListener");
        }
    }
}
