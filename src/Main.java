import java.util.concurrent.Semaphore;

import static java.lang.Thread.sleep;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        Person[] persons = new Person[5];
        Semaphore[] dua = new Semaphore[5];
        for(int i=0; i<5; i++){
            dua[i] = new Semaphore(1);
        }
        for(int i = 0; i < 5; i++)
        {

            if(i % 2 == 0)
            {
                persons[i] = new Person(dua[i], dua[(i+1)%5], i);
            }
            else
            {
                persons[i] = new Person(dua[(i+1)%5],dua[i] , i);
            }
        }
        for(int i = 0; i < 5; i++)
        {
            persons[i].start();
        }
        while (true)
        {
            for(int i = 0; i < 5; i++)
            {
                System.out.println(persons[i].pNumber + " is " + persons[i].status);
            }
            System.out.println("----------");
            sleep(2000);
        }
    }
}