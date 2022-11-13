package com.api.console;

import freemarker.log.Logger;

public class Logging {

    static Logger logger = Logger.getLogger("e2e-argos-test");

    public static void info(String console) {
        logger.info(console);
    }

    public static void debug(String console) {
        logger.debug(console);
    }

    public static void error(String console) {
        logger.error(console);
    }
}
