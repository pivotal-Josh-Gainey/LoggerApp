package com.jgainey.logger_app;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Utils {

    public static Logger LOGGER;



    enum LOGTYPE{INFO,WARNING,ERROR}

    public static void initLogger(){
        LOGGER = Logger.getLogger("logger_app");
    }

    public static void log(LOGTYPE type, String message) {
        switch (type){
            case INFO:
                logInfo(message);
                break;
            case WARNING:
                logWarning(message);
                break;
            case ERROR:
                logError(message);
                break;
            default:

        }
    }

    public static void logInfo(String message){
        LOGGER.log(Level.INFO, message);
    }

    public static void logWarning(String message){
        LOGGER.log(Level.WARNING, message);
    }

    public static void logError(String message){
        LOGGER.log(Level.SEVERE, message);
    }


}
