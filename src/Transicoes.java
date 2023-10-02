import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Transicoes {
    private Map<String, Map<Character, String>> transitions = new HashMap<>();
    private String currentState;
    private ArrayList<String> finalStates = new ArrayList<>();

    public Transicoes() {
        currentState = "q0";
        finalStates.add("qf");
        transitions.put("q0", Map.of('a', "q1", 'b', "q0", 'c', "q0" ));
        transitions.put("q1", Map.of('a', "q2", 'b', "q1", 'c', "q1" ));
        transitions.put("q2", Map.of('a', "q2", 'b', "qf", 'c', "q0"));
        transitions.put("qf", Map.of('a', "qf", 'b', "qf", 'c', "qf"));
    }

    public boolean transicao(char entry) {
        if (transitions.get(currentState).containsKey(entry)) {
            currentState = transitions.get(currentState).get(entry);
            return true;
        } else {
            currentState = null;
            return false;
        }
    }

    public ArrayList<String> getFinalStates() {
        return finalStates;
    }

    public String getCurrentState() {
        return currentState;
    }

}
