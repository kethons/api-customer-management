package demo.customer.management.api.ws.io.repository;

import java.time.LocalDate;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import demo.customer.management.api.ws.io.entity.CustomerEntity;

/**
 * @author Ketan_Kankapure
 *
 */
@Repository
public interface CustomerRepository extends CrudRepository<CustomerEntity, Long> {

	CustomerEntity findByFirstNameAndLastNameAndDateOfBirth(String firstName, String lastName, LocalDate dateOfBirth);

	CustomerEntity findByCustomerId(String id);

}
