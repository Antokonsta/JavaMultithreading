package sleepingHaircutter;

import java.util.Date;

/**
 * Created by Anton on 14.05.17.
 */
class Customer implements Runnable
{
    private  String name;
    private Date inTime;

    private Barbershop barbershop;

    public Customer(Barbershop barbershop)
    {
        this.barbershop = barbershop;
    }

    public String getName() {
        return name;
    }

    public Date getInTime() {
        return inTime;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setInTime(Date inTime) {
        this.inTime = inTime;
    }

    public void run()
    {
        goForHairCut();
    }
    private synchronized void goForHairCut()
    {
        barbershop.add(this);
    }
}