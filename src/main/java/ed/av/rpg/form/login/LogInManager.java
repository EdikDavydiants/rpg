package ed.av.rpg.form.login;

import lombok.RequiredArgsConstructor;
import ed.av.rpg.auth.connection.ConnectionData;
import ed.av.rpg.auth.model.dto.LogInDto;
import org.springframework.web.client.RestTemplate;

@RequiredArgsConstructor
public class LogInManager {

    private final LogInForm form;

    public void processData(LogInDto logInData) {

    }

    public void openForm() {
        form.getNode().setVisible(true);
    }

    public void closeForm() {
        form.getNode().setVisible(false);
    }
}
