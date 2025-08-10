class Macara extends Creature implements Constants
{
    public Macara()
    {
        super();
    }

    public Macara(String name, int health, int strength)
    {
        super(name, health, strength);
    }

    @Override
    public String getName()
    {
        return name + " the macara";
    }

    @Override
    public int getDamage()
    {
        int damage = super.getDamage();

        if ((rand.nextInt(MACARA_CHANCE)) == 0)
        {
            damage = damage * 2;
        }
        return damage;
    }

    @Override
    protected String getCreatureType()
    {
        return "macara";
    }
}