package it.partec.webappspringsecurity.mockuser;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import org.springframework.security.test.context.support.WithMockUser;

@Retention(RetentionPolicy.RUNTIME)
@WithMockUser(username = "user", authorities = {"ROLE_USER"})
public @interface MockUser {

}
