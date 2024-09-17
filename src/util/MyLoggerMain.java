package util;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import static util.MyLogger.log;

public class MyLoggerMain {

    public static void main(String[] args) {
        log("hello thread");
        log(123);
    }
}
