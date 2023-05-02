package Ex2.Ex2_2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Objects;
import java.util.concurrent.*;

public class CustomExecutor extends ThreadPoolExecutor{
    private int Max_Priority;
    public int [] priority_arr;

    //constructor//

    public CustomExecutor(){
        super(Runtime.getRuntime().availableProcessors()/2
                ,Runtime.getRuntime().availableProcessors()-1,300,TimeUnit.MILLISECONDS
                ,new PriorityBlockingQueue<>());
        priority_arr = new int[3];
        Max_Priority=0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CustomExecutor that = (CustomExecutor) o;
        return Max_Priority == that.Max_Priority && Arrays.equals(priority_arr, that.priority_arr);
    }
    @Override
    public int hashCode() {
        int result = Objects.hash(Max_Priority);
        result = 31 * result + Arrays.hashCode(priority_arr);
        return result;
    }
    //1//
    public Future submit(Task task) throws Exception {
        if(this.getQueue().size()==Runtime.getRuntime().availableProcessors()-1) {//checking if there is more space for new threads.
            return null;
        }
        priority_arr[task.getPriority()-1]++;
        return super.submit(task);
    }
    //2//
    public Future submit(Callable c,TaskType t) {
        Task task =  Task.createTask(c,t);
        Future f = null;
        try {
             f = submit(task);
        }
        catch(Exception e){
            System.out.println("There is problem with submit");
            e.printStackTrace();
        }
        return f;
    }
    //3//
    public Future submit(Callable c){
        Task task = Task.createTask(c);
        Future f = null;
        try {
            f = submit(task);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return f;
    }
    //10//
    public int getCurrentMax(){
        if(priority_arr[0]>0){
            this.Max_Priority = 1;
            return 1;
        }
        else if(priority_arr[1]>0){
           this.Max_Priority = 2;
            return 2;
        }
        else if(priority_arr[2]>0){
            this.Max_Priority = 3;
            return 3;
        }
        return 0;
    }
    //11.b//
    public void executeAll(){
        try {
            for (Runnable r : this.getQueue()) {
                ((Task) r).call();
            }
        }
        catch(Exception e){
            System.out.println("There is an error with executeAll");
            e.printStackTrace();
        }
    }
    //11.c//
    public void gracefullyTerminate(){
       this.close();// After a thread had finished, the executor removing him from the priorityBlockingQueue
    }
    @Override
    protected void beforeExecute(Thread t, Runnable r){
        if(getCurrentMax()!=0){
            priority_arr[getCurrentMax()-1]--;

        }
    }
    /*public int lookAtArray(){
        if(priority_arr[0]>0){
            return 1;
        }
        else if(priority_arr[1]>0){
            return 2;
        }
        else if(priority_arr[2]>0){
            return 3;
        }
        return 0;
    }
*/
}


