package demo.customer.management.api.ws.controller;

import static demo.customer.management.api.ws.shared.Constants.DATE_OF_BIRTH;
import static demo.customer.management.api.ws.shared.Constants.FIRST_NAME;
import static demo.customer.management.api.ws.shared.Constants.LAST_NAME;
import static demo.customer.management.api.ws.shared.Constants.VALID_CUSTOMER_ID;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.hateoas.EntityModel;

import demo.customer.management.api.ws.model.request.CustomerDetailsRequestModel;
import demo.customer.management.api.ws.model.response.CustomerDetailsResponseModel;
import demo.customer.management.api.ws.service.CustomerService;
import demo.customer.management.api.ws.shared.dto.CustomerDto;

/**
 * @author Ketan_Kankapure
 *
 */
class CustomerControllerTest {

	@InjectMocks
	CustomerController customerController;

	@Mock
	CustomerService customerService;

	CustomerDetailsRequestModel requestModel;

	CustomerDto customerDto;

	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		customerDto = new CustomerDto();
		customerDto.setCustomerId(VALID_CUSTOMER_ID);
		customerDto.setFirstName(FIRST_NAME);
		customerDto.setLastName(LAST_NAME);
		customerDto.setId(1L);
		customerDto.setDateOfBirth(DATE_OF_BIRTH);

	}

	@Test
	final void testCreateCustomer() {
		when(customerService.createCustomer(any(CustomerDto.class))).thenReturn(customerDto);
		requestModel = new CustomerDetailsRequestModel();
		requestModel.setFirstName(FIRST_NAME);
		requestModel.setLastName(LAST_NAME);
		requestModel.setDateOfBirth(DATE_OF_BIRTH);
		EntityModel<CustomerDetailsResponseModel> response = customerController.createCustomer(requestModel);
		assertNotNull(response);
		assertNotNull(response.getLinks());
		assertNotNull(response.getContent());
	}

	@Test
	final void testGetCustomerDetails() {
		when(customerService.getCustomerById(anyString())).thenReturn(customerDto);
		CustomerDetailsResponseModel response = customerController.getCustomerDetails(VALID_CUSTOMER_ID);
		assertNotNull(response);
		assertEquals(customerDto.getCustomerId(), response.getCustomerId());
		assertEquals(customerDto.getFirstName(), response.getFirstName());
		assertEquals(customerDto.getLastName(), response.getLastName());
		assertEquals(customerDto.getDateOfBirth(), response.getDateOfBirth());

	}
}
