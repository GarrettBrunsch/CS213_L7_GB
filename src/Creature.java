// Garrett Brunsch
// Lab #7 - Abstract & Interface
// Due 8/4/25

public abstract class Creature implements Constants
{
    protected String name;
    protected int health;
    protected int strength;

    public Creature()
    {
        try
        {
            setCreature(DEFAULT_NAME, MIN_HEALTH, MIN_STRENGTH);
        }
        catch (InvalidCreatureParamException e)
        {
            restoreDefaults();
            System.err.println("Error during default creature creation: " + e.getMessage());
        }
    }

    public Creature(String n_name, int n_health, int n_strength)
    {
        try
        {
            setCreature(n_name, n_health, n_strength);
        }
        catch (InvalidCreatureParamException e)
        {
            restoreDefaults();
            System.err.println("Invalid creature parameters: " + e.getMessage());
        }
    }

    // Thrown errors are caught and handled within constructors
    public void setCreature(String n_name, int n_health, int n_strength)
    {
        if (n_name == null || n_name.trim().length() < MIN_NAME_LENGTH)
        {
            throw new InvalidCreatureParamException("Creature name must be at least " + MIN_NAME_LENGTH +
                    " characters long and not null");
        }
        if (n_health < 0)
        {
            throw new InvalidCreatureParamException("Creature health must be > 0");
        }

        if (n_strength < MIN_STRENGTH || n_strength > MAX_STRENGTH)
        {
            throw new InvalidCreatureParamException("Creature strength must be between "+ MIN_STRENGTH+
                    " and "+MAX_STRENGTH);
        }

        name = n_name.trim();
        health = n_health;
        strength = n_strength;
    }

    private void restoreDefaults()
    {
        name = DEFAULT_NAME;
        health = MIN_HEALTH;
        strength = MIN_STRENGTH;
    }

    public String getName()
    {
        return name + " the creature";
    }

    public int getHealth()
    {
        return health;
    }

    public int getStrength()
    {
        return strength;
    }

    public void takeDamage(int damage)
    {
        if ((health - damage) < 0)
        {
            health = 0;
        }
        else
        {
            health -= damage;
        }
    }

    public int getDamage()
    {
        return (rand.nextInt(strength) + 1);
    }

    public String toString()
    {
        return String.format("%-15s | %-15s | %10d | %10d", name, getCreatureType(), strength, health);
    }

    protected String getCreatureType()
    {
        return "creature";
    }

}
