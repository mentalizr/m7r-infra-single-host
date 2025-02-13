package org.mentalizr.infra.utils;

public class InfraLogicHelper {

    public static boolean allTrue(boolean... value) {
        for (boolean b : value) {
            if (!b) return false;
        }
        return true;
    }

    public static boolean atLeastOneFalse(boolean... value) {
        for (boolean b : value) {
            if (!b) return true;
        }
        return false;
    }

}
