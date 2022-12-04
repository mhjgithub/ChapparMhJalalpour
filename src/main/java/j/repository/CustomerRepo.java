package j.repository;

import j.model.Customer;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Component
@Repository
public interface CustomerRepo extends PagingAndSortingRepository<Customer, Long>, QuerydslPredicateExecutor<Customer> {

    // __________________________________________ get
    Optional<Customer> findByIdMoshtariIs(Long id);

    //    @Override
//    List<Customer> findAll();
    List<Customer> findAllByFN(String fN);

    // __________________________________________ delete
    void deleteAllByfN(String fN);
//    void removeCustomersBy(String name);
    // __________________________________________ بررسی نشده
//    List<Customer> findAllByFirst_name(String name);

//    Optional<Customer> findByName (String name);
//    List<Customer> findAllByAge(Integer age);
//    Page<Customer> findAll(Pageable pageable);
//    void removeCustomersByName(String name);


//    void deleteCustomersByName
//    void deleteIfNameEquals (String name);
}
