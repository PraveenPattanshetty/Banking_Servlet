package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import dto.Customer;

public class CustomerDAO {
	EntityManagerFactory factory = Persistence.createEntityManagerFactory("dev");
	EntityManager manager = factory.createEntityManager();
	EntityTransaction transaction = manager.getTransaction();

	public List<Customer> check(String Email) {
		return manager.createQuery("select x from Customer x where Email=?1").setParameter(1, Email).getResultList();
	}

	public List<Customer> check(long Mobile) {
		return manager.createQuery("select x from Customer x where Mobile=?1").setParameter(1, Mobile).getResultList();
	}

	public void save(Customer customer) {
		transaction.begin();
		manager.persist(customer);
		transaction.commit();

	}

	public Customer login(int cust_id) {

		return manager.find(Customer.class, cust_id);
	}

	public void update(Customer customer) {
		transaction.begin();
		manager.merge(customer);
		transaction.commit();
	}
}
