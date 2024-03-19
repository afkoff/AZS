public class FuelingColumn
{
    private int speed;
    private int check = 0;
    private int time;

    public FuelingColumn()
    {
        this.speed = 20;
    }

    public void changeSpeed(int nspeed)
    {
        if(nspeed > 0 && nspeed != speed)
        {
            speed = nspeed;
        }
        else
        {
            throw new RuntimeException("New Speed invalid");
        }
    }
    public int checkSpeed()
    {
        return speed;
    }

    public int getCheck()
    {
        return check;
    }
    public void changeCheck()
    {
        if(check == 0)
        {
            check = 1;
        }
        else
        {
            check = 0;
        }
    }
    public int getTime()
    {
        return time;
    }
    public void changeTime(int x)
    {
        time = x;
    }

}
