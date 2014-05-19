package com.demandcube.githubflow.utils;

import java.io.IOException;

import org.kohsuke.github.GHUser;

import com.google.common.base.Function;
import com.google.common.base.Strings;

public enum UserFunctions implements Function<GHUser, String> {

	NameFunction {

		@Override
		public String apply(GHUser user) {
			String name = null;
			try {
				if (!Strings.isNullOrEmpty(user.getName())) {
					name = user.getName();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			return name;
		}
	},

	EmailFunction {

		@Override
		public String apply(GHUser user) {
			String address = null;
			try {
				if (!Strings.isNullOrEmpty(user.getEmail())
						&& user.getEmail().contains("@")) {
					address = user.getEmail();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			return address;
		}
	}

}
