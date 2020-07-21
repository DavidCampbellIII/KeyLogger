import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;
import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.keyboard.NativeKeyListener;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Main implements NativeKeyListener {

    long startTime;
    long pressedTime;
    long releasedTime;
    EventLine keyline;
    int keycount = 0;
    Trie words = new Trie();
    TreeMap<String, Context> contexts;
    //Logger l = Logger.getLogger();
    PrintWriter pw = new PrintWriter(new File("KeyLogger.txt"));

    public Main() throws FileNotFoundException {
        startTime = System.currentTimeMillis();
    }

    @Override
    public void nativeKeyTyped(NativeKeyEvent e) {
        //System.out.println("Key Typed1");
        //System.out.println(nativeKeyEvent.toString());
    }

    @Override
    public void nativeKeyPressed(NativeKeyEvent e) {
        //System.out.println("Key Pressed " + NativeKeyEvent.getKeyText(e.getKeyCode()));
        if(keycount ==0){
            keyline = new EventLine();
        }
        keycount++;
        keyline.addEvent(e.getKeyCode(), KeyState.KEY_DOWN);
        pressedTime = System.currentTimeMillis();
    }

    @Override
    public void nativeKeyReleased(NativeKeyEvent e) {
        keycount--;
        keyline.addEvent(e.getKeyCode(), KeyState.KEY_UP);
        if(keycount == 0){
            //System.out.println(keyline.toStringEvents());
            pw.println(keyline.toStringEvents());
            pw.flush();
            //words.add(keyline);
        }
        //System.out.println("Key Released " + NativeKeyEvent.getKeyText(e.getKeyCode()));
        //System.out.println(e.getWhen()/1000.0);
        releasedTime = System.currentTimeMillis();

        long totalTime = releasedTime - pressedTime;
        //System.out.println("Total time key pressed " + totalTime);

    }

    public static void main(String[] args) throws FileNotFoundException {
        Logger logger = Logger.getLogger(GlobalScreen.class.getPackage().getName());
        logger.setLevel(Level.WARNING);
        logger.setUseParentHandlers(false);

	    try{
	       GlobalScreen.registerNativeHook();


        }
	    catch(NativeHookException ex){
            System.out.println(ex.toString());
        }
        GlobalScreen.addNativeKeyListener(new Main());

    }

    public void finalize(){
        words.report();
    }
}
