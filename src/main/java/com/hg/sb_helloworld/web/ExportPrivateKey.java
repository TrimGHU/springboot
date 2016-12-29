package com.hg.sb_helloworld.web;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.security.Key;
import java.security.KeyPair;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.UnrecoverableKeyException;
import java.security.cert.Certificate;
import java.security.cert.CertificateFactory;

import javax.security.cert.X509Certificate;

import sun.misc.BASE64Encoder;

@SuppressWarnings( "restriction" )
public class ExportPrivateKey
{
    private File keystoreFile = new File("C:\\Users\\ubt\\Desktop\\ubtrobot.done.keystore");
    private String keyStoreType = KeyStore.getDefaultType();
    private char[] password = "Ubt83474428".toCharArray();
    private String alias = "tomcat";
    private File exportedFile = new File("C:\\Users\\ubt\\Desktop\\ubtrobot.done.key");

    public static KeyPair getPrivateKey( KeyStore keystore, String alias, char[] password )
    {
        try
        {
            Key key = keystore.getKey( alias, password );
            if ( key instanceof PrivateKey )
            {
                Certificate cert = keystore.getCertificate( alias );
                PublicKey publicKey = cert.getPublicKey();
                return new KeyPair( publicKey, (PrivateKey)key );
            }
        } catch ( UnrecoverableKeyException e )
        {
        } catch ( NoSuchAlgorithmException e )
        {
        } catch ( KeyStoreException e )
        {
        }
        return null;
    }

    public void export() throws Exception
    {
        KeyStore keystore = KeyStore.getInstance( keyStoreType );
        BASE64Encoder encoder = new BASE64Encoder();
        keystore.load( new FileInputStream( keystoreFile ), password );
        KeyPair keyPair = getPrivateKey( keystore, alias, password );
        PrivateKey privateKey = keyPair.getPrivate();
        String encoded = encoder.encode( privateKey.getEncoded() );
        FileWriter fw = new FileWriter( exportedFile );
        fw.write( "—–BEGIN PRIVATE KEY—–/n" );
        fw.write( encoded );
        fw.write( "/n" );
        fw.write( "—–END PRIVATE KEY—–" );
        fw.close();

    }
    
    public void exportPublicKey() throws Exception
    {
        FileInputStream file = new FileInputStream("C:\\Users\\ubt\\Desktop\\ubtrobot.csr");
        CertificateFactory ft = CertificateFactory.getInstance("X.509");
        Certificate certificate = ft.generateCertificate(file);
        PublicKey publicKey = certificate.getPublicKey();
        BASE64Encoder b64 = new BASE64Encoder();
        System.out.println("-----BEGIN PUBLIC KEY-----");
        System.out.println(b64.encode(publicKey.getEncoded()) );
        System.out.println("-----END PUBLIC KEY-----");

    }

    public static void main( String args[] ) throws Exception
    {
        ExportPrivateKey export = new ExportPrivateKey();
        //export.export();
        export.exportPublicKey();
    }
}