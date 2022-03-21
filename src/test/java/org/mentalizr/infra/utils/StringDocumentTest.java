package org.mentalizr.infra.utils;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mentalizr.infra.utils.document.Document;
import org.mentalizr.infra.utils.document.Line;
import org.mentalizr.infra.utils.document.StringDocument;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class StringDocumentTest {

    private StringDocument createStringDocument1() {
        return new StringDocument.Builder()
                .addString("line1")
                .addString("line2")
                .addString("line3")
                .build();
    }

    @Test
    void isEmpty_Pos() {
        StringDocument stringDocument = new StringDocument(new ArrayList<>());
        Assertions.assertTrue(stringDocument.isEmpty());
    }

    @Test
    void isEmpty_Neg() {
        StringDocument stringDocument = createStringDocument1();
        Assertions.assertFalse(stringDocument.isEmpty());
    }

    @Test
    void getNrOfLines() {
        StringDocument stringDocument = createStringDocument1();
        assertEquals(3, stringDocument.getNrOfLines());
    }

    @Test
    void getLineByIndex() {
        StringDocument stringDocument = createStringDocument1();
        assertEquals("line1", stringDocument.getStringByIndex(0));
    }

    @Test
    void getLineByIndex_min() {
        StringDocument stringDocument = createStringDocument1();
        assertEquals("line1", stringDocument.getStringByIndex(0));
    }

    @Test
    void getLineByIndex_max() {
        StringDocument stringDocument = createStringDocument1();
        assertEquals("line3", stringDocument.getStringByIndex(2));
    }

    @Test
    void getLineByIndex_negIndexOutOfBoundsLower() {
        StringDocument stringDocument = createStringDocument1();
        assertThrows(IllegalArgumentException.class, () -> stringDocument.getStringByIndex(-1));
    }

    @Test
    void getLineByIndex_negIndexOutOfBoundsUpper() {
        StringDocument stringDocument = createStringDocument1();
        assertThrows(IllegalArgumentException.class, () -> stringDocument.getStringByIndex(3));
    }

    @Test
    void getLineByNumber() {
        StringDocument stringDocument = createStringDocument1();
        assertEquals("line1", stringDocument.getStringByNumber(1));
    }

    @Test
    void getLineByNumber_min() {
        StringDocument stringDocument = createStringDocument1();
        assertEquals("line1", stringDocument.getStringByNumber(1));
    }

    @Test
    void getLineByNumber_max() {
        StringDocument stringDocument = createStringDocument1();
        assertEquals("line3", stringDocument.getStringByNumber(3));
    }

    @Test
    void getLineByNumber_negIndexOutOfBoundsLower() {
        StringDocument stringDocument = createStringDocument1();
        assertThrows(IllegalArgumentException.class, () -> stringDocument.getStringByNumber(0));
    }

    @Test
    void getLineByNumber_negIndexOutOfBoundsUpper() {
        StringDocument stringDocument = createStringDocument1();
        assertThrows(IllegalArgumentException.class, () -> stringDocument.getStringByNumber(4));
    }

    void iteratorDemo() {
        StringDocument document = createStringDocument1();
        for (String string : document) {

        }

        for (Line line : document.getLines()) {

        }

    }




}