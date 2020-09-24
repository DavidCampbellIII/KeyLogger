package core;

import lc.kra.system.keyboard.event.GlobalKeyEvent;
import profiles.KeyLog;

import java.util.*;

public class EventLine {
    private long lastTime = 0;
    private HashMap<Integer, Event> activeEvents;
    private KeyLog trackedKeyLog;

    private long startTime;

    public EventLine(KeyLog trackedKeyLog)
    {
        this.trackedKeyLog = trackedKeyLog;
        activeEvents = new HashMap<Integer, Event>();
        startTime = System.currentTimeMillis();
    }

    public void addEvent(GlobalKeyEvent type, KeyState state)
    {
        int keyCode = type.getVirtualKeyCode();
        if(state == KeyState.KEY_DOWN)
        {
            if(!activeEvents.containsKey(keyCode))
            {
                //System.out.println("Pressing down key!");
                activeEvents.put(keyCode, new Event(type));
                trackedKeyLog.addAction(new Action(type, KeyState.KEY_DOWN, System.currentTimeMillis() - startTime));
                lastTime = System.currentTimeMillis();
            }
        }
        else //Key state is UP
        {
            //System.out.println("Releasing key!");
            Event completedEvent = activeEvents.remove(keyCode);
            completedEvent.release();
            trackedKeyLog.addEvent(completedEvent);
            trackedKeyLog.addAction(new Action(type, KeyState.KEY_UP, System.currentTimeMillis() - startTime));
            //System.out.println("Elapsed: " + (System.currentTimeMillis() - lastTime));
        }
    }
}
