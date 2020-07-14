package com.company;
import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;
import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.keyboard.NativeKeyListener;

public class MainBackup {

    static class Listener implements NativeKeyListener{

        @Override
        public void nativeKeyTyped(NativeKeyEvent nativeKeyEvent) {
            System.out.println("Key Typed");
            System.out.println(nativeKeyEvent.toString());
        }

        @Override
        public void nativeKeyPressed(NativeKeyEvent nativeKeyEvent) {

        }

        @Override
        public void nativeKeyReleased(NativeKeyEvent nativeKeyEvent) {

        }
    }

    public static void main(String[] args) {
        try{
            GlobalScreen.registerNativeHook();
            GlobalScreen.addNativeKeyListener(new Listener());

        }
        catch(NativeHookException ex){
            System.out.println(ex.toString());
        }
    }
}

