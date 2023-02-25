package sharedobjectlib;

public class Main {


    public static native int[] ReadImageFromFile(int[] dimension, String fileName);
    public static native void SaveImageToFile(int[] data, int width, int height, String fileName, int type);
    public static native void RegisterWSQ();


    public static void main(String[] args) {

        System.loadLibrary("WSQ_library_jniexport64");

        int [] dimenssion = {1600, 1500};
        String fileName = "/home/mina/Desktop/base64_decoded_image.wsq";
        int[] data = ReadImageFromFile(dimenssion, fileName);

        String outputfileName = "/home/mina/Desktop/base64_decoded_image.png";
        SaveImageToFile(data, dimenssion[0], dimenssion[1], outputfileName, 4 /* PNG type */ );
    }
}
