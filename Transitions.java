public class Transitions {
    /*The transitions contain the two states
     * as well as the symbol that connects them */
    int state_one;
    int state_two;
    char symbol;

    /*The constructor simply sets the values of the object */
    public Transitions(int x, int y, char c) {
        state_one = x;
        state_two = y;
        symbol = c;
    }
    /*There exists both a print and a toString method that effectively operate
     * identically. It's just a matter of implementation which you'd like to use.
     * when printing the value of the transition */
    public void print() {
        System.out.println("(q"+state_one+", "+symbol+") -> q"+state_two);
    }
    public String toString() {
        return "(q"+state_one+", "+symbol+") -> q"+state_two;
    }
}
