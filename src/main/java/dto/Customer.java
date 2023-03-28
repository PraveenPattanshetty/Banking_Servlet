package dto;

import java.sql.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

import lombok.Data;

@Entity
@Data
public class Customer {

	@Id
	@SequenceGenerator(initialValue = 12110111, allocationSize = 1, sequenceName = "custid", name = "custid")
	@GeneratedValue(generator = "custid")
	int cust_id;
	String Name;
	String Email;
	String Password;
	long Mobile;
	Date DOB;
	String Gender;

	@OneToMany
	List<BankAccount> accounts;
	
}
