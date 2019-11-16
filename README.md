# Android_2DGame - ICE CREAM GAME
This is an example of a simple 2D game creation with android (java)

The rules of the game are:
1. The player (IceCream Car) has to give icecreams to the kids by collide them
2. The player has to avoid to collide with the parents that appear in the game, if a parent collide with the player the game is over 


## Tutorial - Step by step
Follow the next steps to replicate this game
### 1. Integrate the assets to the project
Put the assets (images) inside the folder drawable 
### 2. Add the general theme of the game
Configure a new style inside the res/values/styles.xml file. 

    <!-- General Theme of the app -->
    <style name="AppTheme" parent="Theme.AppCompat.Light.DarkActionBar">
        <item name="windowActionBar">false</item>
        <item name="android:windowFullscreen">true</item>
        <item name="android:windowContentOverlay">@null</item>
        <item name="colorPrimary">@color/colorPrimary</item>
        <item name="colorPrimaryDark">@color/colorPrimaryDark</item>
        <item name="colorAccent">@color/colorAccent</item>
        <item name="windowNoTitle">true</item>
        
    </style>

### 3. Configure the colors in the res/values/colors.xml

    <color name="colorPrimary">#DC22EB</color>
    <color name="colorPrimaryDark">#00574B</color>
    <color name="colorAccent">#D81B60</color>

### 4. Create your menu

In the main activity place an image and the play button, create a method to manage the action of the button, this method has to redirect to a new activity.

### 5. Create the Gameplay activity

### 6. Create a SurfaceView to paint the game

- Create a new class that inherits from SurfaceView and implements the Runnable interface. 
- Add the methods to manage and control the execution (pause, resume, paint, updateInfo)
- Create an attribute to paint in (Canvas) and a painter (Paint) and initilize them in the constructor
- Create a holder to manage the SurfaceView
- Inside this class implement the run method and a thread to manage the execution of the game.
 
    /**
     * Method implemented from runnable interface
     */
    @Override
    public void run() {
        while (isPlaying) {
            updateInfo();
            paintFrame();

        }

    }


### 7. Add the runnable



## Assets taken from
CAR SPRITE:  www.lonegames.net https://opengameart.org/content/ice-cream-car
KID https://opengameart.org/content/pixel-kid
PARENT 
