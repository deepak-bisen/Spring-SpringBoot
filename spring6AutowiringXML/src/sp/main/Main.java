package sp.main;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import sp.beans.Student;

public class Main {
	public static void main(String[] args) {
		String config_path = "sp/resources/ApplicationContext.xml";
		ApplicationContext context = new ClassPathXmlApplicationContext(config_path);

		Student std = (Student) context.getBean("stdId");
		std.display();
		
	}
}
