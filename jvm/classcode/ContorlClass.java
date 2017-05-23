package classcode;

/**
 * Created by wm on 17/5/22.
 */

public class ContorlClass {

    public int getMore(float floatOne, float floatTwo) {
        int result;
        if (floatOne > floatTwo) {
            result = 1;
        } else {
            result = 2;
        }
        return result;
    }

    public int simpleSitch(int key) {
        switch (key) {
            case 0:
                return 3;
            case 1:
                return 2;
            case 4:
                return 1;
            default:
                return -1;
        }
    }

    public void whileLoop() {
        int i = 0;
        while (i < 2) {
            i++;
        }
    }

    public void forLoop() {
        for(int i = 0; i < 2; i++) {
            //do nothing
        }
    }

    public void doWhileLoop() {
        int i = 0;
        do {
            i++;
        } while (i < 2);
    }

}
