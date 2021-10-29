package com.example.popsicle.rendering;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.Log;
import android.view.SurfaceHolder;
import android.widget.Button;

import androidx.annotation.NonNull;

import com.example.popsicle.HomePage;
import com.example.popsicle.MainActivity;
import com.example.popsicle.MainController;
import com.example.popsicle.R;
import com.example.popsicle.models.Background;
import com.example.popsicle.models.Candy;
import com.example.popsicle.models.Character;
import com.example.popsicle.models.Clouds;
import com.example.popsicle.models.Console;
import com.example.popsicle.models.Syrup;
import com.example.popsicle.models.Universe;

import java.util.List;

public class GraphicsRenderer implements Universe.Callback, SurfaceHolder.Callback{

    private final static String TAG = "RenderingObjects";
    private final Universe universe;
    private SurfaceHolder holder;
    int screenX, screenY;
    MainController mc;
    Background background;
    MainActivity activity;

    public GraphicsRenderer(MainActivity activity, Universe u, Resources context, int screenX, int screenY, MainController mc){
        this.activity = activity;
        this.universe = u;
        this.mc = mc;
        this.screenX = screenX;
        this.screenY = screenY;
        this.background = background;
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
        canvas.drawARGB(255, 133, 123, 192);
        Paint elementsPaint = new Paint();
        elementsPaint.setTextSize(60f);

        if (mc.getGameOver()){
            mc.setPlaying(false);
            canvas.drawText("Game Over.", screenX/2 - 100, screenY/2, elementsPaint);
            exiting();
            return;
        }

        // Drawing the elements
        canvas.drawBitmap(universe.getCharacterA().getCharacter(), universe.getCharacterA().getPos().getX(), universe.getCharacterA().getPos().getY(), elementsPaint);
        canvas.drawBitmap(universe.getCharacterB().getCharacter(), universe.getCharacterB().getPos().getX(), universe.getCharacterB().getPos().getY(), elementsPaint);
        canvas.drawBitmap(universe.getCloudB1().getClouds(), universe.getCloudB1().getPos().getX(), universe.getCloudB1().getPos().getY(), elementsPaint);
        canvas.drawBitmap(universe.getCloudB2().getClouds(), universe.getCloudB2().getPos().getX(), universe.getCloudB2().getPos().getY(), elementsPaint);
        canvas.drawBitmap(universe.getCloudA1().getClouds(), universe.getCloudA1().getPos().getX(), universe.getCloudA1().getPos().getY(), elementsPaint);
        canvas.drawBitmap(universe.getCloudA2().getClouds(), universe.getCloudA2().getPos().getX(), universe.getCloudA2().getPos().getY(), elementsPaint);
        canvas.drawBitmap(universe.getPopsicleA().getPopsicle(), universe.getPopsicleA().getPos().getX(), universe.getPopsicleA().getPos().getY(), elementsPaint);
        canvas.drawBitmap(universe.getPopsicleB().getPopsicle(), universe.getPopsicleB().getPos().getX(), universe.getPopsicleB().getPos().getY(), elementsPaint);
        canvas.drawBitmap(universe.getPopsicleB().getPopsicle(), universe.getPopsicleB().getPos().getX(), universe.getPopsicleB().getPos().getY(), elementsPaint);
        canvas.drawBitmap(universe.getUp().getConsole(), universe.getUp().getPos().getX(), universe.getUp().getPos().getY(), elementsPaint);
        canvas.drawBitmap(universe.getLeft().getConsole(), universe.getLeft().getPos().getX(), universe.getLeft().getPos().getY(), elementsPaint);
        canvas.drawBitmap(universe.getDown().getConsole(), universe.getDown().getPos().getX(), universe.getDown().getPos().getY(), elementsPaint);
        canvas.drawBitmap(universe.getRight().getConsole(), universe.getRight().getPos().getX(), universe.getRight().getPos().getY(), elementsPaint);

        for (Syrup syrup: universe.getSyrups()){
            canvas.drawBitmap(syrup.getSyrup(), syrup.getPos().getX(), syrup.getPos().getY(), elementsPaint);
        }

    }

    private void exiting() {
        activity.startActivity(new Intent(activity, HomePage.class));
        activity.finish();

    }

    @Override
    public void universeChanged(Universe u) {
        this.drawSurfaceView();
    }

}
