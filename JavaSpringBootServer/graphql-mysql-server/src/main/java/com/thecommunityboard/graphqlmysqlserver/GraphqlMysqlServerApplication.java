package com.thecommunityboard.graphqlmysqlserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.filter.CommonsRequestLoggingFilter;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class GraphqlMysqlServerApplication {
	@Bean
	public CommonsRequestLoggingFilter requestLoggingFilter() {
		CommonsRequestLoggingFilter loggingFilter = new CommonsRequestLoggingFilter();
		loggingFilter.setIncludeClientInfo(true);
		loggingFilter.setIncludeQueryString(false);
		loggingFilter.setIncludePayload(true);
		loggingFilter.setMaxPayloadLength(10000);
		loggingFilter.setIncludeHeaders(false);
		return loggingFilter;
	}

	public static void main(String[] args) {
		SpringApplication.run(GraphqlMysqlServerApplication.class, args);
	}
}
