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
            lastTime = System.currentTimeMillis();
        }
        else
        {
            System.out.println("Releasing key!");
            Event completedEvent = activeEvents.remove(type);
            completedEvent.release();
            completedEvents.add(completedEvent);
            actions.add(new Action(type, KeyState.KEY_UP));
            System.out.println("Elapsed: " + (System.currentTimeMillis() - lastTime));
        }
    }

    public String toStringActions()
    {
        StringBuilder result = new StringBuilder();

        for(Action action : actions)
        {
            result.append(action.getTimeStamp());
            result.append(":\t");
            //result.append(NativeKeyEvent.getKeyText(action.getKey()));
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
            //result.append(NativeKeyEvent.getKeyText(event.getType()));
            result.append(":\t");
            result.append(event.getElaspedTimeStamp());
            result.append("\n");
        }

        return result.toString();
    }
}
