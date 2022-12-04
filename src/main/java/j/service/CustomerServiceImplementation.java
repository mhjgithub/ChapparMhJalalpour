package j.service;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import j.model.Customer;
import j.model.QCustomer;
import j.repository.CustomerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Optional;
@Component
@Service
//@Transactional
public class CustomerServiceImplementation implements CustomerServiceInterface {

    @Autowired
    CustomerRepo customerRepo;
    // ____________________________________ مرتبط با get
    @Override
    public List<Customer> getAllCustomers() {
        return (List<Customer>) customerRepo.findAll();
    }

    @Override
    public Customer getCustomerById(Long idMoshtari) {
        Optional<Customer> thisCustomer = customerRepo.findByIdMoshtariIs(idMoshtari);
        if (thisCustomer.isPresent()) {
            return thisCustomer.get();
        } else {
            throw new RuntimeException();
        }
    }

    @Override
    public List<Customer> getCustomersByFirst_name(String fN) {
        return customerRepo.findAllByFN(fN);
    }

    @Override
    public List<Customer> filter(int size, int page, String customerFirstName, String customerFamily) {
        BooleanBuilder bb = new BooleanBuilder();
        QCustomer qCustomer = QCustomer.customer;
        // ______________________________________
        if (customerFirstName != null && !customerFirstName.isEmpty()) bb.and(qCustomer.fN.eq(customerFirstName));
        if (customerFamily != null && !customerFamily.isEmpty()) bb.and(qCustomer.lN.eq(customerFamily));
//        if (age != null)
//            bb.and(qCustomer.age.goe(age)); // goe: greater or equal
        // ______________________________________
        Predicate pridicate = bb.getValue();
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "customerId"));
        Page<Customer> thisPage = customerRepo.findAll(pridicate, pageable);
        return thisPage.getContent();
    }


    // ____________________________________ مرتبط با put
    @Override
    public Customer addCustomer(Customer customer) {
        customerRepo.save(customer);
        return customer;
    }

    @Override
    public Customer addCustomer(Long idMoshtari, String first_name, String ln, String email) {
        Customer thisCustomer = new Customer(idMoshtari, first_name, ln, "", email, null);
        customerRepo.save(thisCustomer);
        return thisCustomer;
    }

    // ____________________________________ مرتبط با delete
    @Override
    public String delAllCustomers() {
        String numberOfRecords = "تعداد " + customerRepo.count() + "رکورد از دیتابیس حذف شد.";
        customerRepo.deleteAll();
        return numberOfRecords;
    }

    @Override
    public Customer delThisCustomer(Long id) {
        Optional<Customer> a = customerRepo.findById(id);
        if (a.isPresent()) {
            customerRepo.deleteById(id);
            return a.orElse(null);
        } else throw new RuntimeException();
    }

    @Override
    public String delAllCustomersByThisFirstName(String fN) {
        int primaryNumber = customerRepo.findAllByFN(fN).size();
        customerRepo.deleteAllByfN(fN);
        int numberAfterDelete = customerRepo.findAllByFN(fN).size();
        return "تعداد " + primaryNumber + " نفر مشتری پیدا شدند و پس از حذف تعدادشان شد: " + numberAfterDelete;
    }

    // ____________________________________ مرتبط با post
    public void updateCustomer(Long thisCustomerId, String newFirstName, String newLastName) {
        Optional<Customer> st = customerRepo.findById(thisCustomerId);
        if (st.isPresent()) {
            Customer customer = st.get();
            customer.setfN(newFirstName);
            customer.setLN(newLastName);
            customerRepo.save(customer);
        } else throw new RuntimeException();
    }
