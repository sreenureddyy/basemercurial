package com.sree.base.service.webservice;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public final class Client {

	private Client() {
	}

	public static void main(String args[]) throws Exception {
		// START SNIPPET: client
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
				new String[] { "client-beans.xml" });

		MyService client = (MyService) context.getBean("helloClient");

		String response = client.sayHello("Sree");
		System.out.println("Response: " + response);
		System.exit(0);
		// END SNIPPET: client
	}
}
