package Ex2.Ex2_1;

import java.util.concurrent.ExecutionException;


public class Main {
    public static void main(String[] args){
        String [] arr = Ex2_1.createTextFiles(10000, 50, 100);


        long start =System.currentTimeMillis();
        System.out.println("q2 answer = "+Ex2_1.getNumOfLines(arr));
        long end = System.currentTimeMillis();
        System.out.println("q2 time ="+(end -start)*0.001);

        start =System.currentTimeMillis();
        System.out.println("q3 answer = "+Ex2_1.getNumOfLinesThreads(arr));
        end =System.currentTimeMillis();
        System.out.println("q3 time ="+(end -start)*0.001);

        start =System.currentTimeMillis();
        System.out.println("q4 answer = "+Ex2_1.getNumOfLinesThreadPool(arr));
        end =System.currentTimeMillis();

        System.out.println("q4 time ="+(end -start)*0.001);



    }

}
