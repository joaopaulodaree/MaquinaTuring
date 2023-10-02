import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Transicoes {
    private Map<String, Map<Character, String>> transitions = new HashMap<>();
    private String currentState;
    private ArrayList<String> finalStates = new ArrayList<>();

    public Transicoes() {
        currentState = "q0";
        finalStates.add("qF");
        transitions.put("q0", Map.of('a', "q1", 'b', "q2"));
        transitions.put("q1", Map.of('a', "qf", 'b', "q2"));
        transitions.put("q2", Map.of('a', "q1", 'b', "qf"));
        transitions.put("qf", Map.of('a', "qf", 'b', "qf"));
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
