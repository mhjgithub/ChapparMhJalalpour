package j.dto;

import j.dto.CustomerDto;
import j.model.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper(componentModel = "spring")
public interface CustomerMapper {
//    @Mapping(source = "idMoshtari", target = "idMoshtari", qualifiedByName = "convertToStringAge")
//    @Mapping(source = "fN", target = "firstN")
//    @Mapping(source = "ln", target = "lastN")
//    @Mapping(source = "description", target = "description")
//    @Mapping(source = "email", target = "email")
    CustomerDto modelToDto(Customer customer);

//    @Mapping(source = "idMoshtari", target = "idMoshtari", qualifiedByName = "convertToLong")
//    @Mapping(source = "firstN", target = "fN")
//    @Mapping(source = "lastN", target = "ln")
//    @Mapping(source = "description", target = "description")
//    @Mapping(source = "email", target = "email")
    Customer dtoToModel(CustomerDto customerDTO);// _____________________________________________ تابع تبدیل اینتیجر به استرینگ و بالعکس

    @Named("convertToLong")
    default Long convertToIntegerAge(String string) {
        return Long.parseLong(string);
    }

    @Named("convertToStringAge")
    default String convertToStringAge(Long number) {
        return String.valueOf(number);
    }

    // _____________________________________________ List (for user/swagger)
    List<CustomerDto> modelToDto(Page<Customer> userList);

    List<CustomerDto> modelToDto(List<Customer> userList);
}
