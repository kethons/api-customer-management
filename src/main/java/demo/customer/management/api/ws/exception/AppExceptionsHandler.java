package demo.customer.management.api.ws.exception;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import demo.customer.management.api.ws.model.response.error.ErrorMessage;

/**
 * @author Ketan_Kankapure
 *
 *         Central exception handler class
 *
 */
@ControllerAdvice
public class AppExceptionsHandler {

	/**
	 * For simplicity, INFO logging level has been used to trace via console only in
	 * this class/central exception handler
	 */
	private static final Logger logger = LoggerFactory.getLogger(AppExceptionsHandler.class);

	@ExceptionHandler(value = { CustomerNotFoundException.class })
	public ResponseEntity<Object> handleUserServiceException(CustomerNotFoundException ex, WebRequest request) {
		ErrorMessage errorMessage = new ErrorMessage(new Date(), ex.getMessage());
		logger.info(errorMessage.getMessage());
		return new ResponseEntity<>(errorMessage, new HttpHeaders(), HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(value = { CustomerServiceException.class })
	public ResponseEntity<Object> handleUserServiceException(CustomerServiceException ex, WebRequest request) {
		ErrorMessage errorMessage = new ErrorMessage(new Date(), ex.getMessage());
		logger.info(errorMessage.getMessage());
		return new ResponseEntity<>(errorMessage, new HttpHeaders(), HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(value = { Exception.class })
	public ResponseEntity<Object> handleOtherExceptions(Exception ex, WebRequest request) {
		ErrorMessage errorMessage = new ErrorMessage(new Date(), ex.getMessage());
		logger.info(errorMessage.getMessage());
		return new ResponseEntity<>(errorMessage, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
