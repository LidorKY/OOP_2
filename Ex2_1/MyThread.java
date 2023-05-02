package Ex2.Ex2_1;

import java.io.*;

public class MyThread extends Thread{

    private String file;
    private int counter;

    public MyThread(String f){
        super();
        this.file = ""+f;
        counter = 0;
    }


    public int getCounter(){
        return this.counter;
    }


     public void run(){
        String line;
        try{
            FileReader fileReader = new FileReader(this.file);
            BufferedReader reader = new BufferedReader(fileReader);
            line = reader.readLine();
            while(line!=null){
                line = reader.readLine();
                this.counter++;
            }
        }
        catch(FileNotFoundException e){
            System.out.println("An error occurred with fileReader.");
            e.printStackTrace();
        }
        catch(IOException e2) {
            System.out.println("An error occurred with BufferReader.");
            e2.printStackTrace();
        }
    }
}
