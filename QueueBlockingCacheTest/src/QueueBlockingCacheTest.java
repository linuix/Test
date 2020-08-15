import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @description:
 * @author: uixli
 * @time: 2020-2020/1/12 0012-12:30
 */
public class QueueBlockingCacheTest {
    static FileOutputStream fileOutputStream;
    private static FileInputStream fileInputStream;

    public static void main(String[] args) {
        final QueueBlockingCache queueBlockingCache = new QueueBlockingCache(16000*2*4);

        try {
            fileOutputStream = new FileOutputStream("C:\\Users\\uixli\\Desktop\\workspaces\\hobot\\cacheTest\\hobot_out.pcm");
            fileInputStream = new FileInputStream("C:\\Users\\uixli\\Desktop\\workspaces\\hobot\\cacheTest\\hobot_out11489391.pcm");
            new Thread(new Runnable() {
                @Override
                public void run() {
                    byte buffer[] = new byte[2048];
                    int size = 0;
                    try {
                        while ((size = fileInputStream.read(buffer))>0){
                            queueBlockingCache.write(buffer,0,size);
                            System.out.println("read size = "+size);
                            Thread.sleep(5);
                        }
                        System.out.println("read end");

                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }).start();

            new Thread(new Runnable() {
                @Override
                public void run() {
                    byte buffer[] = new byte[2048];
                    int size = 0;
                    try {
                        Thread.sleep(2000);
                        int i = 0;
                        while ((size = queueBlockingCache.read(buffer,0,buffer.length))>0){
                            i++;
                            fileOutputStream.write(buffer,0,size);
                            System.out.println("write size = "+size);
                            if (i%4 ==0){
                                Thread.sleep(20);
                            }else {
                                Thread.sleep(10);
                            }
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }).start();


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }
}
