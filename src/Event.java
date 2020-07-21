import org.jnativehook.keyboard.NativeKeyEvent;

public class Event implements java.io.Serializable {

    private int type;
    private KeyState state;

    private long downTimeStamp;
    private long upTimeStamp;
    private long elaspedTimeStamp;

    public Event(int type){
        this.type = type;

        state = KeyState.KEY_DOWN;
        downTimeStamp = System.nanoTime();
    }

    public void release()
    {
        state = KeyState.KEY_UP;
        upTimeStamp = System.nanoTime();
        elaspedTimeStamp = upTimeStamp - downTimeStamp;
    }

    public int getType()
    {
        return type;
    }

    public KeyState getState()
    {
        return state;
    }

    public long getElaspedTimeStamp()
    {
        return elaspedTimeStamp;
    }
}
