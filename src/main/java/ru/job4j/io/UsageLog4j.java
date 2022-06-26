package ru.job4j.io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UsageLog4j {

    private static final Logger LOG = LoggerFactory.getLogger(UsageLog4j.class.getName());

    public static void main(String[] args) {
        boolean flag = true;
        char letter = 'a';
        byte byteNum = 127;
        short shortNum = 12500;
        int intNum = 13;
        long longNum = 57L;
        float floatNum = 0.75F;
        double doubleNum = 3.14;
        LOG.debug("Eight primitive types : flag : {}, letter : {}, byteNum : {}, shortNum : {}, "
                        + "intNum : {}, longNum : {}, floatNum : {}, doubleNum : {}",
                flag, letter, byteNum, shortNum, intNum, longNum, floatNum, doubleNum);
    }
}
