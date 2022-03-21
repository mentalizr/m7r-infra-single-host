package org.mentalizr.infra.utils.document;

import java.util.List;

public class LineSpotIterator implements SpotIterator {

    private final List<Line> lines;
    private int index;

    public LineSpotIterator(Document document) {
        this.lines = document.getLines();
        this.index = -1;
    }

    @Override
    public boolean hasNext() {
        return (this.lines.size() - 1 > this.index);
    }

    @Override
    public Line getNext() {
        if (!hasNext()) throw new IllegalStateException("Document has no next line.");
        this.index++;
        return this.lines.get(this.index);
    }

    @Override
    public void stepForward() {
        if (!hasNext()) throw new IllegalStateException("Document has no next line.");
        this.index++;
    }

    @Override
    public boolean hasCurrent() {
        return (this.index >= 0 && !this.lines.isEmpty());
    }

    @Override
    public Line getCurrent() {
        if (!hasCurrent()) throw new IllegalStateException("Document has no current line.");
        return this.lines.get(this.index);
    }

    @Override
    public boolean hasPrevious() {
        return (this.index >= 1);
    }

    @Override
    public Line getPrevious() {
        if (!hasPrevious()) throw new IllegalStateException("Document has no previous line.");
        this.index--;
        return this.lines.get(this.index);
    }

    @Override
    public void stepBack() {
        if (!hasPrevious()) throw new IllegalStateException("Document has no previous line.");
        this.index--;
    }

}
