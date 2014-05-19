package com.demandcube.githubflow;

import static spark.Spark.post;

import java.io.IOException;
import java.util.Collection;
import java.util.Date;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.kohsuke.github.GHRepository;

import spark.Request;
import spark.Response;
import spark.Route;
import spark.servlet.SparkApplication;

import com.google.common.base.Predicate;
import com.google.common.collect.Collections2;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

public class Service implements SparkApplication {
	private static final Logger logger = Logger.getLogger(Service.class);

	Test test = new Test();

	public void init() {
		BasicConfigurator.configure();
		logger.debug("hahaha");
		post(new Route("/getRepositories") {

			@Override
			public Object handle(Request request, Response response) {
				// TODO return repos for user
				logger.debug("in getRepositories");
				// String user = request.body();
				JsonArray array = new JsonArray();
				JsonObject object;
				try {
					Collection<GHRepository> repos = test
							.getRepositories("DemandCube");
					logger.debug("Repos --->" + repos);
					for (GHRepository repo : repos) {
						object = new JsonObject();
						object.addProperty("id", repo.getName());
						object.addProperty("name", repo.getName());
						array.add(object);
					}
				} catch (IOException e) {
					e.printStackTrace();
				}

				response.status(200); // 200 OK
				logger.debug(array);
				return array;

			}
		});
		post(new Route("/getStartDate") {
			@Override
			public Object handle(Request request, Response response) {
				// TODO return repos for user
				logger.debug("in getStartDate. tuone sasa	");
				final String repository = request.body();
				Collection<GHRepository> x= test.getRepositories();
				logger.debug("ngapi "+ x.size());
				Date startDate = Collections2
						.filter(x,
								new Predicate<GHRepository>() {

									@Override
									public boolean apply(GHRepository GHRepo) {
										logger.debug("gani --->"
												+ GHRepo.getName());
										return GHRepo.getName().equals(
												repository);
									}
								}).iterator().next().getCreatedAt();

				response.status(200); // 200 OK
				logger.debug("Start Date " + startDate);
				return startDate;
			}
		});

	}
}