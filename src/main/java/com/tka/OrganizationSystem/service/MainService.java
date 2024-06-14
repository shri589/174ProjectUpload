package com.tka.OrganizationSystem.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tka.OrganizationSystem.dao.MainDao;
import com.tka.OrganizationSystem.entity.Country;
import com.tka.OrganizationSystem.entity.Employee;

@Service
public class MainService {
	
	
	@Autowired
	MainDao dao;
	

	public String addCountry(Country c) {		
		
		String msg= dao.addCountry(c);
		
		if(Objects.isNull(msg)) {
			msg="Country is not addedd...";
		}
		
		return msg;
		
		
	}


	public String updateCountry(int id, Country c) {
		
		String msg= dao.updateCountry(id,c);
		if(Objects.isNull(msg)) {
			msg="Country is not Updatated..";
		}
		
		return msg;
		
	}
	public String deleteCountry(int id) {
		String msg = dao.deleteCountry(id);
		if(Objects.isNull(msg)) {
			msg = "Country is not deleted..";
		}
		return msg;
	}

	
	public Country getCountryById(int id) {
	    Country country = dao.getCountryById(id);
	    if (Objects.isNull(country)) {
	        return null;
	    }
	    return country;
	}

	public List<Country> getAllCountries() {
	    List<Country> countries = dao.getAllCountries();
	    if (Objects.isNull(countries) || countries.isEmpty()) {
	        return null;
	    }
	    return countries;
	}


	public String addEmployee(Employee emp) {
		// TODO Auto-generated method stub
		
		String msg=dao.addEmployee(emp);
		
		if(Objects.isNull(msg)) {
			msg="Record is not be addedd...";
		}
		return msg;		
		
	}
public String updateEmployee(Employee emp) {
		
		String msg=dao.updateEmployee(emp);
		if(Objects.isNull(msg)) {
			msg="Record is not be Updatated";
		}
		
		return msg;
	}

	public String deleteEmployee(int id) {
		
		String msg=dao.deleteEmployee(id);
		if(Objects.isNull(msg)) {
			msg="Record is not Deleted..";
		}		
		return msg;
	}
	
	public List<Employee> getAllEmployee() {
		
		List<Employee> list=
				dao.getAllEmployee();
		
		if(Objects.isNull(list)) {
			list=null;
		}		
		return list;
	}


	public Employee getParticularById(int id) {
		
		Employee emp= dao.getParticularById(id);
		if(Objects.isNull(emp)) {
			emp=null;
		}
		
		return emp;
	}


	public List<Employee> getEmployeeByStatus(String status) {
		List<Employee> list= dao.getEmployeeByStatus(status);
		if(Objects.isNull(list)) {
			list=null;
		}
		return list;
	}


	public HashMap loginCheck(Employee emp) {
		Employee e= dao.loginCheck(emp);
		HashMap map= new HashMap();
		if(Objects.nonNull(e)) {
			map.put("msg", "Invalid User");
			map.put("user", e);
		}else {
			map.put("msg", "Valid User");
			map.put("user", e);
		}
		return map;
	}
	
	public List<Employee> getEmployeeBySalary(double salary) {
		List<Employee> list= dao.getEmployeeBySalary(salary);
		if(Objects.isNull(list)) {
			list=null;
		}
		return list;
	}


	public List<Employee> getEmployeesBySalaryRange(double minSalary, double maxSalary) {
        List<Employee> list = dao.getEmployeeBySalaryRange(minSalary, maxSalary);
        if (Objects.isNull(list)) {
            list = new ArrayList<>(); 
        }
        return list;
    }



	
}

	


	

