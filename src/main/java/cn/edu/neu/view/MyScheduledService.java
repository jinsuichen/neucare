package cn.edu.neu.view;

import javafx.concurrent.ScheduledService;
import javafx.concurrent.Task;
import javafx.scene.control.DialogPane;
import javafx.stage.Stage;

public class MyScheduledService extends ScheduledService {

    private DialogPane dialogPane;
    private Stage stage;
    int i = 0;

    public MyScheduledService(DialogPane dialogPane, Stage stage) {
        this.dialogPane = dialogPane;
        this.stage = stage;
    }

    @Override
    protected Task createTask() {

        return new Task<Integer>() {

            //不是UI线程，也不是application线程，所以不能更新组件
            //在这里做复杂多任务，将值传递给updateValue
            @Override
            protected Integer call() throws Exception {
                System.out.println("call " + Thread.currentThread().getName());
                return i = i + 1;
            }

            //UI线程，可以更新组件
            //在这里更新控件
            @Override
            protected void updateValue(Integer value) {
                System.out.println("updateValue " + Thread.currentThread().getName());

                System.out.println("updateValue的值为"+value);

                if(i<=10){
                    dialogPane.setContentText(i+"");
                }else{
                    //取消任务 注意这里一定要这么写，才能关闭每一次调用的task
                    MyScheduledService.this.cancel();
                    stage.close();
                }

            }
        };

    }
}
