package org.examples.repository;

import org.examples.domain.Employee;
import org.examples.domain.Employee_Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface EmployeeAdressRepository extends JpaRepository<Employee_Address, Integer>{

	@Query("select e from Employee_Address e where e.employee = :employee")
	public Employee_Address findAddressByEmployee(@Param("employee") Employee employee);
}
