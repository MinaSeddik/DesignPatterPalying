package Cryptographic;

import org.springframework.boot.autoconfigure.web.ServerProperties;

import javax.net.ssl.SSLServerSocket;
import javax.net.ssl.SSLServerSocketFactory;
import java.io.IOException;

public class ServerSecuredSocket {


    public static void main(String[] args) throws IOException {
        SSLServerSocketFactory sslserversocketfactory = (SSLServerSocketFactory) SSLServerSocketFactory
                .getDefault();
        SSLServerSocket sslserversocket = (SSLServerSocket) sslserversocketfactory
                .createServerSocket(443);

    }
}
