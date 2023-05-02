package Ex2.Ex2_1;

import java.io.*;
import java.util.ArrayList;
import java.util.Random;
import java.io.BufferedReader;
import java.util.concurrent.*;

public class Ex2_1 {
    ////////////////Q1/////////////
    public static String[] createTextFiles(int n, int seed, int bound){
        int i;
        String index;
        Random rand = new Random(seed);
        String [] file_arr = new String[n];

        for(i=0;i<n;i++){
            index = Integer.toString(i);
            File f = new File("trash files\\file_"+index+".txt");
            try{
                FileWriter fileWriter = new FileWriter(f);
                int k = rand.nextInt(bound);
                //System.out.println(k);
                for(int j=0;j<k;j++){
                    fileWriter.write("hello world\n");
                }
                fileWriter.close();
                file_arr[i]="trash files\\file_"+index+".txt";
            }
            catch (IOException e) {
                System.out.println("An error occurred with filewriter.");
                e.printStackTrace();
            }
        }
        return file_arr;
    }
    /////////////////Q2/////////////////////
    public static int getNumOfLines(String[] fileNames){
        String line;
        int counter=0;
        for(int i=0;i<fileNames.length;i++){
            try{
                FileReader fileReader = new FileReader(fileNames[i]);
                BufferedReader reader = new BufferedReader(fileReader);
                line = reader.readLine();
                while(line!=null){
                    line = reader.readLine();
                    counter++;
                }
            }
            catch(FileNotFoundException e){
                System.out.println("An error occurred with fileReader.");
                e.printStackTrace();
            }
            catch(IOException e2){
                System.out.println("An error occurred with BufferReader.");
                e2.printStackTrace();
            }
        }
        return counter;
    }
////////////////Q3///////////////
public static int getNumOfLinesThreads(String[] fileNames) {
    int counter = 0;
    MyThread thread_array [] = new MyThread [fileNames.length];
    try {
        for (int i = 0; i < fileNames.length; i++) {
            thread_array[i]= new MyThread(fileNames[i]);
            thread_array[i].start();
        }
        for(int i = 0; i < thread_array.length; i++){
            if(!thread_array[i].isAlive()) {
                counter = counter + thread_array[i].getCounter();
            }
            else{
                thread_array[i].join();
                counter = counter + thread_array[i].getCounter();
            }
        }
    }
    catch(Exception e){
        e.printStackTrace();
    }
    return counter;
}
//////////////Q4/////////////////////
    public static int getNumOfLinesThreadPool(String[] fileNames){

        ExecutorService executor = (ThreadPoolExecutor)Executors.newFixedThreadPool(fileNames.length);
        int ans =0;
        ArrayList <Thread2> Threads = new ArrayList<>();
        for (int i=0;i<fileNames.length;i++){
            Threads.add(new Thread2(fileNames[i]));
        }
        try {
            for (Future<Integer> f :(ArrayList<Future<Integer>>) executor.invokeAll(Threads)) {
                ans += f.get();
            }
        }
        catch(Exception e){
            System.out.println("There is problem");
            e.printStackTrace();
        }
        executor.shutdown();
        return ans;
    }
}
