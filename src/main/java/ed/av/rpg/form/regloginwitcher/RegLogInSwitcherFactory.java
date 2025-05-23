package ed.av.rpg.form.regloginwitcher;

import org.springframework.context.ApplicationEventPublisher;

public class RegLogInSwitcherFactory {

    public static RegLogInSwitcherForm createRegLogInSwitcherForm(ApplicationEventPublisher publisher) {
        RegLogInSwitcherForm form = new RegLogInSwitcherForm(publisher);
        var regButton = new RegSwitcherButton(form);
        var logInButton = new LogInSwitcherButton(form);
        form.setLogInSwitcherButton(logInButton);
        form.setRegSwitcherButton(regButton);
        form.preInitAddAll(regButton, logInButton);
        return form;
    }
}
