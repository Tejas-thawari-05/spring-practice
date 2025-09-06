package com.Builder;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		User user = new User.UserBuilder()
							.setEmailId("tejas@gmail.com")
							.setUserId("tejas123")
							.setUserName("Tejas")
							.build();
		
		System.out.println(user);
		
		User user2 = User.UserBuilder.builder()
		.setEmailId("chetan@gmail.com")
		.setUserId("chetan123")
		.setUserName("Chetan")
		.build();
		System.out.println(user2);
		
	}

}
