package com.demandcube.githubflow;

import java.io.IOException;
import java.util.Collection;
import java.util.List;

import org.kohsuke.github.GHIssue;
import org.kohsuke.github.GHIssueState;
import org.kohsuke.github.GHRepository;

import com.google.common.base.Predicate;

public interface GitFlow {

	public List<GHIssue> getIssuesInState(GHRepository repository,
			GHIssueState state) throws IOException;

	public List<GHIssue> getAllIssues(GHRepository repo) throws IOException;

	public Collection<GHRepository> getRepositories(String user) throws IOException;

	public List<GHIssue> getIssuesActioned(List<GHIssue> issues,
			Predicate<GHIssue> datePredicate);
}
