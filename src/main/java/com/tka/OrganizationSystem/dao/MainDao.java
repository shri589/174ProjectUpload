package com.tka.OrganizationSystem.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tka.OrganizationSystem.entity.Country;
import com.tka.OrganizationSystem.entity.Employee;

@Repository
public class MainDao {

    @Autowired
    SessionFactory factory;

    public String addCountry(Country c) {
        Session session = null;
        Transaction tx = null;
        String msg = null;

        try {
            session = factory.openSession();
            tx = session.beginTransaction();
            session.persist(c);
            tx.commit();
            msg = "Country is added...";
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }

        return msg;
    }

    public String updateCountry(int id, Country c) {
        Session session = null;
        Transaction tx = null;
        String msg = null;

        try {
            session = factory.openSession();
            tx = session.beginTransaction();

            Country country = session.get(Country.class, id);
            if (country != null) {
                country.setCname(c.getCname());
                session.merge(country);
                tx.commit();
                msg = "Country is updated...";
            } else {
                msg = "Country not found with id: " + id;
            }
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }

        return msg;
    }

    public String deleteCountry(int id) {
        Session session = null;
        Transaction tx = null;
        String msg = null;

        try {
            session = factory.openSession();
            tx = session.beginTransaction();

            Country country = session.get(Country.class, id);
            if (country != null) {
                session.delete(country);
                tx.commit();
                msg = "Country is deleted...";
            } else {
                msg = "Country not found with id: " + id;
            }
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }

        return msg;
    }

