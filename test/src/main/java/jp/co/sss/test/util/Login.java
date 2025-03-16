package jp.co.sss.test.util;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Target({ java.lang.annotation.ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = { LoginValidator.class})

public @interface Login {
	String message() default "Email、もしくはパスワードが間違っています。";
	Class<?>[] groups() default{};
	Class<? extends Payload>[] payload() default {};
	String fieldEmail() default"email";
	String fieldPassword() default "password";
	

}
