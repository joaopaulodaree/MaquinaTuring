public class App {
    public static void main(String[] args) throws Exception {
        TuringMachine tm = new TuringMachine();
        String input = "aabababababababb";
        tm.processInput(input);
    }
}
