package daemon;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @author xucheng.liu
 * @date 2019/7/23
 */
public class Test {

    public static void main(String[] args) {
        Thread thread = new Thread(new TestRunnable());
        thread.setDaemon(true);
        thread.start();
    }

    static class TestRunnable implements Runnable {

        @Override
        public void run() {
            FileOutputStream os = null;
            try{
                Thread.sleep(1000);

                File f = new File("daemon.txt");
                os = new FileOutputStream(f,true);
                os.write("daemon".getBytes());
            } catch(Exception e) {
                e.printStackTrace();
            } finally {
                if (os != null) {
                    try {
                        os.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
