package app.core.services;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.core.entities.Employee;
import app.core.entities.Job;
import app.core.exceptions.CompanyException;
import app.core.repositories.EmployeeRepository;
import app.core.repositories.JobRepository;

@Service
@Transactional
public class CompanyService {

	@Autowired
	private EmployeeRepository employeeRepository;
	
	@Autowired
	private JobRepository jobRepository;
	
	public void addEmployee(Employee employee) throws CompanyException {
		if (employee != null) {
			try {
				employeeRepository.save(employee);
				System.out.println("addEmployee: success");
			} catch (IllegalArgumentException e) {
				throw new CompanyException("CompanyService fail to addEmployee", e);
			}
		} else {
			System.out.println("addEmployee: employee in null");
		}
	}
	
	public Employee findEmployee(Long id) throws CompanyException {
		Optional<Employee> optEmployee = null;
		try {
			optEmployee = employeeRepository.findById(id);
		} catch (IllegalArgumentException e) {
			throw new CompanyException("CompanyService fail to findEmployee by id", e);
		}
		if (optEmployee.isPresent()) {
			System.out.println("findEmployee: succes");
			return optEmployee.get();
		}
		System.out.println("findEmployee: no employee with id=" + id + " found");
		return null;
	}
	
	public List<Employee> findEmployees(String name) throws CompanyException {
		List<Employee> employees = null;
		try {
			employees = employeeRepository.findAllByName(name);
		} catch (IllegalArgumentException e) {
			throw new CompanyException("CompanyService fail to findEmployees by name", e);
		}
		if (employees != null && !employees.isEmpty()) {
			System.out.println("findEmployees(name): succes");
			return employees;
		}
		System.out.println("findEmployees(name): no employee with name=" + name + " found");
		return null;
	}
	
	public List<Employee> findEmployees() throws CompanyException {
		List<Employee> employees = null;
		try {
			employees = employeeRepository.findAll();
		} catch (IllegalArgumentException e) {
			throw new CompanyException("CompanyService fail to findEmployees", e);
		}
		if (employees != null && !employees.isEmpty()) {
			System.out.println("findEmployees: success");
			return employees;
		}
		System.out.println("findEmployees: no employees found");
		return null;
	}
	
	public List<Job> findJobs() throws CompanyException {
		List<Job> jobs = null;
		try {
			jobs = jobRepository.findAll();
		} catch (IllegalArgumentException e) {
			throw new CompanyException("CompanyService fail to findJobs", e);
		}
		if (jobs != null && !jobs.isEmpty()) {
			System.out.println("findJobs: success");
			return jobs;
		}
		System.out.println("findJobs: no jobs found");
		return null;
	}
	
	public List<Job> finJobs(Date endDate) throws CompanyException {
		List<Job> jobs = null;
		try {
			jobs = jobRepository.findByEndDate(endDate);
		} catch (IllegalArgumentException e) {
			throw new CompanyException("CompanyService fail to findJobs by endDate", e);
		}
		if (jobs != null && !jobs.isEmpty()) {
			System.out.println("findJobs(date): success");
			return jobs;
		}
		System.out.println("findJobs(date): no jobs found");
		return null;
	}
	
	public List<Job> findJobsBetween(Date date1, Date date2) throws CompanyException {
		List<Job> jobs = null;
		try {
			jobs = jobRepository.findByEndDateBetween(date1, date2);
		} catch (IllegalArgumentException e) {
			throw new CompanyException("CompanyService fail to findJobs between dates", e);
		}
		if (jobs != null && !jobs.isEmpty()) {
			System.out.println("findJobsBetween(date1, date2): success");
			return jobs;
		}
		System.out.println("findJobsBetween(date1, date2): no jobs found");
		return null;
	}
}
