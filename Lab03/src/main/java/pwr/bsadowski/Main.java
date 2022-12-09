/**
 * @author Bartosz Sadowski
 *gradlew build
 *gradlew jar
 *java -cp Lab03_pop.jar <all-class-path>
 */
package pwr.bsadowski;

import pwr.bsadowski.tools.DataWriter;

import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        int[] time = {1, 2, 3, 4, 5, 6, 7};
        int j=0;

        for(int i = 0; i < time.length; i++){
            String toFile = j+""+time[i];

            System.out.println(toFile);

            DataWriter writer = new DataWriter();
            writer.writeFile("Time.txt", toFile);

            TimeUnit.SECONDS.sleep(5);

            if(i == time.length-1){
                i = -1;
                j++;
            }
        }
    }
}