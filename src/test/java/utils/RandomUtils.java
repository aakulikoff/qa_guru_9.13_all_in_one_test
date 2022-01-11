package utils;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.*;

import java.sql.Timestamp;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class RandomUtils {


    public static String getRandomStringText(int length) {
        String SALTCHARS = "йцукенгшщзхъфывапролджэёячсмитьбюЙЦУКЕНГШЩЗХЪЁЭЖДЛОРПАВЫФЯЧСМИТЬБЮ";
        StringBuilder result = new StringBuilder();
        Random rnd = new Random();
        while (result.length() < length) {
            int index = (int) (rnd.nextFloat() * SALTCHARS.length());
            result.append(SALTCHARS.charAt(index));
        }
        return result.toString();
    }



}
