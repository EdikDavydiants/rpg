package ed.av.rpg;

import ed.av.rpg.module.connection.ConnectionForm;
import ed.av.rpg.module.login.LogInForm;
import ed.av.rpg.module.register.RegisterForm;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
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

//		// 1. Создаем STOMP-клиент
//		WebSocketClient webSocketClient = new StandardWebSocketClient();
//		WebSocketStompClient stompClient = new WebSocketStompClient(webSocketClient);
//		stompClient.setMessageConverter(new StringMessageConverter());
//
//		// 2. Обработчик сессии
//		StompSessionHandler handler = springContext.getBean(ChatStompSessionHandler.class);
//
//		// 3. Подключаемся асинхронно
//		String url = "wss://lawlessly-infinite-sylph.cloudpub.ru/ws";
//		CompletableFuture<StompSession> future = stompClient.connectAsync(url, handler);
//
//		// 4. Подписываемся после подключения
//		future.thenAccept(session -> {
//			session.subscribe("/topic/messages", handler);
//
//			// Отправка тестового сообщения
//			session.send("/app/chat", "Hello from Java Client!");
//		});
//
//		// 5. Ждем завершения (для демо-приложения)
//		try {
//			Thread.sleep(5000); // Даем время на получение ответа
//		} catch (InterruptedException e) {
//			Thread.currentThread().interrupt();
//		}

		Application.launch(RpgApplication.class, args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {

		//var chat = springContext.getBean(Chat.class);
		var logInForm = springContext.getBean(LogInForm.class);
		var enterForm = springContext.getBean(ConnectionForm.class);
		var registerForm = springContext.getBean(RegisterForm.class);

		primaryStage.setScene(new Scene(new HBox(logInForm, enterForm, registerForm), 1000, 700));
		primaryStage.show();
	}

	@Override
	public void stop() throws Exception {
		springContext.close();
		super.stop();
	}
}
