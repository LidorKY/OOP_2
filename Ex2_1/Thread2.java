package Ex2.Ex2_1;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.concurrent.Callable;

public class Thread2 implements Callable<Integer> {

    String name;

    public Thread2(String name){
        super();
        this.name = name;
    }

    public Integer call(){
        int counter = 0;
        String line;
        try{
            FileReader fileReader = new FileReader(name);
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
        catch(IOException e2) {
            System.out.println("An error occurred with BufferReader.");
            e2.printStackTrace();
        }
        return counter;
    }
}
