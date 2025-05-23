package ed.av.rpg.util;

public final class StringConstants {

    public static final class Headers {
        public static final String SESSION_ID_HEADER_KEY = "session-id";
        public static final String CLASS_NAME_HEADER_KEY = "simple-class-name";
        public static final String USER_ID_HEADER_KEY = "user-id";
    }

    public static final class TopicUri {
        public static final String COMMON_TOPIC_URI = "/topic/common";
        public static final String PERSONAL_TOPIC_URI = "/topic/personal" + "/";
    }

    public static final class InfoMessages {
        public static final String CONNECTION_SUCCESS = "Подключено к серверу.";
        public static final String CONNECTION_FAILED = "Не удалось соединиться с сервером!";
        public static final String AUTHENTICATION_SUCCESS = "Аутентификация прошла успешно.";
        public static final String REGISTRATION_SUCCESS = "Вы зарегистрированы.";
        public static final String WRONG_PASSWORD = "Ошибка аутентификации : неверный пароль!";
        public static final String USERNAME_NOT_FOUND = "Ошибка аутентификации: пользователя с таким username не существует!";
        public static final String LOGIN_ALREADY_EXISTS = "Ошибка регистрации: Логин занят.";
    }

    public static final class ErrorMessages {


    }

    public static final class WarningMessages {


    }

    public static final String COMPONENT_NOT_INITIALIZED = "Component is not initialized yet!";
}
