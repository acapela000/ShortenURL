package today.whatisthaodoing.ShortenURL;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.Collections;

@Configuration
@EnableWebMvc
public class SwaggerConfig {
    private ApiInfo getApi() {
        return new ApiInfo(
                "",
             "",
             "",
             "MIT",
             new Contact("","",""),
                "",
                "",
                Collections.emptyList()
        );
    }

    @Bean
    public Docket swaggerAPI() {
        return new Docket(DocumentationType.OAS_30)
                .apiInfo(getApi())
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build();
    }

}
