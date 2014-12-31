package com.demandcube.githubflow;

import java.io.IOException;

import org.kohsuke.github.GHIssue;
import org.kohsuke.github.GHIssueState;
import org.kohsuke.github.GHRepository;
import org.kohsuke.github.GitHub;

public class Main {
	private static final String repo = "DemandCube/NeverwinterDP";
	public static void main(String[] args) {
		try {
			GHRepository repository = GitHub.connectUsingPassword("artfullyContrived", "hgfj!2010").getRepository(repo);
			for (GHIssue issue : repository.getIssues(GHIssueState.CLOSED)) {
				System.out.println("closed by ");
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
