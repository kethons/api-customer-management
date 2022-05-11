package demo.customer.management.api.ws.exception;

/**
 * @author Ketan_Kankapure
 */
public class CustomerServiceException extends RuntimeException {

	private static final long serialVersionUID = -8230137866480742838L;

	public CustomerServiceException(String message) {
		super(message);
	}

}
