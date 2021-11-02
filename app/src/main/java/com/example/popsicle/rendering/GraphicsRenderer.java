package com.example.popsicle.rendering;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;
import android.view.SurfaceHolder;

import androidx.annotation.NonNull;

import com.example.popsicle.GameOver;
import com.example.popsicle.GameWon;
import com.example.popsicle.HomePage;
import com.example.popsicle.MainActivity;
import com.example.popsicle.R;
import com.example.popsicle.models.Constants;
import com.example.popsicle.models.Position;
import com.example.popsicle.models.Syrup;
import com.example.popsicle.models.Universe;
import com.example.popsicle.models.WhichPlayer;

/**
 * GraphicsRenderer class method is to render the image of the universe
 * or of the game on screen.It does so by drawing the universe on the
 * Canvas that is hold by the SurfaceHolder inside the SurfaceView.
 * @author Hana
 */
public class GraphicsRenderer implements Universe.Callback, SurfaceHolder.Callback{

    private final static String TAG = "RenderingObjects";

    /**
     * The Universe
     */
    private final Universe universe;

    /**
     * The SurfaceHolder inside the SurfaceView to draw the Canvas
     */
    private SurfaceHolder holder;

    /**
     * The screen sizes of the device emulator
     */
    int screenX, screenY;

    /**
     * The main activity page that creates everything
     */
    MainActivity activity;

    /**
     * The bitmaps or the pictures of all elements in the game
     */
    Bitmap characterA, scaledCharacterA, characterB, scaledCharacterB, popsicleA, scaledPopsicleA, popsicleB, scaledPopsicleB,
            cloudA1, scaledCloudA1, cloudA2, scaledCloudA2, cloudB1, scaledCloudB1, cloudB2, scaledCloudB2,
            up, scaledUp, down, scaledDown, left, scaledLeft, right, scaledRight, syrup, scaledSyrup;

    /**
     * GraphicsRenderer constructor that takes the activity, the universe, and the resources,
     * which is the surfaceView's resources. The constructor creates the Bitmap for the
     * elements in the universe
     * @param activity The MainActivity that creates the game
     * @param u The Universe that holds all elements in the game
     * @param context The resources from the SurfaceView in the mainActivity
     */
    public GraphicsRenderer(MainActivity activity, Universe u, Resources context){
        this.activity = activity;
        this.universe = u;
        this.screenX = u.getConstants().screenX;
        this.screenY = u.getConstants().screenY;

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
     * The surfaceCreated method is to call the drawSurfaceView method
     * to draw the game
     * @param surfaceHolder the surfaceHolder to hold the Canvas
     */
    @Override
    public void surfaceCreated(@NonNull SurfaceHolder surfaceHolder) {
        Log.d(TAG, "start surfaceCreated");
        this.holder = surfaceHolder;
        drawSurfaceView();
    }

    /**
     * The surfaceChanged method is to call the drawSurfaceView method
     * to draw the game. This will be called every time there is a
     * change in the universe.
     * @param surfaceHolder The surfaceHolder inside the surfaceView to hold the Canvas
     *                      where we draw our game
     * @param i Integer
     * @param i1 Integer 1
     * @param i2 Integer 2
     */
    @Override
    public void surfaceChanged(@NonNull SurfaceHolder surfaceHolder, int i, int i1, int i2) {
        Log.d(TAG, "start surfaceChanged");
        drawSurfaceView();
    }

    /**
     * The surfaceDestroyed method is to destroy the surfaceHolder that draws the game
     * inside the Canvas when the game is ended and terminated.
     * @param surfaceHolder the surfaceHolder to hold the Canvas
     */
    @Override
    public void surfaceDestroyed(@NonNull SurfaceHolder surfaceHolder) {
        Log.d(TAG, "start surfaceDestroyed");
        this.holder = null;
    }

    /**
     * The drawSurfaceView method calls the draw method to draw the universe
     * on our canvas.
     */
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
     * Main draw method to draw all of our elements in the game
     * @param canvas The canvas that is hold by surfaceHolder inside surfaceView
     */
    private void draw(Canvas canvas){
//        Log.d(TAG, "Start drawing the universe");

        if (universe == null) return;
        if (canvas == null) return;

        // Draw the canvas
        canvas.drawARGB(255, 133, 123, 192);
        Paint elementsPaint = new Paint();
        elementsPaint.setTextSize(60f);
        elementsPaint.setColor(Color.WHITE);

        if (this.universe.getGameOver()){
//            canvas.drawText("Game Over.", screenX/2 - 100, screenY/2, elementsPaint);
            this.universe.setPlaying(false);
            exiting();
            return;
        }

        if (this.universe.getGameWon()){
//            canvas.drawText("Game Over.", screenX/2 - 100, screenY/2, elementsPaint);
            this.universe.setPlaying(false);
            exitingGameWon();
            return;
        }

        // Drawing the elements
        if (WhichPlayer.amIPlayerA){
            canvas.drawText("Live counter: ", screenX/25, screenY/8, elementsPaint);
            canvas.drawText(Integer.toString(this.universe.getCharacterA().getVisualizedLivesCounter()), screenX/10, screenY/5, elementsPaint);
        }

        if (WhichPlayer.amIPlayerB){
            canvas.drawText("Live counter: ", screenX/25, screenY/8, elementsPaint);
            canvas.drawText(Integer.toString(this.universe.getCharacterB().getVisualizedLivesCounter()), screenX/10, screenY/5, elementsPaint);
        }

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

    /**
     * The exiting method to exit from the SurfaceHolder and go to the HomePage when the game
     * is terminated.
     */
    public void exiting() {
        try {
            Thread.sleep(3000);
            activity.startActivity(new Intent(activity, GameOver.class));
//            activity.startActivity(new Intent(activity, HomePage.class));
            activity.finish();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void exitingGameWon() {
        try {
            Thread.sleep(3000);
            activity.startActivity(new Intent(activity, GameWon.class));
//            activity.startActivity(new Intent(activity, HomePage.class));
            activity.finish();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    /**
     * The universeChanged method is to draw the universe again whenever there
     * are changes in the universe (i.e. position of the characters, etc)
     * @param u The universe that has all our elements
     */
    @Override
    public void universeChanged(Universe u) {
        this.drawSurfaceView();
    }

}
