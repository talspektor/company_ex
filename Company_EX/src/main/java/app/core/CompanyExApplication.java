package app.core;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import app.core.entities.Employee;
import app.core.entities.Job;
import app.core.services.CompanyService;

@SpringBootApplication
public class CompanyExApplication {

	public static void main(String[] args) {
		ApplicationContext applicationContext = SpringApplication.run(CompanyExApplication.class, args);
		
		CompanyService companyService = applicationContext.getBean(CompanyService.class);
		
		try {
			List<Job> jobs1 = new ArrayList<Job>();
			jobs1.add(new Job("make cofee", new Date(2020, 10, 8)));
			jobs1.add(new Job("wash dishess", new Date(2020, 9, 1)));
			
			Employee employee1 = new Employee("David", 20000, jobs1);
			companyService.addEmployee(employee1);
			
			List<Job> jobs2 = new ArrayList<Job>();
			jobs2.add(new Job("make cofee", new Date(2019, 1, 4)));
			jobs2.add(new Job("wash dishess", new Date(2017, 5, 14)));
			
			Employee employee2 = new Employee("Avi", 20000, jobs2);
			companyService.addEmployee(employee2);
			
			System.out.println("======================");
			System.out.println("FIND EMPLOEEY BY ID");
			
			// return single employee by id
			Employee employee = companyService.findEmployee(2L);
			System.out.println(employee);
			
			System.out.println("======================");
			System.out.println("FIND ALL EMPLOEEYS");
			
			// return all the employees from db
			List<Employee> employees2 = companyService.findEmployees();
			System.out.println(employees2);
			
			System.out.println("======================");
			System.out.println("FIND EMPLOEEY BY NAME");
			
			// return all ampoyees by name
			List<Employee> employees3 = companyService.findEmployees("David");
			System.out.println(employees3);
			
			System.out.println("======================");
			System.out.println("FIND ALL JOBS");
			
			// return all jobs from db
			List<Job> jobs3 = companyService.findJobs();
			System.out.println(jobs3);
			
			System.out.println("======================");
			System.out.println("FIND ALL JOBS BY DATE");
			
			// retrunf all jobs by date
			List<Job> jobs4 = companyService.finJobs(new Date(2017, 5, 14));
			System.out.println(jobs4);
			
			System.out.println("======================");
			System.out.println("FIND ALL JOBS BETWEEN DATES");
			
			List<Job> jobs5 = companyService.findJobsBetween(new Date(2017, 1, 22), new Date(2020, 11, 10));
			System.out.println(jobs5);
		} catch (Exception e) {
			e.printStackTrace();
		}	
	}
}
