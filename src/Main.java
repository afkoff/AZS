import java.util.Random;
public class Main
{
    public static void main(String[] args)
    {
        GasStation gasStation = new GasStation(3, 300, 500);
        Random random = new Random();
        int time = 0;

        while(time < 10080)
        {
            if(random.nextDouble() < 0.3)
            {
                double tankVol = random.nextInt(71) + 30;
                double curFill = random.nextInt(100);
                double desFill = random.nextInt((int)(tankVol - (tankVol * (curFill / 100)) + 1));
                gasStation.addCarToQueue(new Car(tankVol, curFill, desFill));
                System.out.println("Новая машина заехала на АЗС.");
            }
            gasStation.update(time);
            time ++;
        }
        System.out.println("Неделя прошла.");
    }
}