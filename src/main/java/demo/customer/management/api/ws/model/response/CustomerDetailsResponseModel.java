package demo.customer.management.api.ws.model.response;

import java.time.LocalDate;

/**
 * @author Ketan_Kankapure
 *
 */
public class CustomerDetailsResponseModel {

	private String firstName;
	private String lastName;
	private LocalDate dateOfBirth;
	private String customerId;

	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(LocalDate dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
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

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

}
