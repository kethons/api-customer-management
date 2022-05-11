package demo.customer.management.api.ws.model.response.error;

/**
 * @author Ketan_Kankapure
 *
 */
public enum ErrorMessages {

	MISSING_REQUIRED_FIELD("Missing required field. All fields are required"),
	INVALIDE_DATE_OF_BIRTH("Invalid date of birth"),
	RECORD_ALREADY_EXISTS("Customer already exists"), 
	INTERNAL_SERVER_ERROR("Internal server error"),
	NO_RECORD_FOUND("Customer with provided id is not found");

	private String errorMessage;

	ErrorMessages(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
}
