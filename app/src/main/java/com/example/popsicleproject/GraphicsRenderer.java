package com.example.popsicleproject;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceView;
import android.view.SurfaceHolder;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import androidx.annotation.NonNull;

import com.example.popsicleproject.models.Universe;

import java.util.ArrayList;
import java.util.List;
import java.lang.reflect.Array;


public class GraphicsRenderer extends SurfaceView implements Runnable, Universe.Callback, SurfaceHolder.Callback {

    private final static String TAG = "RenderingObjects";
    private final Universe universe;
    private SurfaceHolder holder ;
    private boolean isPlaying, isGameOver = false;
    private Paint paint;

    private Bitmap characterA_bitmap, characterB_bitmap,
            popsicleA_bitmap, popsicleB_bitmap,cloud_bitmap, syrup_bitmap,
            buttonUp_bitmap, buttonDown_bitmap, buttonLeft_bitmap,buttonRight_bitmap;

    private MainActivity activity;

    public GraphicsRenderer(Universe u, MainActivity activity, int screenX, int screenY, Resources context){
        super(activity);
        this.activity = activity;

        this.universe = u;

        characterA_bitmap = BitmapFactory.decodeResource(context, R.mipmap.char_a);
        characterB_bitmap = BitmapFactory.decodeResource(context, R.mipmap.char_b);
        popsicleA_bitmap = BitmapFactory.decodeResource(context, R.mipmap.popsicle_a);
        popsicleB_bitmap = BitmapFactory.decodeResource(context, R.mipmap.popsicle_b);
        cloud_bitmap = BitmapFactory.decodeResource(context, R.mipmap.cloud);
        syrup_bitmap = BitmapFactory.decodeResource(context,R.mipmap.syrup);
        buttonUp_bitmap = BitmapFactory.decodeResource(context, R.mipmap.up);
        buttonDown_bitmap = BitmapFactory.decodeResource(context, R.mipmap.down);
        buttonLeft_bitmap = BitmapFactory.decodeResource(context, R.mipmap.left);
        buttonRight_bitmap = BitmapFactory.decodeResource(context, R.mipmap.right);


        paint = new Paint();
        paint.setTextSize(60f);


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


    public draw(Canvas canvas) {

        if (universe == null) return ;
        if (canvas == null) return;

        if (getHolder().getSurface().isValid()){
            Canvas canvas = getHolder().lockCanvas();
            canvas.drawARGB(255, 133, 123, 192);

            if (isGameOver){
                isPlaying = false;
                canvas.drawText("Game Over.", screenX/2 - 100, screenY/2, paint);
                canvas.drawText("Wait until we bring you to the main page.", screenX/2 - 500, screenY/2 + 100, paint);
                waitBeforeExiting();
                getHolder().unlockCanvasAndPost(canvas);
                return;
            }

            canvas.drawBitmap(characterA.getCharacter(), characterA.x, characterA.y, paint);
            canvas.drawBitmap(characterB.getCharacter(), characterB.x, characterB.y, paint);

            canvas.drawBitmap(up.getConsole(), up.x, up.y, paint);
            canvas.drawBitmap(down.getConsole(), down.x, down.y, paint);
            canvas.drawBitmap(left.getConsole(), left.x, left.y, paint);
            canvas.drawBitmap(right.getConsole(), right.x, right.y, paint);

            canvas.drawBitmap(cloudA1.getCloud(), cloudA1.x, cloudA1.y, paint);
            canvas.drawBitmap(cloudA2.getCloud(), cloudA2.x, cloudA2.y, paint);
            canvas.drawBitmap(cloudB1.getCloud(), cloudB1.x, cloudB1.y, paint);
            canvas.drawBitmap(cloudB2.getCloud(), cloudB2.x, cloudB2.y, paint);

            canvas.drawBitmap(popsicleA.getPopsicle(), popsicleA.x, popsicleA.y, paint);
            canvas.drawBitmap(popsicleB.getPopsicle(), popsicleB.x, popsicleB.y, paint);

            for (Syrup syrup: syrupsA1){
                canvas.drawBitmap(syrup.syrup, syrup.x, syrup.y, paint);
            }

            for (Syrup syrup: syrupsA2){
                canvas.drawBitmap(syrup.syrup, syrup.x, syrup.y, paint);
            }

            for (Syrup syrup: syrupsB1){
                canvas.drawBitmap(syrup.syrup, syrup.x, syrup.y, paint);
            }

            for (Syrup syrup: syrupsB2){
                canvas.drawBitmap(syrup.syrup, syrup.x, syrup.y, paint);
            }

            getHolder().unlockCanvasAndPost(canvas);

        }
    }


    @Override
    public void run() {
        while (isPlaying){
            update();
            draw();
            sleep();
        }
    }

    @Override
    public void universeChanged(Universe u){
        this.drawSurfaceView();
    }

}
