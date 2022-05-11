package demo.customer.management.api.ws.service;

import demo.customer.management.api.ws.shared.dto.CustomerDto;

/**
 * @author Ketan_Kankapure
 *
 */
public interface CustomerService {

	CustomerDto createCustomer(CustomerDto customerDto);

	CustomerDto getCustomerById(String customerId);

}
