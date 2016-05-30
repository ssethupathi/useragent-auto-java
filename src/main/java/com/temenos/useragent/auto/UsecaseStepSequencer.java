package com.temenos.useragent.auto;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class UsecaseStepSequencer {

	private List<UsecaseStep> unsequencedSteps;

	public UsecaseStepSequencer(List<UsecaseStep> steps) {
		unsequencedSteps = steps;
	}

	public List<UsecaseStep> sequence() {
		Collections.sort(unsequencedSteps, new UsecaseStepComparator());
		return unsequencedSteps;
	}

	private static class UsecaseStepComparator implements
			Comparator<UsecaseStep> {
		public int compare(UsecaseStep o1, UsecaseStep o2) {
			return o1.sequenceId() - o2.sequenceId();
		}
	}
}
