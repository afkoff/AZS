import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GasStation
{
    private int gasolineArrive;
    private int gasolineAmount;
    private int columnCount;
    private List<Car> queue;
    private List<FuelingColumn> columns;
    private int waiting = 0;
    private int waitingCheck = 0;
    private int waitingTime;
    private int gasolineNow;

    public GasStation(int columnCount, int gasolineAmount, int gasolineArrive)
    {
        if(columnCount <= 0)
        {
            throw new RuntimeException("columnCount invalid");
        }
        else
        {
            this.columnCount = columnCount;
        }
        if(gasolineAmount <= 0)
        {
            throw new RuntimeException("gasolineAmount invalid");
        }
        else
        {
            this.gasolineAmount = gasolineAmount;
        }
        if(gasolineArrive <= 0)
        {
            throw new RuntimeException("gasolineArrive invalid");
        }
        else
        {
            this.gasolineArrive = gasolineArrive;
        }

        this.queue = new ArrayList<>();
        this.columns = new ArrayList<>();

        for(int i = 0; i < columnCount; i++)
        {
            columns.add(new FuelingColumn());
        }

        gasolineNow = gasolineAmount;
    }

    public void addCarToQueue(Car car)
    {
        queue.add(car);
    }
    public int refuiling(double fuel, double speed)
    {
        return (int) Math.ceil(fuel / speed);
    }

    public void update(int curTime)
    {
        if(waitingCheck == 1 && waitingTime == curTime)
        {
            System.out.println("Поставлена новая колонка.");
            waitingCheck = 0;
            columns.add((new FuelingColumn()));
        }
        if(curTime != 0 && curTime % gasolineArrive == 0)
        {
            gasolineNow = gasolineAmount;
        }
        for(int i = 0; i < columns.size(); i++)
        {
            if (columns.get(i).getTime() == 0 && columns.get(i).getCheck() == 1)
            {
                System.out.println("Машина заправлена к " + curTime + " min");
                columns.get(i).changeCheck();
            }
            else
            {
                columns.get(i).changeTime(columns.get(i).getTime() - 1);
            }
        }
        List<Integer> cars = new ArrayList<>();
        if(queue.size() != 0)
        {
            for(int i = 0; i < queue.size(); i++)
            {
                if(gasolineNow >= queue.get(i).checkDesFill()) {

                    for (int j = 0; j < columns.size(); j++) {
                        if (queue.get(i).getCheck() == 0 && columns.get(j).getCheck() == 0) {
                            System.out.println("Машина встала на заправку в " + curTime + "min");
                            columns.get(j).changeCheck();
                            queue.get(i).changeCheck();
                            cars.add(i);
                            columns.get(j).changeTime(refuiling(queue.get(i).checkDesFill(), columns.get(j).checkSpeed()));
                            gasolineNow -= queue.get(i).checkDesFill();
                        }
                    }
                }
                else
                {
                    System.out.println("Топлива недостаточно.");
                    break;
                }
            }
            if(cars.size() != 0)
            {
                for (int i = 0; i < cars.size(); i++) {
                    queue.remove(0);
                }
            }
        }
        int count = 0;
        int[] Wait = new int[columns.size()];
        for(int i = 0; i < columns.size(); i++)
        {
            if(columns.get(i).getCheck() == 1)
            {
                count += 1;
                Wait[i] = columns.get(i).getTime();
            }
        }
        Arrays.sort(Wait);
        if(queue.size() != 0)
        {
            for(int i = 0; i < Wait.length; i++)
            {
                if(Wait[i] != 0)
                {
                    waiting = Wait[i];
                    break;
                }
            }
            System.out.println("Время ожидания: " + waiting);
        }
        else
        {
            waiting = 0;
            System.out.println("Время ожидания: " + waiting + ". Свободно " + (columns.size() - count) + " колонок.");
        }

        if(waiting > 12 && waitingCheck == 0)
        {
            System.out.println("Новая колонка будет через 2 дня.");
            int waitingTime = curTime + 2880;
            waitingCheck = 1;
        }
    }



}
