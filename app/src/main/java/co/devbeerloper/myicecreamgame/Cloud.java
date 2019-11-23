package co.devbeerloper.myicecreamgame;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class Cloud {

    public static final float INIT_X =2000;
    public static final float INIT_Y =10;
    public static final int SPRITE_SIZE_WIDTH =300;
    public static final int SPRITE_SIZE_HEIGTH=200;
    public static final float GRAVITY_FORCE=10;
    private final int MIN_SPEED = 1;
    private final int MAX_SPEED = 20;

    private float maxY;
    private float maxX;

    private float speed = 0;
    private float positionX;
    private float positionY;
    private Bitmap spriteCloud;


    public Cloud (Context context, float screenWidth, float screenHeigth){

        speed = 1;
        positionX = this.INIT_X;
        positionY = this.INIT_Y;
        //Getting bitmap from resource
        Bitmap originalBitmap= BitmapFactory.decodeResource(context.getResources(), R.drawable.cloud);
        spriteCloud  = Bitmap.createScaledBitmap(originalBitmap, SPRITE_SIZE_WIDTH, SPRITE_SIZE_HEIGTH, false);

        this.maxX = screenWidth - (spriteCloud.getWidth()/2);
        this.maxY = screenHeigth - spriteCloud.getHeight();
    }

    public Cloud (Context context, float initialX, float initialY, float screenWidth, float screenHeigth){

        speed = 1;
        positionX = initialX;
        positionY = initialY;

        Bitmap originalBitmap= BitmapFactory.decodeResource(context.getResources(), R.drawable.icecreamcar);
        spriteCloud  = Bitmap.createScaledBitmap(originalBitmap, SPRITE_SIZE_WIDTH, SPRITE_SIZE_HEIGTH, false);

        this.maxX = screenWidth - (spriteCloud.getWidth()/2);
        this.maxY = screenHeigth - spriteCloud.getHeight();

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
    /**
     * Control the position and behaviour of the cloud
     */
    public void updateInfo () {
        speed = 5;
        this.positionX -=speed;
        if(this.positionX == 0) {
            this.positionX = INIT_X;
        }
    }
}
