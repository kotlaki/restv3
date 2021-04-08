package org.kerganov.restv3;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.rest.RestBindingMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/* Во время решения задачи мной было придумано несколько вариантов реализации:
    1. Думаю самый правильный, это вытащить информацию из pom.xml, из pom.properties, с использованием MavenXpp3Reader
    или при помощи средств фреймворка. Выделить ее и передать например в очередь, для дальнейшего вывода
    в браузере как ответа. К сожалению, я не смог реализовать этот вариант((( Не хватило времени для изучения apache camel.
    2. Мое решение с использованием статического метода и статической переменной, для упрощения доступа к
    выделенной информации. Понимаю что плохо использовать статику в коде.
*/

@Component
public class RestRoute extends RouteBuilder {

    @Autowired
    private Info info;

    @Override
    public void configure() throws Exception {
        restConfiguration().component("servlet").bindingMode(RestBindingMode.json);
        rest()
                .get("/stat")
                .route()
                .process(new Processor() {
                    @Override
                    public void process(Exchange exchange) throws Exception {
                        // вытащил информацию через статический метод и переменную
                        String str = Info.message;
                        exchange.getMessage().setBody(str);
                    }
                })
                .to("log:myLogger?showAll=true")
                .endRest();

        //считываем информацию из pom.xml, передаем её в bean для поиска нужной
        // есть еще способ для считывания информации о версии и имени
        // артефакта при помощи MavenXpp3Reader. Пример есть в PomReader.java
        // выражение ../ означает родительскую директорию
        from("file://E:\\work_temp\\project\\restv3/?fileName=pom.xml&noop=true")
                .bean(info, "infoArtifact(${body})");

    }

}
