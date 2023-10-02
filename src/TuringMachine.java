import java.util.HashMap;
import java.util.Map;

public class TuringMachine {
    private Map<String, Map<Character, Transition>> transitions = new HashMap<>();

    public TuringMachine() {
        transitions.put("q0", Map.of(
            'B', new Transition("qf", 'B', 'R'),
            'a', new Transition("q1", 'B', 'R')
        ));
        
        transitions.put("q1", Map.of(
            'a', new Transition("q1", 'a', 'R'),
            'b', new Transition("q1", 'b', 'R'),
            'B', new Transition("q2", 'B', 'L')
        ));
        
        transitions.put("q2", Map.of(
            'b', new Transition("q3", 'B', 'L')
        ));

        transitions.put("q3", Map.of(
            'b', new Transition("q4", 'B', 'L')
        ));
        
        transitions.put("q4", Map.of(
            'a', new Transition("q4", 'a', 'L'),
            'b', new Transition("q4", 'b', 'L'),
            'B', new Transition("qf", 'B', 'R')
        ));
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
