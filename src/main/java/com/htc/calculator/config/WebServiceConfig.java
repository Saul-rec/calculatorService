package com.htc.calculator.config;

import java.util.Properties;

import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.config.annotation.WsConfigurerAdapter;
import org.springframework.ws.soap.server.endpoint.SoapFaultDefinition;
import org.springframework.ws.soap.server.endpoint.SoapFaultMappingExceptionResolver;
import org.springframework.ws.transport.http.MessageDispatcherServlet;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.xml.xsd.XsdSchema;

import com.htc.calculator.exceptions.CalculatorExceptionHandler;
import com.htc.calculator.exceptions.DetailSoapFaultDefinitionExceptionResolver;

@EnableWs
@Configuration
public class WebServiceConfig extends WsConfigurerAdapter {
	
	
	@Bean
	public ServletRegistrationBean<MessageDispatcherServlet> messageDispatcherServlet(ApplicationContext context) {
	    MessageDispatcherServlet messageDispatcherServlet = new MessageDispatcherServlet();
	    messageDispatcherServlet.setApplicationContext(context);
	    messageDispatcherServlet.setTransformWsdlLocations(true);
	    return new ServletRegistrationBean<>(messageDispatcherServlet, "/ws/*");
	  }
	
	 @Bean(name = "calculatorService")
	 public DefaultWsdl11Definition defaultWsdl11Definition(XsdSchema calculatorSchema) {
	   DefaultWsdl11Definition definition = new DefaultWsdl11Definition();
	   definition.setPortTypeName("calculatorServicePort");
	   definition.setLocationUri("/ws");
	   definition.setTargetNamespace("http://www.htc.com/calculatorService");
	   definition.setSchema(calculatorSchema);
	   return definition;
	 }

	 @Bean
	 public XsdSchema calculatorSchema() {
	   return new SimpleXsdSchema(new ClassPathResource("xsd/CalculatorService.xsd"));
	 }
	 
	 
	 @Bean
	 public SoapFaultMappingExceptionResolver exceptionResolver() {
		 SoapFaultMappingExceptionResolver resolver = new DetailSoapFaultDefinitionExceptionResolver();
		 
//		 SoapFaultDefinition definition = new SoapFaultDefinition();
//		 definition.setFaultCode(SoapFaultDefinition.SERVER);
//		 resolver.setDefaultFault(definition);
		 
		 Properties errorMapping = new Properties();
		 errorMapping.setProperty(Exception.class.getName(), SoapFaultDefinition.SERVER.toString());
		 errorMapping.setProperty(CalculatorExceptionHandler.class.getName(), 
				 SoapFaultDefinition.SERVER.toString());
		 resolver.setExceptionMappings(errorMapping);
		 resolver.setOrder(1);
		 
		 return resolver;
	 }

}
