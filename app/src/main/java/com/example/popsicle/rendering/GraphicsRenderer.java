package com.example.popsicle.rendering;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.Log;
import android.view.SurfaceHolder;

import androidx.annotation.NonNull;

import com.example.popsicle.models.Universe;

public class GraphicsRenderer implements Universe.Callback, SurfaceHolder.Callback{

    private final static String TAG = "RenderingObjects";
    private final Universe universe;
    private SurfaceHolder holder;

    public GraphicsRenderer(Universe u){
        this.universe = u;
    }

    /**
     * The functions here are given.
     * Please dont touch it.
     * @param surfaceHolder
     */

    @Override
    public void surfaceCreated(@NonNull SurfaceHolder surfaceHolder) {
        Log.d(TAG, "start surfaceCreated");
        this.holder = surfaceHolder;
        drawSurfaceView();
    }

    @Override
    public void surfaceChanged(@NonNull SurfaceHolder surfaceHolder, int i, int i1, int i2) {
        Log.d(TAG, "start surfaceChanged");
        drawSurfaceView();
    }

    @Override
    public void surfaceDestroyed(@NonNull SurfaceHolder surfaceHolder) {
        Log.d(TAG, "start surfaceDestroyed");
        this.holder = null;
    }

    public void drawSurfaceView(){
        if (universe != null && holder != null){
            Canvas canvas = holder.lockCanvas();
            this.draw(canvas);
            holder.unlockCanvasAndPost(canvas);
        } else {
            Log.e(TAG, "error in drawSurfaceView");
        }
    }

    /**
     * Drawing some stuff!
     * @param canvas
     */
    private void draw(Canvas canvas){
        Log.d(TAG, "Start drawing the universe");

        if (universe == null) return;
        if (canvas == null) return;

        // Draw the canvas
        canvas.drawARGB(255, 255, 192, 203);

        // Drawing the characters
        Paint characterPaint = new Paint();
        characterPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        characterPaint.setStrokeWidth(10);
        characterPaint.setARGB(135,0,0,0);

    }

    @Override
    public void universeChanged(Universe u) {
        this.drawSurfaceView();
    }

}
