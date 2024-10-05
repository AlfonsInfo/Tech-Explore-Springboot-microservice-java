package spring.template.encoding;

import spring.template.encoding.utility.conversion.StringConversion;

//@SpringBootApplication
//@EnableAdminServer
//@EnableAsync
public class EncodingServiceApplication {

	public static void main(String[] args) {
		StringConversion.stringToByte("Hello World");


		//SpringApplication.run(EmailServiceApplication.class, args);
	}

}
