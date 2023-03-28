package dto;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

import lombok.Data;

@Entity
@Data
public class BankAccount {

	@Id
	@GeneratedValue(generator = "accno")
	@SequenceGenerator(initialValue = 879546213, allocationSize = 1, sequenceName = "accno", name = "accno")
	long accno;
	String type;
	double amount;
	boolean status;
	double acclimit;

	@ManyToOne
	Customer customer;

	@OneToMany(cascade = CascadeType.ALL)
	List<BankTransaction> transactions;
}
