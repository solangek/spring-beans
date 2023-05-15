package hac.springbeans;

import hac.springbeans.beans.Label;
import hac.springbeans.beans.Messages;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.web.context.annotation.ApplicationScope;
import org.springframework.web.context.annotation.RequestScope;
import org.springframework.web.context.annotation.SessionScope;

/**
 * create some beans witn various scopes using QUALIFIERS (method names)
 * since there are multiple methods for creating Label beans, we will need to use @Qualifier
 * to specify which one we want to use
 */
@Configuration
public class BeanConfiguration {

    /* singleton scope */
    @Bean
    @Scope("singleton")
    public Label autowiredFieldSingletonScope() {
        Label l =  new Label();
        l.setLabel("I'm SINGLETON Label bean");
        return l;
    }

    /* application scope */
    @Bean
    @ApplicationScope
    public Label autowiredFieldApplicationScope() {
        Label l =  new Label();
        l.setLabel("I'm APPLICATION Label bean");
        return l;
    }

    /* request scope */
    @Bean
    @RequestScope
    public Label autowiredFieldRequestScope() {
        Label l =  new Label();
        l.setLabel("I'm REQUEST Label bean");
        return l;
    }

    /* session scope
    in Servlet we would write: request.getSession().addAttribute ("sessionScopeBeanExample",new Label())
    */
    @Bean
    @SessionScope
    public Label sessionScopeBeanExample () {
        Label l =  new Label();
        l.setLabel("I'm SESSION Label bean");
        return l;
    }

    /* BEAN using ctor - session scope */
    @Bean
    @SessionScope
    public Messages sessionBeanExample () {
        Messages m = new Messages();
        m.add(new Label("I'm session bean Messages"));
        return m;
    }
}
