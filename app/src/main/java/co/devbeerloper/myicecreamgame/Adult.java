package co.devbeerloper.myicecreamgame;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.util.Random;

public class Adult {

    public static final float INIT_X =2000;
    public static final float INIT_Y =10;
    public static  int SPRITE_SIZE_WIDTH =150;
    public static  int SPRITE_SIZE_HEIGTH=250;
    public static int minSpeed = 5;
    public static int maxSpeed = 10;
    public static final float GRAVITY_FORCE=10;

    private float maxY;
    private float maxX;

    private float speed = 0;
    private float positionX;
    private float positionY;
    private Bitmap spriteCloud;
    private boolean borrar;


    public Adult (Context context, float screenWidth, float screenHeigth){
        borrar = false;
        Random randomGenerator = new Random();
        speed = getRandomNumberInRange(minSpeed, maxSpeed);
        positionX = this.INIT_X;
        positionY = randomGenerator.nextInt(500)+1;

        Bitmap originalBitmap= BitmapFactory.decodeResource(context.getResources(), R.drawable.adult);
        spriteCloud  = Bitmap.createScaledBitmap(originalBitmap, SPRITE_SIZE_WIDTH, SPRITE_SIZE_HEIGTH, false);

        this.maxX = screenWidth - (spriteCloud.getWidth()/2);
        this.maxY = screenHeigth - spriteCloud.getHeight();
    }

    private static int getRandomNumberInRange(int min, int max) {

        if (min >= max) {
            throw new IllegalArgumentException("max must be greater than min");
        }

        Random r = new Random();
        return r.nextInt((max - min) + 1) + min;
    }

    public static float getInitX() {
        return INIT_X;
    }

    public static float getInitY() {
        return INIT_Y;
    }

    public float getSpeed() {
        return speed;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

    public float getPositionX() {
        return positionX;
    }

    public void setPositionX(float positionX) {
        this.positionX = positionX;
    }

    public float getPositionY() {
        return positionY;
    }

    public void setPositionY(float positionY) {
        this.positionY = positionY;
    }

    public Bitmap getSpriteCloud() {
        return spriteCloud;
    }

    public void setSpriteCloud(Bitmap spriteCloud) {
        this.spriteCloud = spriteCloud;
    }

    public boolean getBorrar(){
        return this.borrar;
    }
    /**
     * Control the position and behaviour of the cloud
     */
    public void updateInfo () {
        this.positionX -=speed;
        System.out.println(this.getPositionX());
        if(this.positionX <= 0) {
            this.borrar = true;
        }
    }
}
