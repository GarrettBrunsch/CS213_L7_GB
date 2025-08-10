class Bahamut extends Creature implements Constants
{
    public Bahamut()
    {
        super();
    }

    public Bahamut(String name, int health, int strength)
    {
        super(name, health, strength);
    }

    @Override
    public String getName()
    {
        return name + " the bahamut";
    }

    @Override
    public int getDamage()
    {
        int damage = super.getDamage();

        if ((rand.nextInt(100)) < BAHAMUT_CHANCE)
        {
            damage = damage + BAHAMUT_BONUS_DAMAGE;
        }
        return damage;
    }

    @Override
    protected String getCreatureType()
    {
        return "bahamut";
    }
}