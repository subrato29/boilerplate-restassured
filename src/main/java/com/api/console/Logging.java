package com.api.console;

import freemarker.log.Logger;

public class Logging {

    static Logger logger = Logger.getLogger("devpinoyLogger");

    public static String info(String console) {
        logger.info(console);
        return console;
    }

    public static String debug(String console) {
        logger.debug(console);
        return console;
    }

    public static String error(String console) {
        logger.error(console);
        return console;
    }
}
