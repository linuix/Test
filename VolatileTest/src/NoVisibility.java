import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;

public class NoVisibility {
    private static boolean ready = false;
    private static int number;

    private static class ReaderThread extends Thread {
        @Override
        public void run() {
            while (!ready) {
                Thread.yield();
            }
            System.out.println(number);
        }
    }

    public static void main(String[] args) {
        byte srcData[] =    {   1,2,3,4,5,6,7,8,
                                1,2,3,4,5,6,7,8,
                                1,3,3,4,5,6,7,8,
                                1,2,3,4,5,6,7,8,
                                1,2,3,4,5,6,7,8};
        byte targetData[] = new byte[srcData.length/4];
        int length = readDataFromSrc(0,srcData,srcData.length,targetData,2);
        System.out.println("length = "+length+"----\n"+Arrays.toString(targetData));
    }

    private static int readDataFromSrc(int index, byte srcData[],int srcLength, byte target[],int targetLength){
        int i =0;
        for (i = index; i < srcLength;i+=8){
            System.arraycopy(srcData,i,target,(i/8)*2,2);
        }
        return i/4;
    }
}
