import java.util.HashMap;
import java.util.Map;

public class TuringMachine {
    private Map<String, Map<Character, Transition>> transitions = new HashMap<>();

    public TuringMachine() {
        createTransition("q0", 'B', "qf", 'B', 'R');
        createTransition("q0", 'a', "q1", 'B', 'R');

        createTransition("q1", 'a', "q1", 'a', 'R');
        createTransition("q1", 'b', "q1", 'b', 'R');
        createTransition("q1", 'B', "q2", 'B', 'L');

        createTransition("q2", 'b', "q3", 'B', 'L');

        createTransition("q3", 'b', "q4", 'B', 'L');

        createTransition("q4", 'a', "q4", 'a', 'L');
        createTransition("q4", 'b', "q4", 'b', 'L');
        createTransition("q4", 'B', "qf", 'B', 'R');
    }

    private void createTransition(String state, char symbol, String nextState, char writeSymbol, char direction){
        transitions.computeIfAbsent(state, k -> new HashMap<>()).put(symbol, new Transition(nextState, writeSymbol, direction));
    }

    public void processInput(String input) {
        String currentState = "q0";
        int tapePosition = 0;
        char[] tape = input.toCharArray();
        
        while (!currentState.equals("qf")) {
            char currentSymbol = tapePosition >= 0 && tapePosition < tape.length ? tape[tapePosition] : ' ';
            
            Transition transition = transitions.get(currentState).get(currentSymbol);
            if (transition == null) {
                System.out.println("Rejeitado");
                return;
            }
            tape[tapePosition] = transition.getWriteSymbol();
            
            if (transition.getDirection() == 'R') {
                tapePosition++;
                if (tapePosition == tape.length) {
                    char[] newTape = new char[tape.length + 1];
                    System.arraycopy(tape, 0, newTape, 0, tape.length);
                    newTape[tape.length] = 'B';
                    tape = newTape;
                }
            } else if (transition.getDirection() == 'L') {
                tapePosition--;
                if (tapePosition < 0) {
                    char[] newTape = new char[tape.length + 1];
                    System.arraycopy(tape, 0, newTape, 1, tape.length);
                    newTape[0] = 'B';
                    tape = newTape;
                    tapePosition = 0;
                }
            }
            
            currentState = transition.getNextState();
        }
        
        System.out.println("Aceito");
    }
    
}
