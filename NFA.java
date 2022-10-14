import java.util.ArrayList;

public class NFA {
    /*Each NFA consists of start state, accept state
     * as well as a list of transitions that will
     * define the possible valid strings associated
     * with the regex passed to the object.
     * ---
     * Notable methods include the concat, union, and 
     * klene star methods. Each of these methods perform
     * the associated operation onto the associated NFA(s)
     * passed to the method                               */
    private int startState;
    private int acceptState;
    private ArrayList<Transitions> trns;

    /*Constructor assumes the NFA is being created for the first time
     * This allowing a basic NFA with one start and one accept state
     * to be created. More complex NFAs are created by performing the
     * methods that perform concatenation, union, and klene star 
     * operations on the object(s)                                  */
    public NFA(int x, int y, char sym) {
        startState = x;
        acceptState = y;
        trns = new ArrayList<Transitions>();
        Transitions firstTrns = new Transitions(startState, acceptState, sym);
        this.addTrans(firstTrns);
    }

    /*The following method is used to add a newly constructed transition
     * to an existing NFA. In addition to adding it to the associated ArrayList,
     * the addTrans method also positions the new transition in the proper order
     * for printing the output (i.e. orderd by start state, end state, and symbol) */
    public void addTrans(Transitions newTrans) {
        int curSize = this.trns.size();
        if(curSize <= 0) {
            trns.add(newTrans);
            return;
        }
        int i = 0;
        while(newTrans.state_one >= this.trns.get(i).state_one) {
            i++;
            if(i >= curSize) {
                trns.add(newTrans);
                return;
            }
        }

        while(i < curSize) {
            Transitions temp = trns.get(i);
            trns.set(i, newTrans);
            newTrans = temp;
            i++;
        }
        trns.add(newTrans);
    }
    /*Basic fetch methods for other objects */
    public int getStartState() {
        return(startState);
    }
    public int getAcceptState() {
        return(acceptState);
    }
    public int getNumTrans() {
        return trns.toArray().length;
    }
    public Transitions getTransAt(int i) {
        return trns.get(i);
    }

    /*Cocnat method creates a new transition between this NFA and the one passed to it.
     * Then it sets this NFA's accept state to the accept state of the original NFA */
    public void concat(NFA nfa) {
        Transitions newTrans = new Transitions(acceptState, nfa.getStartState(), 'E');
        this.addTrans(newTrans);
        for(int i = 0; i < nfa.getNumTrans(); i++) {
            this.addTrans(nfa.getTransAt(i));
        }
        acceptState = nfa.getAcceptState();
    }
    /* The union operation generates a new start state, with E transitions to the start
     * states of this NFA and the NFA passed to it. It also generates a new accept state
     * that has E transitions from the original accept states of both NFAs.            */
    public void union(NFA nfa, int start, int accept) {
        this.addTrans(new Transitions(start, this.startState, 'E'));
        this.addTrans(new Transitions(start, nfa.getStartState(), 'E'));
        this.addTrans(new Transitions(this.acceptState, accept, 'E'));
        this.addTrans(new Transitions(nfa.getAcceptState(), accept, 'E'));
        for(int i = 0;  i < nfa.getNumTrans(); i++) {
            this.addTrans(nfa.getTransAt(i));
        }
        startState = start;
        acceptState = accept;
    }
    /*The kleene() method generates a new start state that is also the accept state.
     * The original accept state now has an E transition to a new state that has an E
     * transition to the start state.                                               */
    public void kleene(int start) {
        this.addTrans(new Transitions(start, this.startState, 'E'));
        this.addTrans(new Transitions(this.acceptState, start, 'E'));
        startState = start;
        acceptState = start;

    }

    /*Prints the start and accept states as well as the list of transitions */
    public void print() {
        System.out.println("Start: q"+startState);
        System.out.println("Accept: q"+acceptState);
        for(int i = 0; i < trns.size(); i++ ) {
            trns.get(i).print();
        }
    }
}
