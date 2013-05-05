package com.bb.neighbor.tribe.compare;

import java.util.Comparator;

import com.bb.neighbor.tribe.model.Tribe;

public class ComparatorTribe implements Comparator<Tribe> {
	@Override
	public int compare(Tribe t1, Tribe t2) {
		return t1.getLetters().compareTo(t2.getLetters());
	}
}
