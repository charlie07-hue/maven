package chap09;

import org.apache.commons.dbcp.BasicDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@ComponentScan(basePackages = {"chap09"})
@EnableWebMvc 
@EnableTransactionManagement
public class MvcConfig implements WebMvcConfigurer{
	
	@Value("${db.driver}")
	private String driver;
	@Value("${db.url}")
	private String url;
	@Value("${db.username}")
	private String username;
	@Value("${db.password}") // properties에서 get해서 value값을 가지고 password에 set
	private String password;
	
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
		dataSource.setDriverClassName(driver); // properties파일만 변경하면 되도록 수정하였다.
		dataSource.setUrl(url);
		dataSource.setUsername(username);
		dataSource.setPassword(password);
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
	
	// 기능을 구현함
	@Bean
	public LoginInterceptor loginInterceptor() {
		return new LoginInterceptor();
	}
	
	// 구현한 기능을 등록함
	@Override
	public void addInterceptors(InterceptorRegistry reg) {
		reg.addInterceptor(loginInterceptor()).addPathPatterns("/member/mypage.do").addPathPatterns("").addPathPatterns("");
		// excludePathPatterns()
		// 관리자: .addPathPatterns("/admin/**").excludePathPatterns("/admin/index.do")
	}
	
	//property file을 처리해주는 bean이 있다
	@Bean
	public static PropertySourcesPlaceholderConfigurer property() {
		PropertySourcesPlaceholderConfigurer conf = new PropertySourcesPlaceholderConfigurer();
		conf.setLocations(new ClassPathResource("db.properties")); // 최상위 폴더이므로 파일명만 작성 (?)
		return conf;
	}
	
	@Bean
	public PlatformTransactionManager txmanager() {
		DataSourceTransactionManager ptm = new DataSourceTransactionManager();
		ptm.setDataSource(dataSource()); //주입
		return ptm; //주입된 상태의 객체가 리턴
	}
	
}
