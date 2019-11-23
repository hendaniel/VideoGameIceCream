package co.devbeerloper.myicecreamgame;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.util.ArrayList;
import java.util.Random;

public class GameSurfaceView extends SurfaceView implements Runnable {

    private boolean isPlaying;
    private IceCreamCar icecreamCar;
    private Cloud cloud;
    private Paint paint;
    private Canvas canvas;
    private SurfaceHolder holder;
    private Thread gameplayThread = null;
    private ArrayList<Kid> kids;
    private ArrayList<Adult> adults;
    private ArrayList<PowerBIG> powerbig;
    private ArrayList<PowerSmall> powersmall;
    private int randomAdult;
    public Context contextGlobal;
    public float screenWithGlobal;
    public int contador;
    public int aumentarDificultad;
    public int disminuirDificultad;
    public float screenHeightGlobal;

    /**
     * Contructor
     *
     * @param context
     */
    public GameSurfaceView(Context context, float screenWith, float screenHeight) {
        super(context);
        randomAdult = 2;
        aumentarDificultad = 5;
        disminuirDificultad = 5;
        contador = 0;
        kids = new ArrayList<>();
        adults = new ArrayList<>();
        powerbig = new ArrayList<>();
        powersmall = new ArrayList<>();
        contextGlobal = context;
        screenWithGlobal = screenWith;
        screenHeightGlobal = screenHeight;
        icecreamCar = new IceCreamCar(context, screenWith, screenHeight);
        cloud = new Cloud(context, screenWith, screenHeight);
        paint = new Paint();
        holder = getHolder();
        isPlaying = true;
    }

    /**
     * Method implemented from runnable interface
     */
    @Override
    public void run() {
        while (isPlaying) {
            randomKid();
            randomAdult();
            randomPowerBig();
            randomPowerSmall();
            paintFrame();
            updateInfo();
            verifyCollition();

        }

    }
    private void verifyCollition(){
        for(int i = 0; i < kids.size() ; i++){
            if(icecreamCar.getPositionX() + IceCreamCar.SPRITE_SIZE_WIDTH >= kids.get(i).getPositionX() && Math.abs(icecreamCar.getPositionY() - kids.get(i).getPositionY()) < Kid.SPRITE_SIZE_HEIGTH){
                contador++;
                kids.remove(kids.get(i));
                if(contador == aumentarDificultad){
                    aumentarDificultad =+ 5;
                    randomAdult +=5;
                    Adult.maxSpeed += 2;
                    Adult.minSpeed += 2;
                }
            }
        }
        for(int i = 0; i < adults.size() ; i++){
            if(icecreamCar.getPositionX() + IceCreamCar.SPRITE_SIZE_WIDTH >= adults.get(i).getPositionX() && Math.abs(icecreamCar.getPositionY() - adults.get(i).getPositionY()) < Adult.SPRITE_SIZE_HEIGTH){
                contador--;
                adults.remove(adults.get(i));
                if(contador == disminuirDificultad){
                    if(Adult.minSpeed > 2) {
                        disminuirDificultad -= 5;
                        randomAdult -=5;
                        Adult.maxSpeed -= 2;
                        Adult.minSpeed -= 2;
                    }
                }
            }
        }
        for(int i = 0; i < powerbig.size() ; i++){
            if(icecreamCar.getPositionX() + IceCreamCar.SPRITE_SIZE_WIDTH >= powerbig.get(i).getPositionX() && Math.abs(icecreamCar.getPositionY() - powerbig.get(i).getPositionY()) < PowerBIG.SPRITE_SIZE_HEIGTH){
                powerbig.remove(powerbig.get(i));
                if(IceCreamCar.SPRITE_SIZE_WIDTH <= 700)
                    IceCreamCar.SPRITE_SIZE_WIDTH += 50;
                if(IceCreamCar.SPRITE_SIZE_HEIGTH <= 600)
                    IceCreamCar.SPRITE_SIZE_HEIGTH += 50;
            }
        }
        for(int i = 0; i < powersmall.size() ; i++){
            if(icecreamCar.getPositionX() + IceCreamCar.SPRITE_SIZE_WIDTH >= powersmall.get(i).getPositionX() && Math.abs(icecreamCar.getPositionY() - powersmall.get(i).getPositionY()) < PowerSmall.SPRITE_SIZE_HEIGTH){
                powersmall.remove(powersmall.get(i));
                if(IceCreamCar.SPRITE_SIZE_WIDTH >= 300)
                IceCreamCar.SPRITE_SIZE_WIDTH -= 50;
                if(IceCreamCar.SPRITE_SIZE_HEIGTH >= 200)
                IceCreamCar.SPRITE_SIZE_HEIGTH -= 50;
            }
        }
    }

