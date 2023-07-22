package com.pe.aseyco.technical.challenge.core;

import org.springframework.beans.factory.BeanCreationException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MainApplication {

    public static void main(String[] args) {
        try {
            SpringApplication.run(MainApplication.class, args);
        } catch (BeanCreationException e) {
            Throwable realCause = unwrap(e);
            realCause.printStackTrace();
        }
    }

    public static Throwable unwrap(Throwable throwable) {
        if (throwable != null && BeanCreationException.class.isAssignableFrom(throwable.getClass())) {
            return unwrap(throwable.getCause());
        } else {
            return throwable;
        }
    }
    
}
