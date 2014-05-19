package com.demandcube.githubflow.utils;

import java.util.Comparator;

import org.kohsuke.github.GHIssue;

public enum DateComparator implements Comparator<GHIssue> {
	CREATIONDATECOMPARATOR {
		@Override
		public int compare(GHIssue issue1, GHIssue issue2) {
			return issue1.getCreatedAt().compareTo(issue2.getCreatedAt());
		}
	},

	CLOSUREDATECOMPARATOR {
		@Override
		public int compare(GHIssue issue1, GHIssue issue2) {
			return issue1.getClosedAt().compareTo(issue2.getClosedAt());
		}
	};
}
