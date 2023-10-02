import java.util.ArrayList;

public class MaquinaTuring {
    private String currentState;
    private Transicoes transitions;
    private ArrayList<String> finalStates;

    public MaquinaTuring() {
        transitions = new Transicoes();
        finalStates = transitions.getFinalStates();
        currentState = transitions.getCurrentState();
    }

    public void verificarPalavra(String palavra) {
        for (char letra : palavra.toCharArray()) {
            if (!transitions.transicao(letra)) {
                System.out.println("A Palavra " + palavra + " não foi aceita!");
                return;
            }
        }
        currentState = transitions.getCurrentState();
        
        if (currentState.equals("qf"))
            System.out.println("A Palavra " + palavra + " foi aceita!");
        else
            System.out.println("A Palavra " + palavra + " não foi aceita!");

    }

}