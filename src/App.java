public class App {
    public static void main(String[] args) throws Exception {
        TuringMachine tm = new TuringMachine();
        String input = "10+11";
        tm.processInput(input);
    }
}
