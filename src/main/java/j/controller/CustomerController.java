package j.controller;

import j.dto.CustomerDto;
import j.dto.CustomerMapper;
import j.model.Customer;
import j.service.CustomerServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@RestController
//@Controller
//@Transactional
@RequestMapping("/Customer")
//@ApiOperation("Products API")
public class CustomerController {
    // _______________________________________________
    CustomerMapper customerMapper;
    CustomerServiceInterface customerServiceInterface;

    public CustomerController(CustomerMapper customerMapper, CustomerServiceInterface customerServiceInterface) {
        this.customerMapper = customerMapper;
        this.customerServiceInterface = customerServiceInterface;
    }
    @Autowired
    public CustomerController(CustomerServiceInterface customerServiceInterface) {
        this.customerServiceInterface = customerServiceInterface;
    }

    public CustomerController() {
    }

    // _______________________________________________ Gets!!!
    @GetMapping("/getAllCustomers")
    private ResponseEntity<List<CustomerDto>> getAllCustomers() {
        List<Customer> list = customerServiceInterface.getAllCustomers();
        List<CustomerDto> listDto = customerMapper.modelToDto(list);
//        outln("فهرست مشتریان ارسال شد.");
        return ResponseEntity.ok(listDto);
    }

    @GetMapping("/getCustomerById/{id}")
    private ResponseEntity<CustomerDto> getCustomerById(@PathVariable("id") Long id) {
        Customer thisCustomer = customerServiceInterface.getCustomerById(id);
        CustomerDto customerDto = customerMapper.modelToDto(thisCustomer);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(customerDto)
//                .contentType(MediaType.parseMediaType(st.getImageType())) // برای این که زمان دانلود فرمتش مشخص شود
//                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + st.getImageName() + "\"") // لازم نیست نامی که به دانلود کننده فایل می رسد همان نام اصلی در پایگاه داده باشد
                ;
    }

    @GetMapping("/getCustomersByFirst_name/{first_name}")
    private List<CustomerDto> getCustomersByFirst_name(@PathVariable("first_name") String first_name) {
        return customerMapper.modelToDto(customerServiceInterface.getCustomersByFirst_name(first_name));
    }


    @GetMapping("/getFiteredCustomers")
    private ResponseEntity<List<CustomerDto>> getFiteredCustomers(
            @RequestParam(name = "size", defaultValue = "100", required = false) int size
            , @RequestParam(name = "page", defaultValue = "0", required = false) int page
            , @RequestParam(name = "customerFirstName", required = false) String customerFirstName
            , @RequestParam(name = "customerFamily", required = false) String customerFamily
    ) {
        List<Customer> list = customerServiceInterface.filter(size, page, customerFirstName, customerFamily);
        List<CustomerDto> listDto = customerMapper.modelToDto(list);
        return ResponseEntity.ok(listDto);
    }

    // _______________________________________________ Put
    @PutMapping("/putCustomer")
    private ResponseEntity putCustomer(@RequestBody @Valid CustomerDto customerDto) {// todo valid سفارشته؟ جاش؟
        Customer customer = customerMapper.dtoToModel(customerDto);
        customerServiceInterface.addCustomer(customer);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(" مشتری با عکس افزوده شد:" + "\n" + customer.toString());
    }

    // _______________________________________________ Delete
    @DeleteMapping("/delAllCustomers")
    private ResponseEntity delAllCustomers() {
        return ResponseEntity.status(HttpStatus.OK).body(customerServiceInterface.delAllCustomers());
//        return "مشتریی با این مشخصات حذف شد: " + deletedCustomer.toString();
    }

    @DeleteMapping("/delStById_{customerId}")
    private ResponseEntity delStById(@PathVariable("customerId") Long customerId) {
        Customer deletedCustomer = customerServiceInterface.delThisCustomer(customerId);
        return ResponseEntity.status(HttpStatus.FOUND).body("این مشتری وجود داشت و حذف شد : " + deletedCustomer.toString());
    }

    @DeleteMapping("/delAllCustomersByThisFirstName/{FirstName}")
    private ResponseEntity delAllCustomersByThisFirstName(@PathVariable("FirstName") String FirstName) {
        return ResponseEntity.status(HttpStatus.FOUND).body(customerServiceInterface.delAllCustomersByThisFirstName(FirstName));
    }

// _______________________________________________ Post
//    @RequestMapping(value = "/postChangeCustomer/{id}/{newName}/{newAge}", method = RequestMethod.POST, headers = "Accept=application/json")
    @PostMapping("/postChangeCustomer/{thisCustomerId}/{newFirstName}/{newLastName}")
    private ResponseEntity postChangeCustomer(@PathVariable("thisCustomerId") Long thisCustomerId, @PathVariable("newFirstName") String newFirstName, @PathVariable("newLastName") String newLastName) {
        customerServiceInterface.updateCustomer(thisCustomerId, newFirstName, newLastName);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body("مشخصات تغییر کرد");
    }

    @PostMapping("/postChangeCustomerByMap")
    private ResponseEntity postChangeCustomerByMap(@RequestBody Map<String, Object> map) {
        String first_name = (String) map.get("first_name");
        String ln = (String) map.get("ln");
        Long idMoshtari = ((Number) map.get("idMoshtari")).longValue();
//        Integer newAge = (int) map.get("age");
        customerServiceInterface.updateCustomer(idMoshtari, first_name, ln);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body("مشخصات تغییر کرد");
    }

}
