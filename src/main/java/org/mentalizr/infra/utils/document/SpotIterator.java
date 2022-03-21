package org.mentalizr.infra.utils.document;

public interface SpotIterator {

    boolean hasNext();

    Line getNext();

    void stepForward();

    boolean hasCurrent();

    Line getCurrent();

    boolean hasPrevious();

    Line getPrevious();

    void stepBack();
}
