package it.unibo.oop.lab.mvc;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class SimpleController implements Controller {

    private final List<String> historyList = new LinkedList<>();
    private String nextString;
    @Override
    public final void setNextStringToPrint(final String nextString) {
        this.nextString = Objects.requireNonNull(nextString, "This method does not accept null values.");
    }

    @Override
    public final String getNextStringToPrint() {
        return this.nextString;
    }

    @Override
    public final List<String> getPrintedStrings() {
        return this.historyList;
    }

    @Override
    public final void printCurrentString() {
        if (this.nextString == null) {
            throw new IllegalStateException("Theres is no string set");
        }
        historyList.add(this.nextString);
        System.out.println(this.nextString);
    }
}
