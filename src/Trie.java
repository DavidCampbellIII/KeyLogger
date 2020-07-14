import org.jnativehook.keyboard.NativeKeyEvent;

import java.util.HashMap;

public class Trie {
    class Node  {
        public String data;
        public HashMap<String, Node> children;
        public int count;
        public double avgKeyTime;

        Node(String data) {
            children = new HashMap<>();
            count = 0;
            this.data = data;
            this.avgKeyTime =0;
        }
    }
    Node root;
    Node current;

    public Trie(){
        root = new Node(" ");
        current = root;
    }
    public void add(EventLine event){
        for(EventLine.Event e:event.events){
            if(e.type == EventLine.KEY_DOWN){
                String data = NativeKeyEvent.getKeyText(e.e.getKeyCode());
                if(data.equals(" ")){
                    current.count++;
                    current = root;
                }
                else {
                    if (!current.children.containsKey(data)) {
                        current.children.put(data, new Node(data));
                    }
                    current = current.children.get(data);
                }
            }
        }
    }
    public void report(){
        report(root, "");
    }

    public void report(Node current, String out){
        out += current.data;
        if(current.count > 0){
            System.out.println(out);
        }
        for(String key:current.children.keySet()){
            report(current.children.get(key), out);
        }
    }

}
