package demo.customer.management.api.ws.exception;

/**
 * @author Ketan_Kankapure
 *
 */
public class CustomerNotFoundException extends RuntimeException {

	private static final long serialVersionUID = -7293071309664599803L;

	public CustomerNotFoundException(String message) {
		super(message);
	}

}
