import org.jnativehook.keyboard.NativeKeyEvent;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class EventLine {
    public static char KEY_UP = 'U';
    public static char KEY_DOWN = 'D';
//    class Event{
//        public char type;
//        public NativeKeyEvent e;
//
//        public Event(char type, NativeKeyEvent e){
//            this.type = type;
//            this.e = e;
//        }
//    }

    public long lastTime = 0;
    public List<Event> events;
    public EventLine(){

        events = new LinkedList<>();
    }

    public void addEvent(char type, NativeKeyEvent e){
        events.add(new Event(type, e));
    }

    public String toString(){
        String out = "";
        lastTime = 0;
        Map<Integer, Long> keystarttime = new HashMap<>();
        for(Event event:events){
            if(event.type == KEY_DOWN){
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

}
