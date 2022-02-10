package com.study.work.work20211219;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Test1 {

	public static void main(String[] args) {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext5.xml");

		Student john = ctx.getBean("s1", Student.class);
		System.out.println(john);

		Student mary = ctx.getBean("s2", Student.class);
		System.out.println(mary.getClass());

		List<Teacher> teachers1 = new ArrayList<Teacher>();
		teachers1.add(ctx.getBean("t1", Teacher.class));
		teachers1.add(ctx.getBean("t2", Teacher.class));
		
		Set<Clazz> maryclass =  mary.getClazzs(); 
		Set<String> teachersnew = new LinkedHashSet<>();
		System.out.println(mary.getName() + "的課程：" + mary.getClazzs());

		teachers1.forEach(t -> {
			List<Clazz> tclass = t.getClazzs().stream()
						.filter(c -> maryclass.contains(c))
						.collect(Collectors.toList());
			if (tclass.size()>0) {
				teachersnew.add(t.getName());
			}
		});
		teachersnew.forEach(System.out::println);
		
	}

}
