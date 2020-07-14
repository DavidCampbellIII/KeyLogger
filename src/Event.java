import org.jnativehook.keyboard.NativeKeyEvent;

public class Event implements java.io.Serializable {

    public char type;
    public NativeKeyEvent e;

    public Event(char type, NativeKeyEvent e){
        this.type = type;
        this.e = e;
    }
}
