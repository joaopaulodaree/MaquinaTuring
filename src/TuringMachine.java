import java.util.HashMap;
import java.util.Map;

public class TuringMachine {
    private Map<String, Map<Character, Transition>> transitions;

    public TuringMachine() {
        // createTransition("q0", 'B', "qf", 'B', 'R');
        // createTransition("q0", 'a', "q1", 'B', 'R');

        // createTransition("q1", 'a', "q1", 'a', 'R');
        // createTransition("q1", 'b', "q1", 'b', 'R');
        // createTransition("q1", 'B', "q2", 'B', 'L');

        // createTransition("q2", 'b', "q3", 'B', 'L');

        // createTransition("q3", 'b', "q4", 'B', 'L');

        // createTransition("q4", 'a', "q4", 'a', 'L');
        // createTransition("q4", 'b', "q4", 'b', 'L');
        // createTransition("q4", 'B', "qf", 'B', 'R');

        transitions = new HashMap<>();

        createTransition("q0", '0', "q0", '0', 'R');
        createTransition("q0", '1', "q0", '1', 'R');
        createTransition("q0", '+', "q0", '+', 'R');
        createTransition("q0", 'B', "q1", 'B', 'L');
        createTransition("q1", '+', "q2", 'B', 'L');
        createTransition("q1", '0', "q7", 'C', 'L');
        createTransition("q1", '1', "q3", 'C', 'L');
        createTransition("q2", '0', "q2", '0', 'L');
        createTransition("q2", '1', "q2", '1', 'L');
        createTransition("q2", 'I', "q2", '1', 'L');
        createTransition("q2", 'O', "q2", '0', 'L');
        createTransition("q2", 'B', "qf", 'B', 'R');
        createTransition("q3", '0', "q3", '0', 'L');
        createTransition("q3", '1', "q3", '1', 'L');
        createTransition("q3", '+', "q4", '+', 'L');
        createTransition("q4", 'O', "q4", 'O', 'L');
        createTransition("q4", 'I', "q4", 'I', 'L');
        createTransition("q4", '1', "q5", 'O', 'L');
        createTransition("q4", '0', "q6", 'I', 'R');
        createTransition("q5", '1', "q5", '0', 'L');
        createTransition("q5", '0', "q6", '1', 'R');
        createTransition("q5", 'B', "q6", '1', 'R');
        createTransition("q4", 'B', "q6", 'I', 'R');
        createTransition("q6", '0', "q6", '0', 'R');
        createTransition("q6", '1', "q6", '1', 'R');
        createTransition("q6", 'O', "q6", 'O', 'R');
        createTransition("q6", 'I', "q6", 'I', 'R');
        createTransition("q6", '+', "q6", '+', 'R');
        createTransition("q6", 'C', "q1", '1', 'L');
        createTransition("q7", '0', "q7", '0', 'L');
        createTransition("q7", '1', "q7", '1', 'L');
        createTransition("q7", '+', "q8", '+', 'L');
        createTransition("q8", 'O', "q8", 'O', 'L');
        createTransition("q8", 'I', "q8", 'I', 'L');
        createTransition("q8", '0', "q9", 'O', 'R');
        createTransition("q8", '1', "q9", 'I', 'R');
        createTransition("q9", '0', "q6", '0', 'R');
        createTransition("q9", '1', "q6", '1', 'R');
        createTransition("q9", 'O', "q6", 'O', 'R');
        createTransition("q9", 'I', "q6", 'I', 'R');
        createTransition("q9", '+', "q6", '+', 'R');
        createTransition("q9", 'C', "q1", '0', 'L');
    }

    private void createTransition(String state, char symbol, String nextState, char writeSymbol, char direction) {
        transitions.computeIfAbsent(state, k -> new HashMap<>()).put(symbol,
                new Transition(nextState, writeSymbol, direction));
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
                for (Character letra : tape) {
                    if (letra != 'B')
                        System.out.print(letra);
                }
                System.out.println();
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
        for (Character letra : tape) {
            if (letra != 'B')
                System.out.print(letra);
        }
        System.out.println();
    }

}
