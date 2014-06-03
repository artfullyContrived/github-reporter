package com.demandcube.githubflow.utils.predicates;

import java.util.Date;

import org.kohsuke.github.GHIssue;
import org.kohsuke.github.GHPullRequest;

import com.google.common.base.Predicate;

public class CreatedBetweenPredicate<T> implements Predicate<T> {

	private Date start;
	private Date end;

	public CreatedBetweenPredicate(Date start, Date end) {
		super();
		this.start = start;
		this.end = end;
	}

	@Override
	public boolean apply(T t) {

		if (t instanceof GHIssue) {
			GHIssue issue = (GHIssue) t;
			return issue.getCreatedAt().after(start)
					&& issue.getCreatedAt().before(end);
		} else {
			GHPullRequest pullRequest = (GHPullRequest) t;
			return pullRequest.getCreatedAt().after(start)
					&& pullRequest.getCreatedAt().before(end);
		}
	}
}