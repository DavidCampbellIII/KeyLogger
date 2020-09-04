package core;

import lc.kra.system.keyboard.event.GlobalKeyEvent;

import java.util.*;

public class EventLine {
    public long lastTime = 0;
    public HashMap<Integer, Event> activeEvents;
    public List<Event> completedEvents;
    public List<Action> actions;

    private long startTime;

    public EventLine()
    {
        activeEvents = new HashMap<Integer, Event>();
        completedEvents = new ArrayList<>();
        actions = new ArrayList<>();
        startTime = System.currentTimeMillis();
    }

    public void addEvent(GlobalKeyEvent type, KeyState state)
    {
        int keyCode = type.getVirtualKeyCode();
        if(state == KeyState.KEY_DOWN)
        {
            if(!activeEvents.containsKey(keyCode))
            {
                System.out.println("Pressing down key!");
                activeEvents.put(keyCode, new Event(type));
                actions.add(new Action(type, KeyState.KEY_DOWN, System.currentTimeMillis() - startTime));
                lastTime = System.currentTimeMillis();
            }
        }
        else //Key state is UP
        {
            System.out.println("Releasing key!");
            Event completedEvent = activeEvents.remove(keyCode);
            completedEvent.release();
            completedEvents.add(completedEvent);
            actions.add(new Action(type, KeyState.KEY_UP, System.currentTimeMillis() - startTime));
            System.out.println("Elapsed: " + (System.currentTimeMillis() - lastTime));
        }
    }

    public String toStringActions()
    {
        StringBuilder result = new StringBuilder();

        for(Action action : actions)
        {
            result.append("TIMESTAMP:");
            result.append(action.getDateTime());
            result.append("\t\tFROM START:");
            result.append(String.format("%10d", action.getFromStart()));
            result.append("\t");
            result.append(action.getKey().getKeyChar());
            result.append("\t");
            result.append(action.getState());
            result.append("\n");
        }

        return result.toString();
    }

    public String toStringEvents()
    {
        StringBuilder result = new StringBuilder();

        for(Event event : completedEvents)
        {
            result.append(event.getType().getKeyChar());
            result.append(" - ELAPSED TIME (DU):\t\t");
            result.append(event.getElaspedTimeStamp());
            result.append("ms\n");
        }

        return result.toString();
    }
}
