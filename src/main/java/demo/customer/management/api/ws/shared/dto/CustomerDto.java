package demo.customer.management.api.ws.shared.dto;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * @author Ketan_Kankapure
 *
 */
public class CustomerDto implements Serializable {

	private static final long serialVersionUID = 4038823218599686920L;

	private long id;
	private String firstName;
	private String lastName;
	private LocalDate dateOfBirth;
	private String customerId;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(LocalDate dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	@Override
	public String toString() {
		return new StringBuilder(firstName).append(lastName).append(dateOfBirth).toString();
	}

}
