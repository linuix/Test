public class Adapter extends Source implements Targetable {


    @Override
    public void doit1() {
        System.out.println("doit1");
    }


    public static void main(String[] args) {
//        Targetable target = new Adapter();
//        target.doit();
//        target.doit1();

        Source source = new Source();
        Targetable target = new Wrapper(source);
        target.doit();
        target.doit1();
    }
}
