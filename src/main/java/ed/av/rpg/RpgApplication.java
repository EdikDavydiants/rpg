package ed.av.rpg;

import ed.av.rpg.form.chat.Chat;
import ed.av.rpg.form.common.lazycomponents.containers.LHBox;
import ed.av.rpg.form.connection.ConnectionForm;
import ed.av.rpg.form.login.LogInForm;
import ed.av.rpg.form.register.RegisterForm;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
@RequiredArgsConstructor
public class RpgApplication extends Application {

	private static ConfigurableApplicationContext springContext;

	public static void main(String[] args) {

		springContext = SpringApplication.run(RpgApplication.class, args);

		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}

		Application.launch(RpgApplication.class, args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {

		var chat = springContext.getBean(Chat.class);
		var logInForm = springContext.getBean(LogInForm.class);
		var connectionForm = springContext.getBean(ConnectionForm.class);
		var registerForm = springContext.getBean(RegisterForm.class);


		LHBox rootForm = new LHBox();
		rootForm.preInitAddAll(connectionForm, registerForm, logInForm, chat);
		rootForm.initialize();
		primaryStage.setScene(new Scene(rootForm.getNode(), 1000, 700));
		primaryStage.show();
	}

	@Override
	public void stop() throws Exception {
		springContext.close();
		super.stop();
	}
}
