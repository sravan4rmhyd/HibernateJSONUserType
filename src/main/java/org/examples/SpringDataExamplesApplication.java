package org.examples;

import org.examples.domain.Employee;
import org.examples.domain.Employee_Address;
import org.examples.repository.EmployeeAdressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringDataExamplesApplication implements CommandLineRunner{

	/*@Autowired
	private EmployeeRepository employeeRepository;*/
	@Autowired
	private EmployeeAdressRepository adressRepository;
	public static void main(String[] args) {
		SpringApplication.run(SpringDataExamplesApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		//Employee employee = employeeRepository.findById(2).orElseGet(() -> new Employee(1, "sravan", null));
		Employee employee = new Employee(2, null, null);
		Employee_Address address = adressRepository.findAddressByEmployee(employee);
		System.out.println(address);
	}

}
