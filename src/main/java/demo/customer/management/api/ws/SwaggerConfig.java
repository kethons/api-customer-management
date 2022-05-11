package demo.customer.management.api.ws;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.hateoas.client.LinkDiscoverer;
import org.springframework.hateoas.client.LinkDiscoverers;
import org.springframework.hateoas.mediatype.collectionjson.CollectionJsonLinkDiscoverer;
import org.springframework.plugin.core.SimplePluginRegistry;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.VendorExtension;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author Ketan_Kankapure
 *
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

	Contact contact = new Contact("Ketan Kankapure", "https://www.linkedin.com/in/ketan-kankapure-480410198/",
			"k.s.kankapure1@gmail.com");

	@SuppressWarnings("rawtypes")
	List<VendorExtension> vendorExtensions = new ArrayList<>();

	ApiInfo apiInfo = new ApiInfo("Customer management app RESTful Web Service documentation",
			"This pages documents Customer management app RESTful Web Service endpoints", "1.0", null, contact,
			"Apache 2.0", "http://www.apache.org/licenses/LICENSE-2.0", vendorExtensions);

	@Bean
	public Docket apiDocket() {

		Docket docket = new Docket(DocumentationType.SWAGGER_2).protocols(new HashSet<>(Arrays.asList("HTTP")))
				.apiInfo(apiInfo).select().apis(RequestHandlerSelectors.basePackage("demo.customer.management.api.ws"))
				.paths(PathSelectors.any()).build();

		return docket;

	}

	@Bean
	public LinkDiscoverers discovers() {
		List<LinkDiscoverer> plugins = new ArrayList<>();
		plugins.add(new CollectionJsonLinkDiscoverer());
		return new LinkDiscoverers(SimplePluginRegistry.create(plugins));
	}

}
