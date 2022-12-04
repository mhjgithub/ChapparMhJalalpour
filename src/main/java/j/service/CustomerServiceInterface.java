package j.service;

import j.model.Customer;

import java.util.List;

public interface CustomerServiceInterface {
    public List<Customer> getAllCustomers();

    public Customer getCustomerById(Long id);

    List<Customer> getCustomersByFirst_name(String first_name);

    List<Customer> filter(int size, int page, String customerFirstName, String customerFamily);

    // ____________________________________ مرتبط با put
    public Customer addCustomer(Customer customer);

    public Customer addCustomer(Long idMoshtari, String first_name, String ln, String email);

    // ____________________________________ مرتبط با post
    void updateCustomer(Long thisCustomerId, String newFirstName, String newLastName);
    // ____________________________________ مرتبط با delete
    String delAllCustomers();

    Customer delThisCustomer(Long id);

    String delAllCustomersByThisFirstName(String firstName);

// __________________________________________ بررسی نشده

//    Page<Customer> getAll(int s, int n);
//    public Customer removeCustomer (Long id);
//    List<Customer> getCustomer(String name);
//    int removaAllByName(String stName);
//    void addCustomer(Customer customer, MultipartFile file);
//    List<Customer> filter (int pageSize , int pageNumber , String customerFirst_name , Integer age , String customerFamilyName);
//    List<Customer> filter (int pageSize , int pageNumber , Predicate pridicate);
//
//    List<Customer> filter(int size, int page, String customerFirst_name, Integer age, String customerFamilyName, String order);

}
