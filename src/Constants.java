// Garrett Brunsch
// Lab #7 - Abstract & Interface
// Due 8/4/25

import java.util.Random;

public interface Constants
{
    // General Defaults
    public static final Random rand = new Random();
    public static final int MAX_TEST_CASES = 10;

    public static final String OUTPUT_FILE = "src/out_battle_results.txt";

    // Creature Defaults
    public static final int MIN_STRENGTH = 50;
    public static final int MAX_STRENGTH = 197;

    public static final int MIN_HEALTH = 50;
    public static final int MAX_HEALTH = 197;

    public static final int MIN_NAME_LENGTH = 3;
    public static final String DEFAULT_NAME = "Unknown";

    // Special attack defaults
    public static final int BAHAMUT_BONUS_DAMAGE = 25;
    public static final int BAHAMUT_CHANCE = 10;
    public static final int MACARA_CHANCE = 20;

    // Army Defaults
    public static final int MIN_ARMY_SIZE = 1;
    public static final int MAX_ARMY_SIZE = 15;
    public static final int DEFAULT_ARMY_SIZE = MIN_ARMY_SIZE;
    public static final String ARMY_1_NAME = "Army #1";
    public static final String ARMY_2_NAME = "Army #2";

}
