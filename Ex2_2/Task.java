package Ex2.Ex2_2;

import java.util.concurrent.Callable;
import java.io.*;
import java.util.concurrent.Future;

public class Task<T> implements Callable ,Comparable {

    private Callable func ;
    private int priority;

    private Task(Callable func, TaskType task){
        this.func = func;
        priority = task.getPriorityValue();
    }
    private Task(Callable func){
        this.func = func;
        priority=3;
    }

    //Factory//
    public static Task createTask(Callable func, TaskType task){
        return new Task(func,task);
    }
    //default Factory//
    public static Task createTask(Callable func){
        return new Task(func);
    }

    @Override
    public Object call() throws Exception {
        return this.func.call();
    }
    @Override
    public int compareTo(Object t1){

        if(this.priority< ((Task) t1).priority){
            return 1;
        }
        else if(this.priority> ((Task) t1).priority){
            return -1;
        }
        else{
            return 0;
        }
    }
    public int getPriority(){
        return this.priority;
    }
}
