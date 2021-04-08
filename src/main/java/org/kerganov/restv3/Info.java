package org.kerganov.restv3;

import org.springframework.stereotype.Component;

/*
    Немного хардкода)))
    Получаем информацию из pom.xml и выделяем нужную.
 */

@Component
public class Info {

    public static String message;

    public static void infoArtifact(String msg) {
        String[] artifactId = msg.split("<artifactId>|\\</artifactId>");
        String[] artifactVersion = msg.split("<version>|\\</version>");
        message = "name: " + artifactId[3] + "; version: " + artifactVersion[3];
    }

}
