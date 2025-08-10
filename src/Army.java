// Garrett Brunsch
// Lab #7 - Abstract & Interface
// Due 8/4/25class Army implements Constants

{
    private String armyName = DEFAULT_NAME;
    private int armySize = DEFAULT_ARMY_SIZE;
    private Creature[] creatures = new Creature[MAX_ARMY_SIZE];

    public Army()
    {
        try
        {
            setArmy(DEFAULT_NAME, DEFAULT_ARMY_SIZE);
            initializeCreatures();
        }
        catch (InvalidArmyParamException e)
        {
            restoreDefaults();
            System.err.println("Failed to create army with default parameters: " + e.getMessage());
        }
    }

    public Army(int size)
    {
        try
        {
            setArmy(DEFAULT_NAME, size);
            initializeCreatures();
        }
        catch (InvalidArmyParamException e)
        {
            restoreDefaults();
            System.err.println("Failed to create an army with size " + size + ": " + e.getMessage());
        }
    }

    public Army(String name)
    {
        try
        {
            setArmy(name, DEFAULT_ARMY_SIZE);
            initializeCreatures();
        }
        catch (InvalidArmyParamException e)
        {
            restoreDefaults();
            System.err.println("Failed to create an army named " + name + ": " + e.getMessage());
        }
    }

    public Army(String name, int size)
    {
        try
        {
            setArmy(name, size);
            initializeCreatures();
        }
        catch (InvalidArmyParamException e)
        {
            restoreDefaults();
            System.err.println("Failed to create an army named " + name + " of size " + size+ ": " + e.getMessage());
        }
    }

    private void restoreDefaults()
    {
        armyName = DEFAULT_NAME;
        armySize = DEFAULT_ARMY_SIZE;
        creatures = new Creature[armySize];
    }

    // Throws propagate to/ are caught and handled within constructors
    public void setArmy(String name, int size)
    {
        if (name == null || name.trim().isEmpty())
        {
            throw new IllegalArgumentException("Army name cannot be null or empty");
        }

        if (size < MIN_ARMY_SIZE || size > MAX_ARMY_SIZE)
        {
            throw new IllegalArgumentException("Army size must be between " + MIN_ARMY_SIZE + " and " +  MAX_ARMY_SIZE);
        }

        armyName = name.trim();
        armySize = size;
    }

    public int getArmySize()
    {
        return armySize;
    }

    public String getArmyName()
    {
        return armyName;
    }

    public Creature getCreature(int index)
    {
        Creature result = null;

        if (index >= 0 && index < armySize)
        {
            result = creatures[index];
        }
        return result;
    }

    private void initializeCreatures()
    {
        // Prevents names from being "consumed" when creation fails
        boolean[] originalUsedNames = new boolean[Main.usedNames.length];
        System.arraycopy(Main.usedNames, 0, originalUsedNames, 0, originalUsedNames.length);

        try
        {
            for (int i = 0; i < armySize; i++)
            {
                String creatureName = Main.getUniqueName();
                int creatureType = rand.nextInt(4);
                int strength = rand.nextInt(MAX_STRENGTH - MIN_STRENGTH + 1) + MIN_STRENGTH;
                int health = rand.nextInt(MAX_HEALTH - MIN_HEALTH + 1) + MIN_HEALTH;

                switch (creatureType)
                {
                    case 0:
                        creatures[i] = new Bahamut(creatureName, health, strength);
                        break;
                    case 1:
                        creatures[i] = new Cyberbahamut(creatureName, health, strength);
                        break;
                    case 2:
                        creatures[i] = new Superbahamut(creatureName, health, strength);
                        break;
                    case 3:
                        creatures[i] = new Macara(creatureName, health, strength);
                        break;
                }
            }
        }
        catch (InvalidArmyParamException e)
        {
            System.arraycopy(originalUsedNames, 0, Main.usedNames, 0, originalUsedNames.length);
            throw new InvalidArmyParamException("Failed to initialize creatures: " + e.getMessage()); // Caught and handled within constructor
        }
    }

    public int getTotalHealth()
    {
        int totalHealth = 0;

        for (int i = 0; i < armySize; i++)
        {
            if (creatures[i] != null)
            {
                totalHealth += creatures[i].getHealth();
            }
        }
        return totalHealth;
    }

    public String toString()
    {
        String headerFormat = "%-15s | %-15s | %10s | %10s";
        String result = armyName + " Stats\n";
        result += String.format(headerFormat, "Creature", "Type", "Strength", "Health") + "\n";
        result += "-".repeat(65) + "\n";

        for (int i = 0; i < armySize; i++)
        {
            if (creatures[i] != null)
            {
                result += creatures[i].toString() + "\n";
            }
        }

        result += "Total Health: " + getTotalHealth() + "\n";

        return result;
    }

}
