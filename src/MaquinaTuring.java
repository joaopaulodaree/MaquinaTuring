import java.util.ArrayList;
import java.util.List;

public class MaquinaTuring {
    private String tape;
    private int position;
    private String currentState;
    private Transicoes transitions;
    private ArrayList<String> finalStates = transitions.getFinalStates();

    public MaquinaTuring() {
        currentState = transitions.getCurrentState();
    }

    public void verificarPalavra(String palavra) {
        for (char letra : palavra.toCharArray()) {
            if (!transitions.transicao(letra)) {
                System.out.println("A Palavra " + palavra + " não foi aceita!");
                return;
            }
        }
        if (currentState.equals(finalStates.get(0)))
            System.out.println("A Palavra " + palavra + " foi aceita!");
        else
            System.out.println("A Palavra " + palavra + " não foi aceita!");

    }

}