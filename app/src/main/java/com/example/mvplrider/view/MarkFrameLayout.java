package com.example.mvplrider.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.drawable.ColorDrawable;
import android.text.TextPaint;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.mvplrider.R;

public class MarkFrameLayout extends FrameLayout {
 
    private static final int DEFAULT_DEGRESES = -15;//水印倾斜角度
    private static final int DEFAULT_MARK_PAINT_COLOR = Color.parseColor("#FFCCCCCC");//水印颜色
    private static final int DEFAULT_ALPHA = (int) (0.5 * 255);//水印透明度
    private static final String DEFAULT_MARK_SHOW_VALUE = "[水印]";//水印内容
 
    private boolean showMark = true;
    private float mMarkTextSize;
    private int mMarkTextColor;
    private boolean mMarkLayerIsForeground; //水印绘制在控件背景上，还是前景色上
    private float mDegrees;
    private int mVerticalSpacing;
    private int mHorizontalSpacing;
    private int mMarkPainAlpha;
    private String mMarkValue;
    private TextPaint mMarkPaint;
    private Bitmap mMarkBitmap;
 
    public MarkFrameLayout(@NonNull Context context) {
        this(context, null);
    }
 
    public MarkFrameLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        if (showMark) {
            int defaultMarkTextSize = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 12, getResources().getDisplayMetrics());
            int defaultSpacing = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 24, getResources().getDisplayMetrics());
 
            TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.MarkFrameLayout);
            mDegrees = a.getInteger(R.styleable.MarkFrameLayout_mark_rotate_degrees, DEFAULT_DEGRESES);
            mMarkTextColor = a.getColor(R.styleable.MarkFrameLayout_mark_textcolor, DEFAULT_MARK_PAINT_COLOR);
            mMarkTextSize = a.getDimension(R.styleable.MarkFrameLayout_mark_textsize, defaultMarkTextSize);
            mMarkPainAlpha = a.getInt(R.styleable.MarkFrameLayout_mark_alpha, DEFAULT_ALPHA);
            mMarkLayerIsForeground = a.getBoolean(R.styleable.MarkFrameLayout_mark_is_foreground, true);//默认绘制在前景色上
            mHorizontalSpacing = (int) a.getDimension(R.styleable.MarkFrameLayout_mark_hor_spacing, defaultSpacing);
            mVerticalSpacing = (int) a.getDimension(R.styleable.MarkFrameLayout_mark_ver_spacing, defaultSpacing);
            mMarkValue = a.getString(R.styleable.MarkFrameLayout_mark_show_value);
            mMarkValue = TextUtils.isEmpty(mMarkValue) ? DEFAULT_MARK_SHOW_VALUE : mMarkValue;
 
            a.recycle();
            initWaterPaint();
            setForeground(new ColorDrawable(Color.TRANSPARENT)); //重置前景色透明
        }
    }
 
    @Override
    public void onDrawForeground(Canvas canvas) {
        super.onDrawForeground(canvas);
        if (showMark && mMarkLayerIsForeground) {
            drawMark(canvas); //绘制前景色
        }
    }
 
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (showMark && !mMarkLayerIsForeground) {
            drawMark(canvas); //绘制被景色
        }
    }
 
    private void initWaterPaint() {
        //初始化Mark的Paint
        mMarkPaint = new TextPaint(Paint.ANTI_ALIAS_FLAG); //mMarkPaint.setAntiAlias(true)
        mMarkPaint.setColor(mMarkTextColor);
        mMarkPaint.setAlpha(mMarkPainAlpha);
        mMarkPaint.setTextSize(mMarkTextSize);
        //初始化MarkBitmap
        Paint.FontMetrics fontMetrics = mMarkPaint.getFontMetrics();
        int textHeight = (int) (fontMetrics.bottom - fontMetrics.top);
        int textLength = (int) mMarkPaint.measureText(mMarkValue);
        mMarkBitmap = Bitmap.createBitmap(textLength + 2 * mHorizontalSpacing,
                textHeight + mVerticalSpacing * 2, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(mMarkBitmap);
        canvas.drawText(mMarkValue, mHorizontalSpacing, mVerticalSpacing, mMarkPaint);
    }
 
    private void drawMark(Canvas canvas) {
        int maxSize = Math.max(getMeasuredWidth(), getMeasuredHeight());
        mMarkPaint.setShader(new BitmapShader(mMarkBitmap, Shader.TileMode.REPEAT, Shader.TileMode.REPEAT));
        canvas.save();
        canvas.translate(-(maxSize - getMeasuredWidth()) / 2, 0);
        canvas.rotate(mDegrees, maxSize / 2, maxSize / 2);
        canvas.drawRect(new RectF(0, 0, maxSize, maxSize), mMarkPaint);
        canvas.restore();
    }
 
    public void setShowMark(boolean showMark) {
        this.showMark = showMark;
        invalidate();
    }
}