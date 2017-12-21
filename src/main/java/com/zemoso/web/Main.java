package com.zemoso.web;

import java.io.*;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;

public class Main {
    public static void main(String[] args) throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException, CertificateException {
/*
        System.setProperty("org.apache.commons.logging.Log","org.apache.commons.logging.impl.SimpleLog");
        System.setProperty("org.apache.commons.logging.simplelog.showdatetime", "true");
        System.setProperty("org.apache.commons.logging.simplelog.log.org.apache.http.wire", "DEBUG");
*/

        addCertificate();

        PlayStoreCrawler playStoreCrawler = new PlayStoreCrawler("com.wecreatestuff.interlocked");

        playStoreCrawler.crawl();
    }

    public static void addCertificate() throws KeyStoreException, CertificateException, NoSuchAlgorithmException, IOException {
        KeyStore keyStore = KeyStore.getInstance("JKS");
        //String fileName = new File("keystore.jks").getCanonicalPath(); // cerrtification file path
        //System.out.println(fileName);
        System.setProperty("javax.net.ssl.trustStore", "/home/keystore.jks");
        System.setProperty("javax.net.ssl.trustStorePassword", "changeit");
    }
}
