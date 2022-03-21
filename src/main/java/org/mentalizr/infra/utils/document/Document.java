package org.mentalizr.infra.utils.document;

import java.util.List;

public interface Document extends Iterable<String> {

    List<String> getStrings();

    List<Line> getLines();

    String asString();

    boolean isEmpty();

    int getNrOfLines();

    String getStringByIndex(int index);

    String getStringByNumber(int number);

    Line getLineByIndex(int index);

    Line getLineByNumber(int number);
}
