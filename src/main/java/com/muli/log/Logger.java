package com.muli.log;

/**
 * Created by muli on 15-5-11.
 */
public class Logger {
    public static org.slf4j.Logger Logger = org.slf4j.LoggerFactory.getLogger(Logger.class);

    public static void main(String[] args){
        int i;
        System.out.println("==================");
        for (i = 0; i < 10; i++){
            Logger.debug("-----------info :" + i);
            Logger.info("-----------info :" + i);
            Logger.warn("-----------info :" + i);
            Logger.error("-----------info :" + i);
            Logger.info("======");
        }
        System.out.println("==================");
    }
}
