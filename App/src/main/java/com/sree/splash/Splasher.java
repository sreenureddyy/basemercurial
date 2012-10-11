package com.sree.splash;


/**
 *
 * @author  Sree
 */
public class Splasher {
    /**
     * Shows the splash screen, launches the application and then disposes
     * the splash screen.
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        SplashWindow.splash(Splasher.class.getResource("images/splash.gif"));
        SplashWindow.invokeMain("com.sree.splash.MyApplication", args);
        SplashWindow.disposeSplash();
    }
    
}
