package com.bb.neighbor.tribe.compare;

import java.util.Comparator;

import com.bb.neighbor.tribe.model.TribeTopic;

public class ComparatorTribeTopicTop implements Comparator<TribeTopic> {
	@Override
	public int compare(TribeTopic t1, TribeTopic t2) {
		if (t1.getIsTop() == t2.getIsTop()) {
			return 0;
		} else if ((t1.getIsTop() == null ? 0 : t1.getIsTop()) > (t2.getIsTop() == null ? 0 : t2.getIsTop())) {
			return -1;
		} else {
			return 1;
		}
	}
}
