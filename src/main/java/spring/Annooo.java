package spring;


import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Conditional;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import javax.validation.Valid;
import javax.validation.constraints.Pattern;

//@EnableWebMvc
//@EnableCaching
//@EnableScheduling
//@EnableAsync

//@EnableWebSocket
//@EnableJpaRepositories
//@EnableTransactionManagement
//@EnableJpaAuditing
////public class Annooo {
////}
//
//@Pattern(regex=,flag=)
//@ConditionalOnClass(DataSource.class)
//@EnableWebMvc
//@Valid
//public class WebConfig extends WebMvcConfigurerAdapter {
//
//    @Override
//    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
//        super.configureMessageConverters(converters);
//        StringHttpMessageConverter stringMessageConverter = new StringHttpMessageConverter();
//
//        // add support for "text" media type. This may require Charset of UTF-8
//        stringMessageConverter.setSupportedMediaTypes(Arrays.asList(MediaType.TEXT_PLAIN, MediaType.parseMediaType("text")));
//
//
//        converters.add(stringMessageConverter);
//    }
//
//}