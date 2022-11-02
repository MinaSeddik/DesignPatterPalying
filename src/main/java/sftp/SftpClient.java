package sftp;

import com.jcraft.jsch.*;

import java.util.Objects;

public class SftpClient {

    private static final String HOST = "devfile.nbinformation.com";
    private static final int PORT = 54689;
    private static final String PRIVATE_KEY = "/home/mina/cert2d_rsa.crt";
    private static final String KNOWN_HOSTS_FILE = "/home/mina/.ssh/known_hosts";
    private static final String PRIVATE_KEY_PASSWORD = "!a=ho8AFI$8R";
    private static final String USER = "cert2d";
    private static final String SFTP_DESTINATION_DIR = "./cert2d/";


    public static void main(String[] args) {
        SftpClient sftpClient = new SftpClient();
        ChannelSftp channelSftp = null;

        try {
            channelSftp = sftpClient.setupJsch();

            // put file
            String fileName = "/home/mina/Desktop/CERT2D2203081709DES61U.eft";

            System.out.println("Put " + fileName + " to " + SFTP_DESTINATION_DIR);
            channelSftp.put(fileName, SFTP_DESTINATION_DIR);

        } catch (JSchException | SftpException e) {
            System.out.println(e.getClass().getName() + ": " + e.getMessage());

        } finally {
            if (Objects.nonNull(channelSftp)) {
                try {
                    channelSftp.getSession().disconnect();
                    channelSftp.exit();
                } catch (JSchException e) {
                }
                System.out.println("shell channel Disconnected.");
            }
        }

        System.out.println("Done");

    }


    private ChannelSftp setupJsch() throws JSchException {
        JSch jsch = new JSch();

        jsch.addIdentity(PRIVATE_KEY, PRIVATE_KEY_PASSWORD);
        System.out.println("identity added ");

        // ssh-keyscan -H -p 54689 devfile.nbinformation.com >> ~/.ssh/known_hosts
        jsch.setKnownHosts(KNOWN_HOSTS_FILE);

        Session session = jsch.getSession(USER, HOST, PORT);
        System.out.println("session created.");

        // disabling StrictHostKeyChecking may help to make connection but makes it insecure
        // see http://stackoverflow.com/questions/30178936/jsch-sftp-security-with-session-setconfigstricthostkeychecking-no
        //
//         java.util.Properties config = new java.util.Properties();
//         config.put("StrictHostKeyChecking", "no");
//         session.setConfig(config);

        session.connect();
        System.out.println("session connected.....");

        Channel channel = session.openChannel("sftp");
        channel.setInputStream(System.in);
        channel.setOutputStream(System.out);
        channel.connect();
        System.out.println("shell channel connected....");


        return (ChannelSftp) channel;
    }

}
