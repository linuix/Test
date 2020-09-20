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
        final QueueBlockingCache queueBlockingCache = new QueueBlockingCache(16000 * 2 * 4);

        try {
            fileOutputStream = new FileOutputStream("C:\\Users\\uixli\\Desktop\\workspaces\\hobotTest\\hobot2mic_pre_out_4196137.pcm");
            fileInputStream = new FileInputStream("C:\\Users\\uixli\\Desktop\\workspaces\\hobotTest\\hobot2mic_pre_4196137.pcm");
            new Thread(new Runnable() {
                @Override
                public void run() {
                    byte buffer[] = new byte[2500];
                    int size = 0;
                    try {
                        while ((size = fileInputStream.read(buffer)) > 0) {
                            queueBlockingCache.write(buffer, 0, size);
                            System.out.println("read size = " + size);
                            Thread.sleep(5);
                        }
                        System.out.println("read end size = " + size);

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
                        Thread.sleep(20);
                        int i = 0;
                        int offset = 0;
                        while ((offset = queueBlockingCache.read(buffer, size, buffer.length - size)) > 0) {
                            System.out.println("write size = " + size);
                            i++;
                            size += offset;
                            if (size != 2048) {
                                System.out.println("write size--------------------------- = " + size);
                                continue;
                            }
                            fileOutputStream.write(buffer, 0, size);
                            if (i % 4 == 0) {
                                Thread.sleep(2);
                            } else {
                                Thread.sleep(1);
                            }
                            size = 0;
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
