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
import com.example.popsicle.models.Constants;
import com.example.popsicle.models.Syrup;
import com.example.popsicle.models.Universe;

import java.util.List;

public class GraphicsRenderer implements Universe.Callback, SurfaceHolder.Callback{

    private final static String TAG = "RenderingObjects";
    private final Universe universe;
    private SurfaceHolder holder;
    int screenX, screenY;
    Background background;
    MainActivity activity;
    Bitmap characterA, scaledCharacterA, characterB, scaledCharacterB, popsicleA, scaledPopsicleA, popsicleB, scaledPopsicleB,
            cloudA1, scaledCloudA1, cloudA2, scaledCloudA2, cloudB1, scaledCloudB1, cloudB2, scaledCloudB2,
            up, scaledUp, down, scaledDown, left, scaledLeft, right, scaledRight, syrup, scaledSyrup;

    public GraphicsRenderer(MainActivity activity, Universe u, Resources context){
        this.activity = activity;
        this.universe = u;
        this.screenX = Constants.screenX;
        this.screenY = Constants.screenY;
        this.background = background;

        this.characterA = BitmapFactory.decodeResource(context, R.mipmap.char_a);
        this.scaledCharacterA = Bitmap.createScaledBitmap(this.characterA, Constants.charWidth, Constants.charHeight, true);
        this.characterB = BitmapFactory.decodeResource(context, R.mipmap.char_b);
        this.scaledCharacterB = Bitmap.createScaledBitmap(this.characterB, Constants.charWidth, Constants.charHeight, true);

        this.popsicleA = BitmapFactory.decodeResource(context, R.mipmap.popsicle_a);
        this.scaledPopsicleA = Bitmap.createScaledBitmap(this.popsicleA, Constants.popsicleWidth, Constants.popsicleHeight, true);
        this.popsicleB = BitmapFactory.decodeResource(context, R.mipmap.popsicle_b);
        this.scaledPopsicleB = Bitmap.createScaledBitmap(this.popsicleB, Constants.popsicleWidth, Constants.popsicleHeight, true);

        this.cloudA1 = BitmapFactory.decodeResource(context, R.mipmap.cloud);
        this.scaledCloudA1 = Bitmap.createScaledBitmap(this.cloudA1, Constants.cloudWidth, Constants.cloudHeight, true);

        this.cloudA2 = BitmapFactory.decodeResource(context, R.mipmap.cloud);
        this.scaledCloudA2 = Bitmap.createScaledBitmap(this.cloudA2, Constants.cloudWidth, Constants.cloudHeight, true);

        this.cloudB1 = BitmapFactory.decodeResource(context, R.mipmap.cloud);
        this.scaledCloudB1 = Bitmap.createScaledBitmap(this.cloudB1, Constants.cloudWidth, Constants.cloudHeight, true);

        this.cloudB2 = BitmapFactory.decodeResource(context, R.mipmap.cloud);
        this.scaledCloudB2 = Bitmap.createScaledBitmap(this.cloudB2, Constants.cloudWidth, Constants.cloudHeight, true);

        this.up = BitmapFactory.decodeResource(context, R.mipmap.up);
        this.scaledUp = Bitmap.createScaledBitmap(this.up, Constants.consoleWidth, Constants.consoleHeight, true);

        this.down = BitmapFactory.decodeResource(context, R.mipmap.down);
        this.scaledDown = Bitmap.createScaledBitmap(this.down, Constants.consoleWidth, Constants.consoleHeight, true);

        this.left = BitmapFactory.decodeResource(context, R.mipmap.left);
        this.scaledLeft = Bitmap.createScaledBitmap(this.left, Constants.consoleWidth, Constants.consoleHeight, true);

        this.right = BitmapFactory.decodeResource(context, R.mipmap.right);
        this.scaledRight = Bitmap.createScaledBitmap(this.right, Constants.consoleWidth, Constants.consoleHeight, true);

        this.syrup = BitmapFactory.decodeResource(context,R.mipmap.syrup);
        this.scaledSyrup = Bitmap.createScaledBitmap(this.syrup, Constants.syrupWidth, Constants.syrupHeight, true);

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

        if (this.universe.getGameOver()){
            this.universe.setPlaying(false);
            canvas.drawText("Game Over.", screenX/2 - 100, screenY/2, elementsPaint);
            exiting();
            return;
        }

        // Drawing the elements
        canvas.drawBitmap(scaledCharacterA, universe.getCharacterA().getPos().getX(), universe.getCharacterA().getPos().getY(), elementsPaint);
        canvas.drawBitmap(scaledCharacterB, universe.getCharacterB().getPos().getX(), universe.getCharacterB().getPos().getY(), elementsPaint);

        canvas.drawBitmap(scaledCloudB1, universe.getCloudB1().getPos().getX(), universe.getCloudB1().getPos().getY(), elementsPaint);
        canvas.drawBitmap(scaledCloudB2, universe.getCloudB2().getPos().getX(), universe.getCloudB2().getPos().getY(), elementsPaint);
        canvas.drawBitmap(scaledCloudA1, universe.getCloudA1().getPos().getX(), universe.getCloudA1().getPos().getY(), elementsPaint);
        canvas.drawBitmap(scaledCloudA2, universe.getCloudA2().getPos().getX(), universe.getCloudA2().getPos().getY(), elementsPaint);

        canvas.drawBitmap(scaledPopsicleA, universe.getPopsicleA().getPos().getX(), universe.getPopsicleA().getPos().getY(), elementsPaint);
        canvas.drawBitmap(scaledPopsicleB, universe.getPopsicleB().getPos().getX(), universe.getPopsicleB().getPos().getY(), elementsPaint);

        canvas.drawBitmap(scaledUp, universe.getUp().getPos().getX(), universe.getUp().getPos().getY(), elementsPaint);
        canvas.drawBitmap(scaledDown, universe.getDown().getPos().getX(), universe.getDown().getPos().getY(), elementsPaint);
        canvas.drawBitmap(scaledLeft, universe.getLeft().getPos().getX(), universe.getLeft().getPos().getY(), elementsPaint);
        canvas.drawBitmap(scaledRight, universe.getRight().getPos().getX(), universe.getRight().getPos().getY(), elementsPaint);


        for (Syrup syrup: universe.getSyrups()){
            canvas.drawBitmap(scaledSyrup, syrup.getPos().getX(), syrup.getPos().getY(), elementsPaint);
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
