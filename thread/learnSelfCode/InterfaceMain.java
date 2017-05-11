package learnSelfCode;

/**
 * Created by wm on 17/5/9.
 */

public class InterfaceMain {

    public interface MainA {
        void say();
    }

    public interface MainB {
        void show();
    }

    public interface MainC extends MainA, MainB {
        void run();
    }

    public class InMain implements MainC {

        @Override
        public void run() {
            
        }

        @Override
        public void say() {

        }

        @Override
        public void show() {

        }
    }

}
