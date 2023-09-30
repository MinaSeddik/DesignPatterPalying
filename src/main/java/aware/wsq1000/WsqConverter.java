package aware.wsq1000;

import com.aware.WSQ1000.IWSQ1000;
import com.aware.WSQ1000.ImageFormat;
import com.aware.WSQ1000.WSQ1000Exception;
import com.aware.WSQ1000.WSQ1000JNI;
import org.apache.commons.math3.util.Pair;

public class WsqConverter {

    public static void main(String[] args) {

//        String inputWsq = "/home/mina/1.wsq";
//        String outputBmp = "/home/mina/aware.1.bmp";

        String inputWsq = "C:\\Users\\Administrator\\Desktop\\1.wsq";
        String outputBmp = "C:\\Users\\Administrator\\Desktop\\aware.1.bmp";

        WsqConverter converter = new WsqConverter();
        converter.wsq2Bmp(inputWsq, outputBmp);


    }

    public Pair<Integer,Integer> wsq2Bmp(String inputWsq, String outputBmp) {
        IWSQ1000 wsq1000 = null;
        Pair<Integer, Integer> scores = null;

        try {

            // AwWsq_Create - Page 14
            wsq1000 = new WSQ1000JNI();

            // AwWsq_SetInputImage - Page 17
            // AWWSQ FORMAT WSQ = ImageFormat.WSQ = 3
            // AwWsq_ReadInputImage - Page 20
            wsq1000.readInputImage(ImageFormat.WSQ, inputWsq);

            // AwWsq_GetQualityScore - Page 84
            int score = wsq1000.getQualityScore();

            // AwWsq_GetNfiqScore - Page 85
            int nfiqScore = wsq1000.getNfiqScore();

            System.out.println("Score: " + score);
            System.out.println("NFIQ Score: " + nfiqScore);

            // AwWsq_SetOutputResolution - Page 53
            wsq1000.setOutputResolution(500d);

            // AwWsq_WriteOutputImage - Page 62
            wsq1000.writeOutputImage(ImageFormat.BMP, outputBmp);

            scores = new Pair<>(score, nfiqScore);

        } catch (WSQ1000Exception ex) {
            System.out.println("Exception : " + ex);
        } finally {
            if (wsq1000 != null)
                // AwWsq_Destroy - Page 15
                wsq1000.destroy();
        }

        return scores;
    }
}
