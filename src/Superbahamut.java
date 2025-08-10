// Garrett Brunsch
// Lab #7 - Abstract & Interface
// Due 8/4/25

class Superbahamut extends Bahamut
{
    public Superbahamut()
    {
        super();
    }

    public Superbahamut(String name, int health, int strength)
    {
        super(name, health, strength);
    }

    @Override
    public String getName()
    {
        return name + " the superbahamut";
    }

    @Override
    protected String getCreatureType()
    {
        return "superbahamut";
    }

}
