package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import dto.BankAccount;

public class BankDAO {
	EntityManagerFactory factory = Persistence.createEntityManagerFactory("dev");
	EntityManager manager = factory.createEntityManager();
	EntityTransaction transaction = manager.getTransaction();

	public void save(BankAccount account) {
		transaction.begin();
		manager.persist(account);
		transaction.commit();
	}

	public List<BankAccount> fetchAll() {
		return manager.createQuery("select x from BankAccount x").getResultList();
	}

	public BankAccount find(long mob) {
		return manager.find(BankAccount.class, mob);
	}

	public void update(BankAccount account) {
		transaction.begin();
		manager.merge(account);
		transaction.commit();
	}
}
