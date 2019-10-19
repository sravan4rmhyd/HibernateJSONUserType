package org.examples;

import org.examples.domain.Employee;
import org.examples.domain.Employee_Address;
import org.examples.repository.EmployeeAdressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {

	@Autowired
	private EmployeeAdressRepository employeeAdressRepository;

	public void saveEmployee() {
		Employee employee = new Employee();
		employee.setEmpName("JIP");

		Employee_Address employeeAddress = new Employee_Address();
		employeeAddress.setStreet("Street 1");
		employeeAddress.setCity("City 1");
		employeeAddress.setCountry("Country 1");
		employeeAddress.setState("State 1");

		employee.setEmployeeAddress(employeeAddress);
		employeeAddress.setEmployee(employee);

		employeeAdressRepository.save(employeeAddress);
		System.out.println("Employee and Employee Address saved successfully!!");
	}
}
