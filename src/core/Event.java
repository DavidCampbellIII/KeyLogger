package core;

import lc.kra.system.keyboard.event.GlobalKeyEvent;
import java.io.Serializable;

public class Event implements Serializable {

    private GlobalKeyEvent type;
    private KeyState state;

    private long downTimeStamp;
    private long upTimeStamp;
    private long elaspedTimeStamp;

    public Event(GlobalKeyEvent type){
        this.type = type;

        state = KeyState.KEY_DOWN;
        downTimeStamp = System.currentTimeMillis();
    }

    public void release()
    {
        state = KeyState.KEY_UP;
        upTimeStamp = System.currentTimeMillis();
        elaspedTimeStamp = upTimeStamp - downTimeStamp;
    }

    public GlobalKeyEvent getType()
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
