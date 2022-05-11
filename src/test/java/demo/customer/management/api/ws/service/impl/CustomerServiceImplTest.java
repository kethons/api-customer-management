package demo.customer.management.api.ws.service.impl;

import static demo.customer.management.api.ws.shared.Constants.DATE_OF_BIRTH;
import static demo.customer.management.api.ws.shared.Constants.FIRST_NAME;
import static demo.customer.management.api.ws.shared.Constants.ID;
import static demo.customer.management.api.ws.shared.Constants.INVALID_CUSTOMER_ID;
import static demo.customer.management.api.ws.shared.Constants.LAST_NAME;
import static demo.customer.management.api.ws.shared.Constants.VALID_CUSTOMER_ID;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import demo.customer.management.api.ws.exception.CustomerNotFoundException;
import demo.customer.management.api.ws.exception.CustomerServiceException;
import demo.customer.management.api.ws.io.entity.CustomerEntity;
import demo.customer.management.api.ws.io.repository.CustomerRepository;
import demo.customer.management.api.ws.shared.Utils;
import demo.customer.management.api.ws.shared.dto.CustomerDto;

/**
 * @author Ketan_Kankapure
 *
 */
class CustomerServiceImplTest {

	@InjectMocks
	CustomerServiceImpl customerService;

	@Mock
	CustomerRepository customerRepository;

	@Mock
	Utils utils;

	private CustomerEntity customerEntity;

	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		customerEntity = new CustomerEntity();
		customerEntity.setId(1L);
		customerEntity.setFirstName(FIRST_NAME);
		customerEntity.setLastName(LAST_NAME);
		customerEntity.setDateOfBirth(DATE_OF_BIRTH);
		customerEntity.setCustomerId(VALID_CUSTOMER_ID);
	}

	@Test
	final void testCreateCustomer() {

		when(customerRepository.findByFirstNameAndLastNameAndDateOfBirth(anyString(), anyString(),
				any(LocalDate.class))).thenReturn(null);
		when(utils.generateCustomerId(anyInt())).thenReturn(VALID_CUSTOMER_ID);
		when(customerRepository.save(any(CustomerEntity.class))).thenReturn(customerEntity);

		CustomerDto customerDto = new CustomerDto();
		customerDto.setCustomerId(VALID_CUSTOMER_ID);
		customerDto.setFirstName(FIRST_NAME);
		customerDto.setLastName(LAST_NAME);
		customerDto.setId(1L);
		customerDto.setDateOfBirth(DATE_OF_BIRTH);

		CustomerDto storedCustomerDetails = customerService.createCustomer(customerDto);
		assertNotNull(storedCustomerDetails);
		assertEquals(customerEntity.getFirstName(), storedCustomerDetails.getFirstName());
		assertEquals(customerEntity.getLastName(), storedCustomerDetails.getLastName());
		assertNotNull(storedCustomerDetails.getCustomerId());
		verify(utils, times(1)).generateCustomerId(30);
		verify(customerRepository, times(1)).save(any(CustomerEntity.class));
	}

	@Test
	final void testCreateCustomer_WhenCustomerAlreadyExists() {

		when(customerRepository.findByFirstNameAndLastNameAndDateOfBirth(anyString(), anyString(),
				any(LocalDate.class))).thenReturn(customerEntity);

		CustomerDto customerDto = new CustomerDto();
		customerDto.setCustomerId(VALID_CUSTOMER_ID);
		customerDto.setFirstName(FIRST_NAME);
		customerDto.setLastName(LAST_NAME);
		customerDto.setId(1L);
		customerDto.setDateOfBirth(DATE_OF_BIRTH);
		assertThrows(CustomerServiceException.class, () -> customerService.createCustomer(customerDto));

	}

	@Test
	final void testGetCustomerById() {

		when(customerRepository.findByCustomerId(anyString())).thenReturn(customerEntity);
		CustomerDto customerDto = customerService.getCustomerById(VALID_CUSTOMER_ID);
		assertNotNull(customerDto);
		assertEquals(FIRST_NAME, customerDto.getFirstName());
		assertEquals(LAST_NAME, customerDto.getLastName());
		assertEquals(ID, customerDto.getId());
		assertEquals(VALID_CUSTOMER_ID, customerDto.getCustomerId());
		assertEquals(DATE_OF_BIRTH, customerDto.getDateOfBirth());

	}

	@Test
	final void testGetCustomerById_CustomerNotFoundException() {
		when(customerRepository.findByCustomerId(anyString())).thenReturn(null);
		assertThrows(CustomerNotFoundException.class, () -> customerService.getCustomerById(INVALID_CUSTOMER_ID));
	};
}
