package com.example.resizablerectangle;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.view.MotionEvent;
import  android.view.View;

import java.util.ArrayList;


public class CanvasOriginal extends View {

    Paint paint;
    Path path;

    DataSender dataListener;
    int fragID;

    ArrayList<PathStorage> PathDrawnData;


    public CanvasOriginal(Context context, DataSender dataListener, int fragid) {
        super(context);
        this.dataListener = dataListener;
        this.fragID = fragid;

        PathDrawnData=new ArrayList<>();

        paint = new Paint();
        path = new Path();
        paint.setAntiAlias(true);
        paint.setColor(Color.parseColor("#40C8E8"));
        paint.setStrokeJoin(Paint.Join.ROUND);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(5f);
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float xpos = event.getX();
        float ypos = event.getY();


        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                path.moveTo(xpos,ypos);
                MainActivity.Receiver.ToSendPath(PathDrawnData,path, fragID);

                return true;
            case MotionEvent.ACTION_MOVE:
                path.lineTo(xpos,ypos);
                MainActivity.Receiver.ToSendPath(PathDrawnData,path, fragID);
                break;

            case MotionEvent.ACTION_UP:
                PathDrawnData.add(new PathStorage(path));
                path=new Path();
                break;

            default:
                return false;
        }
        invalidate();
        return true;
    }


    @Override
    protected void onDraw(android.graphics.Canvas canvas) {
        super.onDraw(canvas);

        for (int i = 0; i < PathDrawnData.size(); i++){
            canvas.drawPath(PathDrawnData.get(i)._path, paint);
        }
        canvas.drawPath(path, paint);

    }

    public void setPaintPath(Path path){
        this.path = path;
        invalidate();
    }

    public void setPathData(ArrayList<PathStorage> pathData){
        PathDrawnData = pathData;
        invalidate();
    }
}