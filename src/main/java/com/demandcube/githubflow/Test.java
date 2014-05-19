package com.demandcube.githubflow;

import java.io.IOException;
import java.util.Collection;
import java.util.List;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.kohsuke.github.GHIssue;
import org.kohsuke.github.GHIssueState;
import org.kohsuke.github.GHRepository;
import org.kohsuke.github.GitHub;

import com.demandcube.githubflow.utils.UserFunctions;
import com.demandcube.githubflow.utils.Utils;
import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import com.google.common.collect.Collections2;
import com.google.common.collect.Lists;

public class Test implements GitFlow {

	private static final String repository = "DemandCube/NeverwinterDP";
	static Logger logger = Logger.getLogger("Test");
	private static GHRepository repo;
	private static Collection<GHRepository> repos;

	// We want a report of issues, opened, issues closed, pull requests opened
	// and closed during the past week and by whom.
	public static void main(String[] args) throws IOException {
		BasicConfigurator.configure();
		repo = GitHub.connectUsingPassword(Utils.getUserName(),
				Utils.getPassword()).getRepository(repository);
		Collection<String> transform = Collections2.filter(Collections2
				.transform(repo.getCollaborators(), UserFunctions.EmailFunction),
				Predicates.notNull());
		logger.debug("recipients " + transform);
	}

	@Override
	public List<GHIssue> getIssuesInState(GHRepository repository,
			GHIssueState state) throws IOException {

		return repository.getIssues(state);
	}

	@Override
	public List<GHIssue> getAllIssues(GHRepository repo) throws IOException {
		List<GHIssue> issues = Lists.newArrayList();
		for (GHIssueState ghIssue : GHIssueState.values()) {
			issues.addAll(repo.getIssues(ghIssue));
		}

		return issues;
	}

	@Override
	public List<GHIssue> getIssuesActioned(List<GHIssue> collection,
			Predicate<GHIssue> datePredicate) {

		return (List<GHIssue>) Collections2.filter(collection, datePredicate);
	}

	public void init() throws IOException {
		this.repo = GitHub.connectAnonymously().getRepository(repository);
	}

	@Override
	public Collection<GHRepository> getRepositories(String user)
			throws IOException {
		logger.debug("User ------>" + user);
		// TODO catch java.io.FileNotFoundException:
		// https://api.github.com/orgs/mjuaji
		Collection<GHRepository> repos = GitHub.connectAnonymously()
				.getOrganization(user).getRepositories().values();
		this.repos = repos;
		logger.debug("Repos ------>" + repos.size());
		return repos;
	}

	public Collection<GHRepository> getRepositories() {

		return this.repos;
	}

	
}