package demo.customer.management.api.ws.service.impl;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import demo.customer.management.api.ws.exception.CustomerNotFoundException;
import demo.customer.management.api.ws.exception.CustomerServiceException;
import demo.customer.management.api.ws.io.entity.CustomerEntity;
import demo.customer.management.api.ws.io.repository.CustomerRepository;
import demo.customer.management.api.ws.model.response.error.ErrorMessages;
import demo.customer.management.api.ws.service.CustomerService;
import demo.customer.management.api.ws.shared.Utils;
import demo.customer.management.api.ws.shared.dto.CustomerDto;

/**
 * @author Ketan_Kankapure
 *
 */
@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	CustomerRepository customerRepository;

	@Autowired
	Utils utils;

	@Override
	public CustomerDto createCustomer(CustomerDto customerDto) {

		CustomerEntity storedCustomerDetails = customerRepository.findByFirstNameAndLastNameAndDateOfBirth(
				customerDto.getFirstName(), customerDto.getLastName(), customerDto.getDateOfBirth());
		if (storedCustomerDetails != null) {
			throw new CustomerServiceException(ErrorMessages.RECORD_ALREADY_EXISTS.getErrorMessage());
		}

		CustomerEntity customerEntity = new CustomerEntity();
		BeanUtils.copyProperties(customerDto, customerEntity);
		customerEntity.setCustomerId(utils.generateCustomerId(30));
		storedCustomerDetails = customerRepository.save(customerEntity);
		CustomerDto returnValue = new CustomerDto();
		BeanUtils.copyProperties(storedCustomerDetails, returnValue);

		return returnValue;
	}

	@Override
	public CustomerDto getCustomerById(String customerId) {
		CustomerDto returnValue = new CustomerDto();
		CustomerEntity customerEntity = customerRepository.findByCustomerId(customerId);
		if (customerEntity == null) {
			throw new CustomerNotFoundException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());
		}
		BeanUtils.copyProperties(customerEntity, returnValue);
		return returnValue;
	}

}
