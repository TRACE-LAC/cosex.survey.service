package com.monkeypox.survey.service.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class JsonUtility {

    public static String objectToJson(Object object) {
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String json = null;
       
        try {
            json = ow.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            log.error("Error convirtiendo el objeto a JSON: " + object.toString(), e);
        }
        return json;
    }

    public void createSimpleMail() {
        SimpleEmail email = new SimpleEmail();

        email.setHostName("smtp.office365.com");
        email.setSmtpPort(587);
        email.setAuthenticator(new DefaultAuthenticator("proofmentes@outlook.com", "Pruebas22"));
        email.setStartTLSEnabled(true);
        try {
            email.setFrom("proofmentes@outlook.com");
            email.setSubject("Job Failure");
            email.setDebug(true);
            email.setMsg("This is a test mail ... :-)" );
            email.addTo("a@y.com");
            email.send();
        } catch (EmailException e) {
            e.printStackTrace();
        }
    }
}