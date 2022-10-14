package com.company;
import java.util.ArrayList;

public class NFA {
    private int startState;
    private int acceptState;
    private ArrayList<Transitions> trns;

    public NFA(int x, int y, char sym) {
        startState = x;
        acceptState = y;
        trns = new ArrayList<Transitions>();
        Transitions firstTrns = new Transitions(startState, acceptState, sym);
        this.addTrans(firstTrns);
    }

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

    public void concat(NFA nfa) {
        Transitions newTrans = new Transitions(acceptState, nfa.getStartState(), 'E');
        this.addTrans(newTrans);
        for(int i = 0; i < nfa.getNumTrans(); i++) {
            this.addTrans(nfa.getTransAt(i));
        }
        acceptState = nfa.getAcceptState();
    }
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
    public void kleene(int start) {
        this.addTrans(new Transitions(start, this.startState, 'E'));
        this.addTrans(new Transitions(this.acceptState, start, 'E'));
        startState = start;
        acceptState = start;

    }

    public void print() {
        System.out.println("Start State: q"+startState);
        System.out.println("Accept State: q"+acceptState);
        for(int i = 0; i < trns.size(); i++ ) {
            trns.get(i).print();
        }
    }
}
