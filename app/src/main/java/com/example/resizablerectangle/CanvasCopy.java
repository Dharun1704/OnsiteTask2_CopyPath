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
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class CanvasCopy extends View implements CanvasOriginal.PathListener{


    public ViewGroup.LayoutParams params;
    private Paint paint = new Paint();
    private Path pathC = new Path();
    private boolean isCanvasSet;

    float xPoint,yPoint;

    public CanvasCopy(Context context) {
        super(context);
    }

    public CanvasCopy(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public CanvasCopy(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setCanvas() {
        paint.setAntiAlias(true);
        paint.setColor(Color.parseColor("#03DAC5"));
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeJoin(Paint.Join.ROUND);
        paint.setStrokeWidth(5f);

        params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        isCanvasSet = true;
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public boolean onTouchEvent(MotionEvent event) {

        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            MainActivity.toast = Toast.makeText(getContext(), "Cannot draw in carbon page. Draw the path in draw page.",
                    Toast.LENGTH_SHORT);
            MainActivity.toast.show();
            return true;
        }
        return false;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (!isCanvasSet)
            setCanvas();

        canvas.drawColor(Color.parseColor("#252525"));
        canvas.drawPath(CanvasOriginal.path, paint);
        postInvalidate();
    }

    @Override
    public void onCopy(Path path) {
        pathC = path;
    }
}