// ____________________________________ بازبینی نشده ___________________
//
//
//    @Override
//    public Customer getCustomer(Long id) {
//        Optional<Customer> st = customerRepo.findById(id);
//        if (st.isPresent()) {
//            return st.get();
//        } else {
//            return null;
////            throw new NotFoundException("مشترییی با شماره شناسایی " + id + " پیدا نشد. ");
//        }
//    }
//
//    @Override
//    public List<Customer> getCustomer(String name) {
//        return customerRepo.findAllByFirst_name(name);
//    }
//
//
//
//
//    @Override
//    public Page<Customer> getAll(int s, int n) {
//        Pageable pageable = PageRequest.of(s, n, Sort.by(
//                Sort.Order.desc("customerId")
//        ));
//        Page<Customer> p = customerRepo.findAll(pageable);
//        return p;
//    }
//
//    @Override
//    public void addCustomer(Customer customer, MultipartFile file) {
//        customer.setImageName(file.getOriginalFilename());
//        customer.setImageType(file.getContentType());
//        try {
//            customer.setImage(IOUtils.toByteArray(file.getInputStream()));
//        } catch (IOException e) {
//            outln("مشکل در addcustomer");
//            outln(e.getMessage());
//            exc(e);
//        }
//        customerRepo.save(customer);
//    }
//
//    @Override
//    public List<Customer> filter(int pageSize, int pageNumber, String customerFirst_name, Integer age, String customerFamilyName) {
//        BooleanBuilder bb = new BooleanBuilder();
//        QCustomer qCustomer = QCustomer.customer;
//        if (customerFirst_name != null && !customerFirst_name.isEmpty())
//            bb.and(qCustomer.name.eq(customerFirst_name));
//        if (customerFamilyName != null && !customerFamilyName.isEmpty())
//            bb.and(qCustomer.family.eq(customerFamilyName));
//        if (age != null)
//            bb.and(qCustomer.age.goe(age)); // goe: greater or equal
//        // ______________________________________
//        Predicate pridicate = bb.getValue();
//        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by(Sort.Direction.ASC, "customerId"));
//        Page<Customer> page = customerRepo.findAll(pridicate, pageable);
//        return page.getContent();
//    }
//
//    @Override
//    public List<Customer> filter(int pageSize, int pageNumber, String customerFirst_name, Integer age, String customerFamilyName, String order) {
//        BooleanBuilder bb = new BooleanBuilder();
//        QCustomer qCustomer = QCustomer.customer;
//        outln("order= " + order);
//        if (order == null || order.isEmpty()) {
//            order = "shimi";
//            outln("nullllllllllllllllll0");
//        }
//        bb.and((qCustomer.orders.any().orderId).goe(3));
//        // ______________________________________
//        Predicate predicate = bb.getValue();
//        outln("predicate.toString()= " + predicate.toString());
//        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by(Sort.Direction.ASC, "customerId"));
//        Page<Customer> page = customerRepo.findAll(predicate, pageable);
//        outln("predicate.toString()= " + predicate.toString());
//        outln(Arrays.toString(page.toList().toArray()));
//        //
//        predicate = qCustomer.orders.any().orderName.eq(order);
//        page = customerRepo.findAll(predicate, pageable);
//        outln("predicate.toString()= " + predicate.toString());
//        outln(Arrays.toString(page.toList().toArray()));
//        //
//        predicate = qCustomer.orders.any().createdAt.eq(new Date(0L));
//        page = customerRepo.findAll(predicate, pageable);
//        outln("predicate.toString()= " + predicate.toString());
//        outln(Arrays.toString(page.toList().toArray()));
//        //
//        predicate = qCustomer.name.contains("ز");
//        page = customerRepo.findAll(predicate, pageable);
//        outln("predicate.toString()= " + predicate.toString());
//        outln(Arrays.toString(page.toList().toArray()));
//        //
//        predicate = qCustomer.orders.size().goe(1);
//        page = customerRepo.findAll(predicate, pageable);
//        outln("predicate.toString()= " + predicate.toString());
//        outln(Arrays.toString(page.toList().toArray()));
//        //
//        predicate = qCustomer.orders.isNotEmpty();
//        page = customerRepo.findAll(predicate, pageable);
//        outln("predicate.toString()= " + predicate.toString());
//        outln(Arrays.toString(page.toList().toArray()));
//        //
//        return page.getContent();
//    }
//
//    @Override
//    public List<Customer> filter(int pageSize, int pageNumber, Predicate pridicate) {
//        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by(Sort.Direction.ASC, "customerId"));
//        Page<Customer> page = customerRepo.findAll(pridicate, pageable);
//        return page.getContent();
//    }


}
