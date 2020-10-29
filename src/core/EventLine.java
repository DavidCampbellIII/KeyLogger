package core;

import analysis.RealtimeAnalysis;
import lc.kra.system.keyboard.event.GlobalKeyEvent;
import profiles.KeyLog;

import java.util.*;

public class EventLine {
    private final int NUM_KEYS_FOR_EVAL = 10;

    private long lastTime = 0;
    private HashMap<Integer, Event> activeEvents;
    private KeyLog trackedKeyLog;
    private RealtimeAnalysis analysis;

    private long startTime;
    private int numKeysPressed;

    public EventLine(KeyLog trackedKeyLog)
    {
        this.trackedKeyLog = trackedKeyLog;
        activeEvents = new HashMap<Integer, Event>();
        startTime = System.currentTimeMillis();
        analysis = new RealtimeAnalysis();
        numKeysPressed = 0;
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
            if(completedEvent == null)
            {
                System.out.println("==ERROR: Tried to get active event that did not exist.  Keycode " + keyCode + "==");
            }
            else
            {
                numKeysPressed++;
                completedEvent.release();
                analysis.addData(completedEvent.getElaspedTimeStamp(), System.currentTimeMillis());
                trackedKeyLog.addEvent(completedEvent);
                trackedKeyLog.addAction(new Action(type, KeyState.KEY_UP, System.currentTimeMillis() - startTime));
                //System.out.println("Elapsed: " + (System.currentTimeMillis() - lastTime));

                if(numKeysPressed >= NUM_KEYS_FOR_EVAL)
                {
                    numKeysPressed = 0;
                    analysis.evaluate();
                }
            }
        }
    }
}
