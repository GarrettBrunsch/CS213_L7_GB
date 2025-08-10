class Cyberbahamut extends Bahamut
{
    public Cyberbahamut()
    {
        super();
    }

    public Cyberbahamut(String name, int health, int strength)
    {
        super(name, health, strength);
    }

    @Override
    public String getName()
    {
        return name + " the cyberbahamut";
    }

    @Override
    protected String getCreatureType()
    {
        return "cyberbahamut";
    }

}