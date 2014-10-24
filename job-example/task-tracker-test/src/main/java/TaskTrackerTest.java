import com.lts.job.common.constant.Constants;
import com.lts.job.task.tracker.TaskTracker;

import java.io.IOException;

/**
 * @author Robert HG (254963746@qq.com) on 8/19/14.
 */
public class TaskTrackerTest {

    public static void main(String[] args) {
        final TaskTracker taskTracker = new TaskTracker();
        taskTracker.setJobRunnerClass(TestJobRunner.class);

        taskTracker.setZookeeperAddress("localhost:2181");
        taskTracker.setNodeGroup("TEST_TRADE");
        taskTracker.setClusterName("QN");
        taskTracker.setWorkThreads(20);
        taskTracker.setJobInfoSavePath(Constants.USER_HOME + "/.job");
        taskTracker.start();

        Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
            @Override
            public void run() {
                taskTracker.stop();
            }
        }));

        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}