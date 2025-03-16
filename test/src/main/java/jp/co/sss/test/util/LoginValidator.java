package jp.co.sss.test.util;

import java.util.Optional;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import jp.co.sss.test.entity.User;
import jp.co.sss.test.repository.UserRepository;

@Component
public class LoginValidator implements ConstraintValidator<Login, Object>{

	@Autowired
    private UserRepository repository;
	
	private String email;
	private String password;
	
	@Override
	public void initialize(Login annotation) {
		this.email = annotation.fieldEmail();
		this.password = annotation.fieldPassword();
	}
	
	@Override
	public boolean isValid(Object value, ConstraintValidatorContext context) {
		BeanWrapper beanWrapper = new BeanWrapperImpl(value);
		String email = (String) beanWrapper.getPropertyValue(this.email);
		String password = (String) beanWrapper.getPropertyValue(this.password);
		
		Optional<User> userOptional = repository.findByEmail(email);
		
		if(userOptional.isPresent()) {
			User user = userOptional.get();
			BCryptPasswordEncoder bcpe = new BCryptPasswordEncoder();
			return bcpe.matches(password,user.getPassword());
			}
		return false;
	}
	
}
