package com.example.mvplrider.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import com.example.mvplrider.R;
import com.example.mvplrider.utils.ImageLoader;

/**
 *
 */
public class CircleView extends View {

    private Paint paint;
    private int x, y;
    private int r = 50;

    public CircleView(Context context){
        this(context,null);
    }

    public CircleView(Context context, @Nullable AttributeSet attrs){
        this(context,attrs,0);
    }

    public CircleView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initPart();
    }

    private void initPart() {
        paint = new Paint();
        paint.setColor(Color.BLUE);
        paint.setStrokeWidth(2);
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        x = r;
        y = r;
        canvas.drawCircle(x, y, r, paint);
        paint.setColor(Color.RED);
        paint.setTextSize(30);
        paint.setStrokeWidth(4);
        canvas.drawText("hello", 20, 20, paint);
//        final Bitmap bmm = ImageLoader.getIconBitmap(getContext(), R.mipmap.ic_launcher);
        //Bitmap drawable = BitmapFactory.decodeResource(getResources(),R.mipmap.ic_launcher);
//        canvas.drawBitmap(bmm, 0, 0, paint);
    }
}
