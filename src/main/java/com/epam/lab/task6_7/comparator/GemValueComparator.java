package com.epam.lab.task6_7.comparator;

import com.epam.lab.task3.model.Gem;

import java.util.Comparator;

public class GemValueComparator implements Comparator<Gem> {
    @Override
    public int compare(Gem o1, Gem o2) {
        return o1.getValue() - o2.getValue();
    }
}
