public class Car
{
    private double tankVol;
    private double curFill;
    private double desFill;
    private int check = 0;

    public Car(double tankVol, double curFill, double desFill)
    {
        if((curFill > 100 || curFill < 0) || (curFill % 1 != 0))
        {
            throw new RuntimeException("curFill invalid");
        }
        if(tankVol - (tankVol * (curFill / 100)) < desFill)
        {
            throw new RuntimeException("Desired Fill invalid");
        }
        this.tankVol = tankVol;
        this.curFill = curFill;
        this.desFill = desFill;
    }

    public double checkDesFill()
    {
        return desFill;
    }
    public int getCheck()
    {
        return check;
    }
    public void changeCheck()
    {
        check = 1;
    }


}
