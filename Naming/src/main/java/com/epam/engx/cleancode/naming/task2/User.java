package com.epam.engx.cleancode.naming.task2;

import java.util.Arrays;
import java.util.Date;

public class User {

	private Date dateOfBirth;

	private String name;

	private boolean isAdmin;

	private User[] subordinateArray;

	private int rating;

	public User(String name) {
		super();
		this.name = name;
	}

	@Override
	public String toString() {
		return "User [dBirth=" + dateOfBirth + ", name=" + name + ", admin=" + isAdmin + ", subordinateArray="
				+ Arrays.toString(subordinateArray) + ", iRating=" + rating + "]";
	}

}
