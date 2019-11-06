package com.cg.realestate.audittrail;
/*
 * Author: 		Jayesh Gaur
 * Description: Audit Trial
 * Created on:  November 6, 2019
 */
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@Configuration
@EnableJpaAuditing(auditorAwareRef = "auditorProvider")
public class EMSAuditConfiguration {
	@Bean
	public AuditorAware<String> auditorProvider(){
		return new AuditorAwareImpl();
	}
}
