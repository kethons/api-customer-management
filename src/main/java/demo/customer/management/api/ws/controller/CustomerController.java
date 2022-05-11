package demo.customer.management.api.ws.controller;

import java.time.LocalDate;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import demo.customer.management.api.ws.exception.CustomerServiceException;
import demo.customer.management.api.ws.model.request.CustomerDetailsRequestModel;
import demo.customer.management.api.ws.model.response.CustomerDetailsResponseModel;
import demo.customer.management.api.ws.model.response.error.ErrorMessages;
import demo.customer.management.api.ws.service.CustomerService;
import demo.customer.management.api.ws.shared.dto.CustomerDto;

/**
 * @author Ketan_Kankapure
 * 
 *         Controller for customer resource
 */
@RestController
@RequestMapping("customers")
public class CustomerController {

	@Autowired
	CustomerService customerService;

	@PostMapping(produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE }, consumes = {
			MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	@ResponseStatus(HttpStatus.CREATED)
	public EntityModel<CustomerDetailsResponseModel> createCustomer(
			@RequestBody CustomerDetailsRequestModel customerDetails) {

		// Validate request
		validate(customerDetails);

		// Serve
		CustomerDetailsResponseModel returnValue = new CustomerDetailsResponseModel();
		CustomerDto customerDto = new CustomerDto();
		BeanUtils.copyProperties(customerDetails, customerDto);
		CustomerDto createdCustomer = customerService.createCustomer(customerDto);
		BeanUtils.copyProperties(createdCustomer, returnValue);

		// Link to self
		Link customerLink = WebMvcLinkBuilder
				.linkTo(WebMvcLinkBuilder.methodOn(CustomerController.class).createCustomer(customerDetails))
				.slash(createdCustomer.getCustomerId()).withSelfRel();

		return EntityModel.of(returnValue, customerLink);
	}

	private void validate(CustomerDetailsRequestModel customerDetails) {
		if (areAllFieldsNotPresent(customerDetails)) {
			throw new CustomerServiceException(ErrorMessages.MISSING_REQUIRED_FIELD.getErrorMessage());
		}
		if (customerDetails.getDateOfBirth().isBefore(LocalDate.now().minusYears(100))) {
			throw new CustomerServiceException(ErrorMessages.INVALIDE_DATE_OF_BIRTH.getErrorMessage());
		}
	}

	private boolean areAllFieldsNotPresent(CustomerDetailsRequestModel customerDetails) {
		return customerDetails.getFirstName().isBlank() || customerDetails.getLastName().isBlank()
				|| customerDetails.getDateOfBirth() == null;
	}

	@GetMapping(path = "/{id}", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public CustomerDetailsResponseModel getCustomerDetails(@PathVariable String id) {
		CustomerDetailsResponseModel returnValue = new CustomerDetailsResponseModel();
		CustomerDto customerDto = customerService.getCustomerById(id);
		BeanUtils.copyProperties(customerDto, returnValue);
		return returnValue;
	}

}
