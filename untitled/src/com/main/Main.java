package com.main;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class Main {

    public static void main(String[] args) {

        final String dir = System.getProperty("user.dir");
        System.out.println("current dir = " + dir + "\\src\\com\\main");

        Logger logger = Logger.getLogger("MyLog");
        FileHandler fh;

        try {

            // This block configure the logger with handler and formatter
            fh = new FileHandler(dir + "\\src\\com\\main\\file.log");
            logger.addHandler(fh);
            SimpleFormatter formatter = new SimpleFormatter();
            fh.setFormatter(formatter);

            // the following statement is used to log any messages
            logger.info("My first log");

        } catch (SecurityException | IOException e) {
            e.printStackTrace();
        }

        logger.info("Hi How r u?");
    }
}
