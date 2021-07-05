package chap07;

import org.apache.commons.dbcp.BasicDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@ComponentScan(basePackages = {"chap07"})
@EnableWebMvc 
public class MvcConfig implements WebMvcConfigurer{
	@Override 
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer conf) {
		conf.enable();
	}
	
	@Override
	public void configureViewResolvers(ViewResolverRegistry reg) {
		// (prefix : jsp가 있는 경로 [시작경로], suffix : 확장자) ☆☆☆ 
		reg.jsp("/WEB-INF/view/", ".jsp"); 
	}
	
	@Override
	public void addViewControllers(ViewControllerRegistry reg) {
		// 비즈니스로직이 필요없는(디자인만 있는 페이지) url과 jsp 매핑
		reg.addViewController("/index.do").setViewName("index"); 
	}
	
	@Bean
	public BasicDataSource dataSource() {
		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setDriverClassName("com.mysql.jdbc.Driver");
		dataSource.setUrl("jdbc:mysql://localhost:3307/webdb");
		dataSource.setUsername("webuser");
		dataSource.setPassword("web1234");
		return dataSource; // set된 객체를 Bean에 등록
	}
	
	@Bean
	public SqlSessionFactory sqlSessionFactory() throws Exception {
		SqlSessionFactoryBean ssfb = new SqlSessionFactoryBean();
		ssfb.setDataSource(dataSource());
		PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
		ssfb.setMapperLocations(resolver.getResources("classpath:mapper/**/*.xml"));
		//ssfb.setMapperLocations(resolver.getResource("D:\\Java\\workspace\\maven\\target\\classes\\chap07/member.xml"));
		return ssfb.getObject(); // sqlSession 객체를 생성하기 때문에
	}
	
	@Bean
	public SqlSessionTemplate sqlSessionTemplate() throws Exception {
		SqlSessionTemplate sqlSessionTemplate = new SqlSessionTemplate(sqlSessionFactory());
		return sqlSessionTemplate;
	}
	
}
