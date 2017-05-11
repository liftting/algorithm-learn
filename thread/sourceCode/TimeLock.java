package sourceCode;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * Created by wm on 17/5/10.
 */

public class TimeLock implements Lock {

    private Sync sync = new Sync(2);

    private static class Sync extends AbstractQueuedSynchronizer {

        public Sync(int count) {
            if (count <= 0) {
                throw new IllegalArgumentException("");
            }

            setState(count);
        }

        @Override
        protected int tryAcquireShared(int descCount) {

            for (; ; ) {

                int curCount = getState();
                int newCount = curCount - descCount;
                if (newCount < 0 || compareAndSetState(curCount, newCount)) {
                    return newCount;
                }
            }

        }

        @Override
        protected boolean tryReleaseShared(int incCount) {
            for (; ; ) {
                int current = getState();
                int newCount = current + incCount;
                if (compareAndSetState(current, newCount)) {
                    return true;
                }
            }
        }
    }

    @Override
    public void lock() {
        sync.acquireShared(1);
    }

    @Override
    public void lockInterruptibly() throws InterruptedException {
        sync.acquireInterruptibly(1);
    }

    @Override
    public boolean tryLock() {
        return sync.tryAcquireShared(1) >= 0;
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return sync.tryAcquireNanos(1,unit.toNanos(time));
    }

    @Override
    public void unlock() {
        sync.releaseShared(1);
    }

    @Override
    public Condition newCondition() {
        return null;
    }
}
