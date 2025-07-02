import java.security.PublicKey;
import java.util.Random;

public class TotalWithin extends Thread {
    int numberOfDarts;
    int within;
    public TotalWithin(int numbofdarts){
        this.numberOfDarts = numbofdarts;
    }
    public void run() {
        Random r = new Random();
        for (int i = 0; i < numberOfDarts; i++) {
            //get x and y coordinates for the darts
            double x = r.nextDouble();
            double y = r.nextDouble();
            //calculate the distance from the origin (0, 0) darts with a distance less than 1 are within the
            //quarter circle so add these to within
            double dist = Math.sqrt((x * x) + (y * y));
            if (dist < 1)
                within++;
        }
    }
    public int getWithin() {
        return within;
    }
}
