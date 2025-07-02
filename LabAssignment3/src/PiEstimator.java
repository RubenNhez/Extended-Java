import java.util.Random;

public class PiEstimator {
    //number of iterations the algorithm, a larger number here should result in a more accurate estimate
    public static void main(String[] args) throws InterruptedException {
         int numberOfDarts = 1_000_000_000;


        // Create Threads and divide number of darts by 4 as there are 4 threads
        TotalWithin Thread1 = new TotalWithin(numberOfDarts/4);
        TotalWithin Thread2 = new TotalWithin(numberOfDarts/4);
        TotalWithin Thread3 = new TotalWithin(numberOfDarts/4);
        TotalWithin Thread4 = new TotalWithin(numberOfDarts/4);
        //Start the threads
        Thread1.start();
        Thread2.start();
        Thread3.start();
        Thread4.start();
        //Join the threads
        Thread1.join();
        Thread2.join();
        Thread3.join();
        Thread4.join();

//        System.out.println(Thread1.getWithin()+Thread2.getWithin()+Thread3.getWithin()+Thread4.getWithin());
        // Sum of the darts
        int sumOfthreads = Thread1.getWithin()+Thread2.getWithin()+Thread3.getWithin()+Thread4.getWithin();
        //estimate pi by getting proportion of darts in the quarter circle and multiplying by 4.
        double estimate = (double)sumOfthreads/numberOfDarts *4;
        System.out.println(estimate);

    }
}


