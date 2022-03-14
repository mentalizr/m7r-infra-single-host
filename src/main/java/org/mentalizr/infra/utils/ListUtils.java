package org.mentalizr.infra.utils;

import de.arthurpicht.utils.core.assertion.MethodPreconditions;

import java.util.List;

public class ListUtils {

    public static <E> E getFirstElement(List<E> list) {
        MethodPreconditions.assertArgumentNotNull("list", list);
        if (list.size() == 0) throw new IllegalArgumentException("Specified list is empty.");
        return list.get(0);
    }

    public static <E> E getLastElement(List<E> list) {
        MethodPreconditions.assertArgumentNotNull("list", list);
        if (list.size() == 0) throw new IllegalArgumentException("Specified list is empty.");
        return list.get(list.size() - 1);
    }

}
