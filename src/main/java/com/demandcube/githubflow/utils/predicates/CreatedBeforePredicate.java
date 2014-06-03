package com.demandcube.githubflow.utils.predicates;

import java.util.Date;

import org.kohsuke.github.GHIssue;

import com.google.common.base.Predicate;

public class CreatedBeforePredicate implements Predicate<GHIssue> {

	private Date date;

	public CreatedBeforePredicate(Date date) {
		super();
		this.date = date;
	}

	@Override
	public boolean apply(GHIssue issue) {
		return issue.getCreatedAt().before(date);
	}
}