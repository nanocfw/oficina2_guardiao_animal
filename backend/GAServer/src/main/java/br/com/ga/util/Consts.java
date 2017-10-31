package br.com.ga.util;


public class Consts {
    public static final String REST_URL = "http://localhost:8090/";
    public static final String REST_LOGIN = "gaadminserver";
    public static final String REST_PASSWORD = "@g4re5tp4ss#";
    public static final String COOKIE_NAME = "ga_client_cookie";
    public static final int COOKIE_LIFE_TIME = 60 * 60 * 24 * 7;// 1 semana
    public static final int MINUTE_IN_MILLI = 60 * 1000;
    public static final int HOUR_IN_MILLI = MINUTE_IN_MILLI * 60;
    public static final int DAY_IN_MILLI = HOUR_IN_MILLI * 24;
    public static final String PARAM_USER_SESSION = "ga_current_user";
}
