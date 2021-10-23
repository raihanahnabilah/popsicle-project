package com.example.popsicleproject;

import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.MotionEvent;
import android.view.SurfaceView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GraphicsRenderer extends SurfaceView implements Runnable {

    private final int screenX, screenY;
    private Thread thread;
    private boolean isPlaying, isGameOver = false;
    private Paint paint;
    private Character characterA, characterB;
    private Console up, down, left, right;
    private Cloud cloudA1, cloudA2, cloudB1, cloudB2;
    private Popsicle popsicleA, popsicleB;
    private MainActivity activity;
    private List<Syrup> syrupsA1, syrupsA2, syrupsB1, syrupsB2;
//     Added values of constants for sizes, fixed movements, etc.
    private Integer charMovementPixels = 20;
    private Integer syrupMovementPixelsA1X = 20;
    private float syrupMovementPixelsA1Y = 7f;
    private Integer syrupMovementPixelsA2X = 20;
    private float syrupMovementPixelsA2Y = 5f;

    private Integer syrupMovementPixelsB1X = 20;
    private float syrupMovementPixelsB1Y = 5f;
    private Integer syrupMovementPixelsB2X = 20;
    private float syrupMovementPixelsB2Y = 7f;


//    final float density = getContext().getResources().getDisplayMetrics().density;


//    public GraphicsRenderer(Context context, int screenX, int screenY){
    public GraphicsRenderer(MainActivity activity, int screenX, int screenY){
        super(activity);

        this.activity = activity;

        this.screenX = screenX;
        this.screenY = screenY;

        characterA = new Character(screenX, screenY, getResources(), R.mipmap.char_a, "a");
        characterB = new Character(screenX, screenY, getResources(), R.mipmap.char_b, "b");

        up = new Console(screenX, screenY, getResources(), R.mipmap.up, "up");
        down = new Console(screenX, screenY, getResources(), R.mipmap.down, "down");
        left = new Console(screenX, screenY, getResources(), R.mipmap.left, "left");
        right = new Console(screenX, screenY, getResources(), R.mipmap.right, "right");

        cloudA1 = new Cloud(this,screenX, screenY, getResources(), R.mipmap.cloud, "a1");
        cloudA2 = new Cloud(this,screenX, screenY, getResources(), R.mipmap.cloud, "a2");
        cloudB1= new Cloud(this,screenX, screenY, getResources(), R.mipmap.cloud, "b1");
        cloudB2 = new Cloud(this,screenX, screenY, getResources(), R.mipmap.cloud, "b2");

        popsicleA = new Popsicle(screenX, screenY, getResources(), R.mipmap.popsicle_a, "a");
        popsicleB = new Popsicle(screenX, screenY, getResources(), R.mipmap.popsicle_b, "b");

        syrupsA1 = new ArrayList<>();
        syrupsA2 = new ArrayList<>();
        syrupsB1 = new ArrayList<>();
        syrupsB2 = new ArrayList<>();

        paint = new Paint();
        paint.setTextSize(60f);

    }

    @Override
    public void run() {
        while (isPlaying){
            update();
            draw();
            sleep();
        }

    }


    private void update() {

        // This is only character A
        if (characterA.isMovingUp){
            characterA.y -= charMovementPixels;
        }
        if (characterA.isMovingDown){
            characterA.y += charMovementPixels;
        }
        if (characterA.isMovingRight){
            characterA.x += charMovementPixels;
        }
        if (characterA.isMovingLeft){
            characterA.x -= charMovementPixels;
        }

        // This is only character B
        if (characterB.isMovingUp){
            characterB.y -= charMovementPixels;
        }
        if (characterB.isMovingDown){
            characterB.y += charMovementPixels;
        }
        if (characterB.isMovingRight){
            characterB.x += charMovementPixels;
        }
        if (characterB.isMovingLeft){
            characterB.x -= charMovementPixels;
        }

        if (Rect.intersects(characterA.getCollissionShape(), popsicleB.getCollissionShape()) ||
                Rect.intersects(characterB.getCollissionShape(), popsicleA.getCollissionShape())){
            isGameOver = true;
            return;
        }

        List<Syrup> trashA1 = new ArrayList<>();

        for (Syrup syrup: syrupsA1){
            if (syrup.x > screenX){
                trashA1.add(syrup);
            }
            syrup.x += syrupMovementPixelsA1X;
            syrup.y += syrupMovementPixelsA1Y;

            if (Rect.intersects(characterA.getCollissionShape(), syrup.getCollissionShape()) ||
            Rect.intersects(characterB.getCollissionShape(), syrup.getCollissionShape())){
                isGameOver = true;
                return;
            }
        }

        for (Syrup syrup: trashA1){
            syrupsA1.remove(syrup);
        }

        List<Syrup> trashA2 = new ArrayList<>();

        for (Syrup syrup: syrupsA2){
            if (syrup.x > screenX){
                trashA2.add(syrup);
            }
            syrup.x += syrupMovementPixelsA2X;
            syrup.y -= syrupMovementPixelsA2Y;

            if (Rect.intersects(characterA.getCollissionShape(), syrup.getCollissionShape()) ||
                    Rect.intersects(characterB.getCollissionShape(), syrup.getCollissionShape())){
                isGameOver = true;
                return;
            }
        }

        for (Syrup syrup: trashA2){
            syrupsA2.remove(syrup);
        }

        List<Syrup> trashB1 = new ArrayList<>();

        for (Syrup syrup: syrupsB1){
            if (syrup.x > screenX){
                trashB1.add(syrup);
            }
            syrup.x -= syrupMovementPixelsB1X;
            syrup.y += syrupMovementPixelsB1Y;

            if (Rect.intersects(characterA.getCollissionShape(), syrup.getCollissionShape()) ||
                    Rect.intersects(characterB.getCollissionShape(), syrup.getCollissionShape())){
                isGameOver = true;
                return;
            }
        }

        for (Syrup syrup: trashB1){
            syrupsB1.remove(syrup);
        }


        List<Syrup> trashB2 = new ArrayList<>();

        for (Syrup syrup: syrupsB2){
            if (syrup.x > screenX){
                trashB2.add(syrup);
            }
            syrup.x -= syrupMovementPixelsB2X;
            syrup.y -= syrupMovementPixelsB2Y;

            if (Rect.intersects(characterA.getCollissionShape(), syrup.getCollissionShape()) ||
                    Rect.intersects(characterB.getCollissionShape(), syrup.getCollissionShape())){
                isGameOver = true;
                return;
            }
        }

        for (Syrup syrup: trashB2){
            syrupsB2.remove(syrup);
        }



    }

    private void draw() {

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

    private void waitBeforeExiting() {
        try {
            Thread.sleep(3000);
            activity.startActivity(new Intent(activity, StartGamePage.class));
            activity.finish();

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void sleep() {
        try {
            Thread.sleep(17);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void resume () {
        isPlaying = true;
        thread = new Thread(this);
        thread.start();
    }

    public void pause () {
        try {
            isPlaying = false;
            thread.join();
        } catch (InterruptedException e){
            e.printStackTrace();
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch(event.getAction()){
            case MotionEvent.ACTION_DOWN:
//                System.out.println(event.getX());
//                System.out.println(up.x);
//                System.out.println(event.getY());
//                System.out.println(up.y);
                // This is to move character A
//                System.out.println(up.width);
                if ((event.getX() > up.x + up.width/4) && (event.getX() < (up.x + up.width - up.width/4)) &&
                        (event.getY() > up.y + up.height/4) && (event.getY() < up.y + up.height - up.height/4)){
                    characterA.isMovingUp = true;
                }
                if ((event.getX() > down.x + down.width/4) && (event.getX() < (down.x + down.width - down.width/4)) &&
                        (event.getY() > down.y + down.height/4) && (event.getY() < down.y + down.height - down.height/4)){
                    characterA.isMovingDown = true;
                }
                if ((event.getX() > right.x + right.width/4) && (event.getX() < (right.x + right.width - right.width/4)) &&
                        (event.getY() > right.y + right.height/4) && (event.getY() < right.y + right.height - right.height/4)){
                    characterA.isMovingRight = true;
                }
                if ((event.getX() > left.x + left.width/4) && (event.getX() < (left.x + left.width - left.width/4)) &&
                        (event.getY() > left.y + left.height/4) && (event.getY() < left.y + left.height - left.height/4)){
                    characterA.isMovingLeft = true;
                }

                break;
            case MotionEvent.ACTION_UP:
                characterA.isMovingUp = false;
                characterA.isMovingDown = false;
                characterA.isMovingRight = false;
                characterA.isMovingLeft = false;
                characterB.isMovingUp = false;
                characterB.isMovingDown = false;
                characterB.isMovingRight = false;
                characterB.isMovingLeft = false;


                // To activate the clouds
                if ((event.getX() > cloudA1.x) && (event.getX() < cloudA1.x + cloudA1.width) &&
                        (event.getY() > cloudA1.y + cloudA1.height/4) && (event.getY() < cloudA1.y  + cloudA1.height - cloudA1.height/4)){
                    cloudA1.toShoot++;
                }
                if ((event.getX() > cloudA2.x) && (event.getX() < cloudA2.x + cloudA2.width) &&
                        (event.getY() > cloudA2.y + cloudA2.height/4) && (event.getY() < cloudA2.y  + cloudA2.height - cloudA2.height/4)){
                    cloudA2.toShoot++;
                }
                if ((event.getX() > cloudB1.x) && (event.getX() < cloudB1.x + cloudB1.width) &&
                        (event.getY() > cloudB1.y + cloudB1.height/4) && (event.getY() < cloudB1.y  + cloudB1.height - cloudB1.height/4)){
                    cloudB1.toShoot++;
                }
                if ((event.getX() > cloudB2.x) && (event.getX() < cloudB2.x + cloudB2.width) &&
                        (event.getY() > cloudB2.y + cloudB2.height/4) && (event.getY() < cloudB2.y  + cloudB2.height - cloudB2.height/4)){
                    cloudB2.toShoot++;
                }
                break;

        }

        return true;
    }

    public void newSyrup(String direction){
        Syrup syrup = new Syrup(getResources());
//        gives random integer from 0 to 3 inclusive
        Random ran = new Random();
        int n = ran.nextInt(4) ;
        System.out.println(n);
        if (direction.equals("a1")){
//            after fixing cloud position according to width/height use cloudA1.x and with y
//            it will initialize in leftup corener of cloud if we use that
            syrup.x = cloudA1.x;
            syrup.y = cloudA1.y + cloudA1.height/2;
            syrupsA1.add(syrup);
        }
        else if (direction.equals("a2")){
            syrup.x = cloudA2.x;
            syrup.y = cloudA2.y + cloudA2.height/2;
            syrupsA2.add(syrup);
        }
        else if (direction.equals("b1")){
            syrup.x = cloudB1.x;
            syrup.y = cloudB1.y + cloudB1.height/2;
            syrupsB1.add(syrup);
        }
        else if (direction.equals("b2")){
            syrup.x = cloudB2.x;
            syrup.y = cloudB2.y + cloudB2.height/2;
            syrupsB2.add(syrup);
        }
    }
}
