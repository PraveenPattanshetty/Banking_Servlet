package dto;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class BankTransaction {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int id;
	double deposit;
	double withdraw;
	double balance;
	LocalDateTime dateTime;
}
