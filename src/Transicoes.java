import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Transicoes {
    private Map<String, Map<Character, String>> transitions = new HashMap<>();
    private String currentState;
    private ArrayList<String> finalStates = new ArrayList<>();

    public Transicoes(){
        currentState = "q0";
        finalStates.add("qF");

    }
}
