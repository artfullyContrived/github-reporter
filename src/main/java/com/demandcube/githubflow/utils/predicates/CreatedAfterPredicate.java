package com.demandcube.githubflow.utils.predicates;

import java.util.Date;

import org.kohsuke.github.GHIssue;

import com.google.common.base.Predicate;

public class CreatedAfterPredicate implements Predicate<GHIssue> {

	private Date date;

	public CreatedAfterPredicate(Date date) {
		super();
		this.date = date;
	}

	@Override
	public boolean apply(GHIssue issue) {
		return issue.getCreatedAt().after(date);
	}
}