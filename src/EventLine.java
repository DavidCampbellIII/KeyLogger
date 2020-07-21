import org.jnativehook.keyboard.NativeKeyEvent;

import java.util.*;

public class EventLine {
    public long lastTime = 0;
    public HashMap<Integer, Event> activeEvents;
    public List<Event> completedEvents;
    public List<Action> actions;

    public EventLine()
    {
        activeEvents = new HashMap<Integer, Event>();
        completedEvents = new ArrayList<>();
        actions = new ArrayList<>();
    }

    public void addEvent(int type, KeyState state)
    {
        if(state == KeyState.KEY_DOWN && !activeEvents.containsKey(type))
        {
            System.out.println("Pressing down key!");
            activeEvents.put(type, new Event(type));
            actions.add(new Action(type, KeyState.KEY_DOWN));
        }
        else
        {
            System.out.println("Releasing key!");
            Event completedEvent = activeEvents.remove(type);
            completedEvent.release();
            completedEvents.add(completedEvent);
            actions.add(new Action(type, KeyState.KEY_UP));
        }
    }

    public String toStringActions()
    {
        StringBuilder result = new StringBuilder();

        for(Action action : actions)
        {
            result.append(action.getTimeStamp());
            result.append(":\t");
            result.append(NativeKeyEvent.getKeyText(action.getKey()));
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
            result.append(NativeKeyEvent.getKeyText(event.getType()));
            result.append(":\t");
            result.append(event.getElaspedTimeStamp());
            result.append("\n");
        }

        return result.toString();
    }

    /*public String toString(){
        String out = "";
        lastTime = 0;
        Map<Integer, Long> keystarttime = new HashMap<>();
        for(Action action : actions){
            if(action.getState() == KeyState.KEY_DOWN){
                keystarttime.put(event.e.getKeyCode(),event.e.getWhen());
                out += String.format("%d ↓ %s ", event.e.getWhen() - lastTime, NativeKeyEvent.getKeyText(event.e.getKeyCode()));
            } else if(event.type == KEY_UP) {
                long time = event.e.getWhen() - keystarttime.get(event.e.getKeyCode());
                out += String.format("%d ↑ %s ", time, NativeKeyEvent.getKeyText(event.e.getKeyCode()));
            }
            lastTime = event.e.getWhen();
        }
        return out;
    }
     */
}
