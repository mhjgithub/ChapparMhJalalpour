package j.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SwaggerCustomerConfig {
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build();
    }
}

//import io.swagger.models.Contact;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import springfox.documentation.service.ApiInfo;
//import springfox.documentation.spi.DocumentationType;
//import springfox.documentation.spring.web.plugins.Docket;
//
//import java.util.Arrays;
//import java.util.HashSet;
//import java.util.Set;
//
//@Configuration
////@EnableSwagger2WebMvc
//public class SwaggerCustomerConfig {
//    static springfox.documentation.service.Contact a = null;
//    public static final Contact DEFAULT_CONTACT = new Contact();
//
//    public static final ApiInfo DEFAULT_API_INFO = new ApiInfo(
//            "Awesome API Title", "Awesome API Description", "1.0",
//            "urn:tos", a,
//            "Apache 2.0", "http://www.apache.org/licenses/LICENSE-2.0", Arrays.asList());
//
//    private static final Set<String> DEFAULT_PRODUCES_AND_CONSUMES =
//            new HashSet<>(Arrays.asList("application/json",
//                    "application/xml"));
//
//    @Bean
//    public Docket api() {
//        return new Docket(DocumentationType.SWAGGER_2)
//                .apiInfo(DEFAULT_API_INFO)
//                .produces(DEFAULT_PRODUCES_AND_CONSUMES)
//                .consumes(DEFAULT_PRODUCES_AND_CONSUMES);
//    }
//}




//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import com.google.common.base.Predicate;
//
//import springfox.documentation.builders.ApiInfoBuilder;
//import springfox.documentation.service.ApiInfo;
//import springfox.documentation.spi.DocumentationType;
//import springfox.documentation.spring.web.plugins.Docket;
//import springfox.documentation.swagger2.annotations.EnableSwagger2;
//import static springfox.documentation.builders.PathSelectors.regex;
//import static com.google.common.base.Predicates.or;
//
//@Configuration
//@EnableSwagger2
//public class SwaggerCustomerConfig {
//
//    @Bean
//    public Docket postsApi() {
//        return new Docket(DocumentationType.SWAGGER_2).groupName("public-api")
//                .apiInfo(apiInfo()).select().paths(postPaths()).build();
//    }
//
//    private Predicate<String> postPaths() {
//        return or(regex("/api/posts.*"), regex("/api/javainuse.*"));
//    }
//
//    private ApiInfo apiInfo() {
//        return new ApiInfoBuilder().title("JavaIsdfsdfnUse API")
//                .description("JaffvaInsdfUse API refedfrence for desdfvelopers")
//                .termsOfServiceUrl("http://sdfdsfdsf.com")
//                .contact("dsfds@gmail.com").license("JavaInsdfsdfUse Licedsfnse")
//                .licenseUrl("javainudsfdsfse@gmail.com").version("dddd1.0").build();
//    }
//
//}




