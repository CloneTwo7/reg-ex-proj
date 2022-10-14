public class RegExStack {

    private NFA[] regExStack;
    static int stackSize;
    int top;

    public RegExStack(int size) {
        //initializes the Regular Expression stack given the size of the file
        this.top = 0;
        this.stackSize = size;
        this.regExStack = new NFA[stackSize];
    }

    /*Typical push-pop methods */
    public void push(NFA data) {
        regExStack[top] = data;
        top++;
    }
    public NFA pop() {
        NFA data = regExStack[--top];
        return(data);
    }
    public void print() { //Display values of the stack
        for(int i = 0;  i < top; i++) { //One line at a time
            regExStack[i].print();
        }
    }
}
