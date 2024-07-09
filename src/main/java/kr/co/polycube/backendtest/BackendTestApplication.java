package kr.co.polycube.backendtest;

import kr.co.polycube.backendtest.user.filter.UserAddFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class BackendTestApplication {

    public static void main(String[] args) {
        SpringApplication.run(
            BackendTestApplication.class,
            args
        );
    }

    @Bean
    public FilterRegistrationBean<UserAddFilter> loggingFilter() {
        FilterRegistrationBean<UserAddFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new UserAddFilter());
        registrationBean.addUrlPatterns("/*");
        return registrationBean;
    }

}
