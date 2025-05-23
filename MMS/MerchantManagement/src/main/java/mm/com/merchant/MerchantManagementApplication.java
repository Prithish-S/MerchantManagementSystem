package mm.com.merchant;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("mm.com.merchant")
public class MerchantManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(MerchantManagementApplication.class, args);
	}

}