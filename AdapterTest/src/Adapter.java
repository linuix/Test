import java.util.Arrays;

public class Adapter extends Source implements Targetable {


    @Override
    public void doit1() {
        System.out.println("doit1");
    }


    public static void main(String[] args) {
//        Targetable target = new Adapter();
//        target.doit();
//        target.doit1();

//        Source source = new Source();
//        Targetable target = new Wrapper(source);
//        target.doit();
//        target.doit1();
//        int oldDataLength = 2;
//        byte oldData[] = new byte[oldDataLength];
//        byte data[] = new byte[]{11,22,33,44};
//        int length = 4;
//        byte tempData[] = {12,13};
//        System.arraycopy(data,length-oldDataLength,tempData,0,oldDataLength);
//        System.arraycopy(data,0,data,oldDataLength,length-oldDataLength);
//        System.arraycopy(oldData,0, data,0,oldDataLength);
//        System.arraycopy(tempData,0,oldData,0,oldDataLength);
//
//        System.out.println(Arrays.toString(data));
//        System.out.println(Arrays.toString(oldData));
//
//        data = new byte[] {55,66,77,88};
//
//        System.arraycopy(data,length-oldDataLength,tempData,0,oldDataLength);
//        System.arraycopy(data,0,data,oldDataLength,length-oldDataLength);
//        System.arraycopy(oldData,0, data,0,oldDataLength);
//        System.arraycopy(tempData,0,oldData,0,oldDataLength);
//
//
//        System.out.println(Arrays.toString(data));
//        System.out.println(Arrays.toString(oldData));

        System.out.println("start..........");
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("run start");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("run end");
            }
        }).start();
        System.out.println("main end..........");

    }
}
