package by.hobbygames.testdata.errors;

public class ApiLoginErrors {
    public static final String PHONE_IS_NOT_PROVIDED = "Данные не введены";
    public static final String PHONE_IS_TOO_SHORT = "Слишком мало символов";
    public static final String PHONE_IS_TOO_LONG = "Слишком много символов";
    public static final String PHONE_IS_NOT_REGISTERED = "Такой телефон не зарегистрирован";

    public static final String EMAIL_IS_INVALID = "Неверный формат Электронной почты";
    public static final String EMAIL_IS_NOT_REGISTERED = "Такой E-Mail адрес не зарегистрирован";

    public static final String PASSWORD_IS_NOT_PROVIDED = "Введите пароль";
    public static final String PASSWORD_IS_WRONG = "Неверный пароль";

    public static final String LOGIN_MISSING_CREDENTIALS = "Введите телефон или электронную почту";
    public static final String LOGIN_INVALID_DATA = "Введённые данные некорректны";
    public static final String LOGIN_WRONG_PHONE_OR_EMAIL = "Неверный телефон/e-mail";
}
