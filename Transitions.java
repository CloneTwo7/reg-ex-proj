public class Transitions {
    int state_one;
    int state_two;
    char symbol;

    public Transitions(int x, int y, char c) {
        state_one = x;
        state_two = y;
        symbol = c;
    }
    public void print() {
        System.out.println("(q"+state_one+", "+symbol+") -> q"+state_two);
    }
    public String toString() {
        return "(q"+state_one+", "+symbol+") -> q"+state_two;
    }
}
