package my.project.boot;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication
@MapperScan(value = "my.project.boot.mapper")
@EnableAutoConfiguration(exclude = DataSourceAutoConfiguration.class)
public class Application{
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}