    public Country getCountryById(int id) {
        Session session = null;
        Country country = null;
        try {
            session = factory.openSession();
            country = session.get(Country.class, id);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return country;
    }


    public List<Country> getAllCountries() {
        Session session = null;
        List<Country> countries = null;
        try {
            session = factory.openSession();
            countries = session.createQuery("FROM Country", Country.class).list();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return countries;
    }

    public String addEmployee(Employee emp) {
		Session session=null;
		Transaction tx=null;
		String msg=null;		
		try {
			session=factory.openSession();
			tx=session.beginTransaction();			
			session.persist(emp);
			tx.commit();			
			msg="Employee Addedd Successully..";			
		}catch (Exception e) {
			if(tx!=null) {
				tx.rollback();
			}
			e.printStackTrace();
		}finally {
			if(session!=null) {
				session.close();
			}
		}
		return msg;
	}
    public String updateEmployee(Employee emp) {
		Session session=null;
		Transaction tx=null;
		String msg = null ;
		
		try {
			
			session=factory.openSession();
			tx=session.beginTransaction();
			Employee employee= session.get(Employee.class, emp.getId());
			
			employee.setName(emp.getName());
			employee.setCountry(emp.getCountry());
			employee.setCreatedby(emp.getCreatedby());
			employee.setCreateddtm(emp.getCreateddtm());
			employee.setUpdatedby(emp.getUpdatedby());
			employee.setUpdateddtm(emp.getUpdateddtm());
			employee.setDepartment(emp.getDepartment());
			employee.setStatus(emp.getStatus());
			employee.setPhoneno(emp.getPhoneno());
			employee.setEmailid(emp.getEmailid());
			employee.setSalary(emp.getSalary());
			
			session.update(employee);
			tx.commit();
			msg = "Employee Updatated Successfully...";
			
		}catch (Exception e) {
			if(tx!=null) {
				tx.rollback();
			}
			e.printStackTrace();
			 
		}finally {
			
			if(session!=null) {
				session.close();
			}
			
		}
		
		
		
		return msg;
	}

	public String deleteEmployee(int id) {
		
		
		
		Session session=null;
		Transaction tx=null;
		String msg=null;
		
		try {
			
			session=factory.openSession();
			tx=session.beginTransaction();
			
			Employee emp= session.get(Employee.class, id);
			session.remove(emp);
			tx.commit();
			msg="Employee is Deleted...";
			
		}catch (Exception e) {
			if(tx!=null) {
				tx.rollback();
			}
			e.printStackTrace();
			
		}finally {
			
			if(session!=null) {
				session.close();
			}
			
		}
		return msg;
	}

	public List<Employee> getAllEmployee() {
		Session session=null;
		Transaction tx=null;
		List<Employee> list=null;
		String hqlQuey="from Employee";		
		try {
			session=factory.openSession();
			tx=session.beginTransaction();
			Query<Employee> query= 
			session.createQuery(hqlQuey,Employee.class);
			list= query.list();
			tx.commit();
		}catch (Exception e) {
				if(tx!=null) {
					tx.rollback();
				}
				e.printStackTrace();
				
			}finally {
				
				if(session!=null) {
					session.close();
				}
			}	
		return list;
	}

	public Employee getParticularById(int id) {
		
		Session session=null;
		Transaction tx=null;
		Employee emp=null;
		try {
			session=factory.openSession();
			tx=session.beginTransaction();
		emp= session.get(Employee.class, id);
			tx.commit();
		}catch (Exception e) {
			if(tx!=null) {
				tx.rollback();
			}
			e.printStackTrace();
			
		}finally {
			if(session!=null) {
				session.close();
			}
		}	
		return emp;
	}

	public List<Employee> getEmployeeByStatus(String status) {
		Session session=null;
		Transaction tx=null;
		List<Employee> list=null;
		String hqlQuey="from Employee where status=:mystatus";		
		try {
			session=factory.openSession();
			tx=session.beginTransaction();
			Query<Employee> query= 
			session.createQuery(hqlQuey,Employee.class);
			
			query.setParameter("mystatus", status);
			
			list= query.list();
			tx.commit();
		}catch (Exception e) {
				if(tx!=null) {
					tx.rollback();
				}
				e.printStackTrace();
				
			}finally {
				
				if(session!=null) {
					session.close();
				}
			}	
		return list;	
		
	}

	public Employee loginCheck(Employee emp) {
		Session session=null;
		Transaction tx=null;
		
		Employee employee= null;
		String hqlQuey="from Employee where emailid=:myemailid and phoneno=:myphoneno";		
		try {
			session=factory.openSession();
			tx=session.beginTransaction();
		Query<Employee> query=	session.createQuery(hqlQuey,Employee.class);
		query.setParameter("myemailid", emp.getEmailid());
		query.setParameter("myphoneno", emp.getPhoneno());
		employee=query.uniqueResult();
		tx.commit();
		}catch (Exception e) {
			if(tx!=null) {
				tx.rollback();
			}
			e.printStackTrace();
			
		}finally {
			
			if(session!=null) {
				session.close();
			}
		}
		return employee;
	}

	public List<Employee> getEmployeeBySalary(double salary) {
		Session session=null;
		Transaction tx=null;
		List<Employee> list=null;
		String hqlQuey="from Employee where salary=:mysalary";		
		try {
			session=factory.openSession();
			tx=session.beginTransaction();
			Query<Employee> query= 
			session.createQuery(hqlQuey,Employee.class);
			
			query.setParameter("mysalary", salary);
			
			list= query.list();
			tx.commit();
		}catch (Exception e) {
				if(tx!=null) {
					tx.rollback();
				}
				e.printStackTrace();
				
			}finally {
				
				if(session!=null) {
					session.close();
				}
			}	
		return list;	
		
	}
	
	
	public List<Employee> getEmployeeBySalaryRange(double minSalary, double maxSalary) {
        Session session = null;
        Transaction tx = null;
        List<Employee> list = null;
        String hqlQuery = "from Employee where salary between :minSalary and :maxSalary";
        try {
            session = factory.openSession();
            tx = session.beginTransaction();
            Query<Employee> query = session.createQuery(hqlQuery, Employee.class);

            query.setParameter("minSalary", minSalary);
            query.setParameter("maxSalary", maxSalary);

            list = query.list();
            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return list;
    }

	

	
}
	
	
	
	
	
	
	
	
	
	
	
	
	



