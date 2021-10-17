package com.example.popsicle.rendering;

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

import com.example.popsicle.R;
import com.example.popsicle.models.Candy;
import com.example.popsicle.models.Character;
import com.example.popsicle.models.Clouds;
import com.example.popsicle.models.Console;
import com.example.popsicle.models.Universe;

public class GraphicsRenderer implements Universe.Callback, SurfaceHolder.Callback{

    private final static String TAG = "RenderingObjects";
    private final Universe universe;
    private SurfaceHolder holder;
    private Bitmap characterA_bitmap;
    private Bitmap characterB_bitmap;
    private Bitmap candyA_bitmap;
    private Bitmap candyB_bitmap;
    private Bitmap cloud_bitmap;
    private Bitmap buttonUp_bitmap;
    private Bitmap buttonDown_bitmap;
    private Bitmap buttonLeft_bitmap;
    private Bitmap buttonRight_bitmap;


    public GraphicsRenderer(Universe u, Resources context){
        this.universe = u;
        this.characterA_bitmap = BitmapFactory.decodeResource(context, R.mipmap.char_a);
        this.characterB_bitmap = BitmapFactory.decodeResource(context, R.mipmap.char_b);
        this.candyA_bitmap = BitmapFactory.decodeResource(context, R.mipmap.popsicle_a);
        this.candyB_bitmap = BitmapFactory.decodeResource(context, R.mipmap.popsicle_b);
        this.cloud_bitmap = BitmapFactory.decodeResource(context, R.mipmap.cloud);
        this.buttonUp_bitmap = BitmapFactory.decodeResource(context, R.mipmap.up);
        this.buttonDown_bitmap = BitmapFactory.decodeResource(context, R.mipmap.down);
        this.buttonLeft_bitmap = BitmapFactory.decodeResource(context, R.mipmap.left);
        this.buttonRight_bitmap = BitmapFactory.decodeResource(context, R.mipmap.right);
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

        // Drawing the characters
        Paint elementsPaint = new Paint();

        // Drawing character A
        Character charA = universe.getCharacterA();
        int posA_x1 = (int) (charA.getPos().getX() - 100);
        int posA_y2 = (int) (charA.getPos().getY() + 100);
        int posA_x2 = (int) (charA.getPos().getX() + 100);
        int posA_y1 = (int) (charA.getPos().getY() - 100);

        Rect boundsA = new Rect(posA_x1, posA_y1, posA_x2, posA_y2);
        Bitmap scaledA_bmp = Bitmap.createScaledBitmap(this.characterA_bitmap, boundsA.width(), boundsA.height(), true);
        canvas.drawBitmap(scaledA_bmp, boundsA.left, boundsA.bottom, elementsPaint);

        // Drawing character B
        Character charB = universe.getCharacterB();
        int posB_x1 = (int) (charB.getPos().getX() - 100);
        int posB_y2 = (int) (charB.getPos().getY() + 100);
        int posB_x2 = (int) (charB.getPos().getX() + 100);
        int posB_y1 = (int) (charB.getPos().getY() - 100);

        Rect boundsB = new Rect(posB_x1, posB_y1, posB_x2, posB_y2);
        Bitmap scaledB_bmp = Bitmap.createScaledBitmap(this.characterB_bitmap, boundsB.width(), boundsB.height(), true);
        canvas.drawBitmap(scaledB_bmp, boundsB.left, boundsB.bottom, elementsPaint);

        // Drawing candy A
        Candy candyA = universe.getCandyA();
        int poscan_x1 = (int) (candyA.getPos().getX() - 100);
        int poscan_y2 = (int) (candyA.getPos().getY() + 100);
        int poscan_x2 = (int) (candyA.getPos().getX() + 100);
        int poscan_y1 = (int) (candyA.getPos().getY() - 100);

        Rect boundscanA = new Rect(poscan_x1, poscan_y1, poscan_x2, poscan_y2);
        Bitmap scaledcanA_bmp = Bitmap.createScaledBitmap(this.candyA_bitmap, boundscanA.width(), boundscanA.height(), true);
        canvas.drawBitmap(scaledcanA_bmp, boundscanA.left, boundscanA.bottom, elementsPaint);

        // Drawing candy B
        Candy candyB = universe.getCandyB();
        int poscanB_x1 = (int) (candyB.getPos().getX() - 100);
        int poscanB_y2 = (int) (candyB.getPos().getY() + 100);
        int poscanB_x2 = (int) (candyB.getPos().getX() + 100);
        int poscanB_y1 = (int) (candyB.getPos().getY() - 100);

        Rect boundscanB = new Rect(poscanB_x1, poscanB_y1, poscanB_x2, poscanB_y2);
        Bitmap scaledcanB_bmp = Bitmap.createScaledBitmap(this.candyB_bitmap, boundscanB.width(), boundscanB.height(), true);
        canvas.drawBitmap(scaledcanB_bmp, boundscanB.left, boundscanB.bottom, elementsPaint);

        // Drawing cloud A1
        Clouds cloudA1 = universe.getCloudsA1();
        int cloudA1_x1 = (int) (cloudA1.getPos().getX() - 100);
        int cloudA1_y2 = (int) (cloudA1.getPos().getY() + 100);
        int cloudA1_x2 = (int) (cloudA1.getPos().getX() + 100);
        int cloudA1_y1 = (int) (cloudA1.getPos().getY() - 100);

        Rect boundsCloudA1 = new Rect(cloudA1_x1, cloudA1_y1, cloudA1_x2, cloudA1_y2);
        Bitmap ScaledBMPCloudA1 = Bitmap.createScaledBitmap(this.cloud_bitmap, boundsCloudA1.width(), boundsCloudA1.height(), true);
        canvas.drawBitmap(ScaledBMPCloudA1, boundsCloudA1.left, boundsCloudA1.bottom, elementsPaint);

        // Drawing cloud A2
        Clouds cloudA2 = universe.getCloudsA2();
        int cloudA2_x1 = (int) (cloudA2.getPos().getX() - 100);
        int cloudA2_y2 = (int) (cloudA2.getPos().getY() + 100);
        int cloudA2_x2 = (int) (cloudA2.getPos().getX() + 100);
        int cloudA2_y1 = (int) (cloudA2.getPos().getY() - 100);

        Rect boundsCloudA2 = new Rect(cloudA2_x1, cloudA2_y1, cloudA2_x2, cloudA2_y2);
        Bitmap ScaledBMPCloudA2 = Bitmap.createScaledBitmap(this.cloud_bitmap, boundsCloudA2.width(), boundsCloudA2.height(), true);
        canvas.drawBitmap(ScaledBMPCloudA2, boundsCloudA2.left, boundsCloudA2.bottom, elementsPaint);

        // Drawing cloud B1
        Clouds cloudB1 = universe.getCloudsB1();
        int cloudB1_x1 = (int) (cloudB1.getPos().getX() - 100);
        int cloudB1_y2 = (int) (cloudB1.getPos().getY() + 100);
        int cloudB1_x2 = (int) (cloudB1.getPos().getX() + 100);
        int cloudB1_y1 = (int) (cloudB1.getPos().getY() - 100);

        Rect boundsCloudB1 = new Rect(cloudB1_x1, cloudB1_y1, cloudB1_x2, cloudB1_y2);
        Bitmap ScaledBMPCloudB1 = Bitmap.createScaledBitmap(this.cloud_bitmap, boundsCloudB1.width(), boundsCloudB1.height(), true);
        canvas.drawBitmap(ScaledBMPCloudB1, boundsCloudB1.left, boundsCloudB1.bottom, elementsPaint);

        // Drawing cloud B2
        Clouds cloudB2 = universe.getCloudsB2();
        int cloudB2_x1 = (int) (cloudB2.getPos().getX() - 100);
        int cloudB2_y2 = (int) (cloudB2.getPos().getY() + 100);
        int cloudB2_x2 = (int) (cloudB2.getPos().getX() + 100);
        int cloudB2_y1 = (int) (cloudB2.getPos().getY() - 100);

        Rect boundsCloudB2 = new Rect(cloudB2_x1, cloudB2_y1, cloudB2_x2, cloudB2_y2);
        Bitmap ScaledBMPCloudB2 = Bitmap.createScaledBitmap(this.cloud_bitmap, boundsCloudB2.width(), boundsCloudB2.height(), true);
        canvas.drawBitmap(ScaledBMPCloudB2, boundsCloudB2.left, boundsCloudB2.bottom, elementsPaint);


        // Drawing Console Button Up
        Console up = universe.getUpButton();
        int up_x1 = (int) (up.getPos().getX() - 70);
        int up_y2 = (int) (up.getPos().getY() + 70);
        int up_x2 = (int) (up.getPos().getX() + 70);
        int up_y1 = (int) (up.getPos().getY() - 70);

        Rect boundsUp = new Rect(up_x1, up_y1, up_x2, up_y2);
        Bitmap ScaledBMPCUp = Bitmap.createScaledBitmap(this.buttonUp_bitmap, boundsUp.width(), boundsUp.height(), true);
        canvas.drawBitmap(ScaledBMPCUp, boundsUp.left, boundsUp.bottom, elementsPaint);

        // Drawing Console Button Down
        Console down = universe.getDownButton();
        int down_x1 = (int) (down.getPos().getX() - 70);
        int down_y2 = (int) (down.getPos().getY() + 70);
        int down_x2 = (int) (down.getPos().getX() + 70);
        int down_y1 = (int) (down.getPos().getY() - 70);

        Rect boundsDown = new Rect(down_x1, down_y1, down_x2, down_y2);
        Bitmap ScaledBMPCDown = Bitmap.createScaledBitmap(this.buttonDown_bitmap, boundsDown.width(), boundsDown.height(), true);
        canvas.drawBitmap(ScaledBMPCDown, boundsDown.left, boundsDown.bottom, elementsPaint);

        // Drawing Console Button Left
        Console left = universe.getLeftButton();
        int left_x1 = (int) (left.getPos().getX() - 70);
        int left_y2 = (int) (left.getPos().getY() + 70);
        int left_x2 = (int) (left.getPos().getX() + 70);
        int left_y1 = (int) (left.getPos().getY() - 70);

        Rect boundsLeft = new Rect(left_x1, left_y1, left_x2, left_y2);
        Bitmap ScaledBMPCLeft = Bitmap.createScaledBitmap(this.buttonLeft_bitmap, boundsLeft.width(), boundsLeft.height(), true);
        canvas.drawBitmap(ScaledBMPCLeft, boundsLeft.left, boundsLeft.bottom, elementsPaint);

        // Drawing Console Button Right
        Console right = universe.getRightButton();
        int right_x1 = (int) (right.getPos().getX() - 70);
        int right_y2 = (int) (right.getPos().getY() + 70);
        int right_x2 = (int) (right.getPos().getX() + 70);
        int right_y1 = (int) (right.getPos().getY() - 70);

        Rect boundsRight = new Rect(right_x1, right_y1, right_x2, right_y2);
        Bitmap ScaledBMPCRight = Bitmap.createScaledBitmap(this.buttonRight_bitmap, boundsRight.width(), boundsRight.height(), true);
        canvas.drawBitmap(ScaledBMPCRight, boundsRight.left, boundsRight.bottom, elementsPaint);






    }

    @Override
    public void universeChanged(Universe u) {
        this.drawSurfaceView();
    }

}