    private void randomAdult() {
        Random random = new Random();
        int ran = random.nextInt(1000);
        if (ran <= randomAdult) {
            Adult adult = new Adult(contextGlobal, screenWithGlobal, screenHeightGlobal);
            adults.add(adult);
        }
    }
    private void randomPowerBig() {
        Random random = new Random();
        int ran = random.nextInt(1000);
        if (ran <= 1) {
            PowerBIG power = new PowerBIG(contextGlobal, screenWithGlobal, screenHeightGlobal);
            powerbig.add(power);
        }
    }
    private void randomPowerSmall() {
        Random random = new Random();
        int ran = random.nextInt(1000);
        if (ran <= 1) {
            PowerSmall power = new PowerSmall(contextGlobal, screenWithGlobal, screenHeightGlobal);
            powersmall.add(power);
        }
    }

    private void randomKid() {
        Random random = new Random();
        int ran = random.nextInt(1000);
        if (ran <= 5) {
            Kid kid = new Kid(contextGlobal, screenWithGlobal, screenHeightGlobal);
            kids.add(kid);
        }
    }

    private void updateInfo() {
        icecreamCar.updateInfo();
        cloud.updateInfo();
        for(int i = 0; i < kids.size() ; i++){
            kids.get(i).updateInfo();
            if(kids.get(i).getBorrar()) {
                kids.remove(kids.get(i));
                i--;
            }
        }
        for(int i = 0; i < adults.size() ; i++){
            adults.get(i).updateInfo();
            if(adults.get(i).getBorrar()) {
                adults.remove(adults.get(i));
                i--;
            }
        }
        for(int i = 0; i < powersmall.size() ; i++){
            powersmall.get(i).updateInfo();
            if(powersmall.get(i).getBorrar()) {
                powersmall.remove(powersmall.get(i));
                i--;
            }
        }
        for(int i = 0; i < powerbig.size() ; i++){
            powerbig.get(i).updateInfo();
            if(powerbig.get(i).getBorrar()) {
                powerbig.remove(powerbig.get(i));
                i--;
            }
        }
    }

    private void paintFrame() {
        if (holder.getSurface().isValid()) {
            canvas = holder.lockCanvas();
            canvas.drawColor(Color.BLACK);
            canvas.drawBitmap(icecreamCar.getSpriteIcecreamCar(), icecreamCar.getPositionX(), icecreamCar.getPositionY(), paint);
            for (Kid kid : kids) {
                canvas.drawBitmap(kid.getSpriteCloud(), kid.getPositionX(), kid.getPositionY(), paint);
            }
            for (Adult adult : adults) {
                canvas.drawBitmap(adult.getSpriteCloud(), adult.getPositionX(), adult.getPositionY(), paint);
            }
            for (PowerSmall power : powersmall) {
                canvas.drawBitmap(power.getSpriteCloud(), power.getPositionX(), power.getPositionY(), paint);
            }
            for (PowerBIG power : powerbig) {
                canvas.drawBitmap(power.getSpriteCloud(), power.getPositionX(), power.getPositionY(), paint);
            }
            Paint paintText = new Paint();
            paintText.setColor(Color.WHITE);
            paintText.setTextSize(100);
            paintText.setTextAlign(Paint.Align.CENTER);
            canvas.drawText(contador + "",1200, 100, paintText);
            holder.unlockCanvasAndPost(canvas);
        }

    }
    public void pause() {
        isPlaying = false;
        try {
            gameplayThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void resume() {

        isPlaying = true;
        gameplayThread = new Thread(this);
        gameplayThread.start();
    }

    /**
     * Detect the action of the touch event
     *
     * @param motionEvent
     * @return
     */
    @Override
    public boolean onTouchEvent(MotionEvent motionEvent) {
        switch (motionEvent.getAction() & MotionEvent.ACTION_MASK) {
            case MotionEvent.ACTION_UP:
                System.out.println("TOUCH UP - STOP JUMPING");
                icecreamCar.setJumping(false);
                break;
            case MotionEvent.ACTION_DOWN:
                System.out.println("TOUCH DOWN - JUMP");
                icecreamCar.setJumping(true);
                break;
        }
        return true;
    }

}
