package com.neu.hardik.utility;

public class Constants {
	public static final String[] TEAMS = { "Admin", "Accounts", "Engineering", "Customer Support", "Sales", "HR",
			"Business Development" };

	public enum Role {
		ADMIN, LEAD, MANAGER, MEMBER
	}

	public enum Status {
		ASSIGNED("ASSIGNED"), STARTED("STARTED"), PARKED("PARKED"), COMPLETED("COMPLETED");
		private String value;

	    Status(String value) {
	        this.value = value;
	    }

	    public String value() {
	        return value;
	    }
	}
}
