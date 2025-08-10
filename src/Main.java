// Garrett Brunsch
// Lab #7 - Abstract & Interface
// Due 8/4/25

import java.util.Scanner;
import java.io.IOException;

public class Main implements Constants
{
    enum MenuOptions
    {
        INVALID, BATTLE, QUIT
    }

    private static final String[] CREATURE_NAMES = {
            "Aragorn", "Legolas", "Gimli", "Gandalf", "Frodo", "Sam", "Merry", "Pippin",
            "Boromir", "Faramir", "Eowyn", "Theoden", "Arwen", "Elrond", "Galadriel",
            "Thorin", "Balin", "Dwalin", "Fili", "Kili", "Bofur", "Bifur", "Bombur",
            "Ori", "Nori", "Dori", "Oin", "Gloin", "Radagast", "Saruman"
    };

    static boolean[] usedNames = new boolean[CREATURE_NAMES.length];

    public static void main(String[] args) throws IOException
    {
        Scanner scanner = new Scanner(System.in);
        Game game = new Game();

        int choice = 0;
        MenuOptions menuChoice = MenuOptions.INVALID;

        while (menuChoice != MenuOptions.QUIT)
        {
            displayMenu();
            choice = getUserChoice(scanner);

            choice = (choice >= MenuOptions.BATTLE.ordinal() && choice <= MenuOptions.QUIT.ordinal()) ? choice : 0;
            menuChoice = MenuOptions.values()[choice];

            switch (menuChoice)
            {
                case BATTLE:
                    game.playGame(scanner);
                    break;
                case QUIT:
                    System.out.println("Now exiting program...");
                    break;
                default:
                    System.out.println("Invalid choice. Please select a valid menu option");
                    break;
            }
        }
        scanner.close();
    }

    public static void displayMenu()
    {
        System.out.print("\n\n=== CREATURE BATTLE SYSTEM ===\n" +
                "1. Battle\n" +
                "2. Quit\n" +
                "Choice: ");
    }

    public static int getUserChoice(Scanner scanner)
    {
        String input = scanner.nextLine().trim();
        int choice = 0;

        try
        {
            if (input != null && !input.isEmpty())
            {
                choice = Integer.parseInt(input);
            }
        }
        catch (NumberFormatException e)
        {
            System.out.print("Incorrect format detected - ");
            choice = 0;
        }
        return choice;
    }

    public static void resetUsedNames()
    {
        for (int i = 0; i < usedNames.length; i++)
        {
            usedNames[i] = false;
        }
    }

    public static String getUniqueName()
    {
        int nameIndex;
        do
        {
            nameIndex = rand.nextInt(CREATURE_NAMES.length);
        } while (usedNames[nameIndex]);

        usedNames[nameIndex] = true;
        return CREATURE_NAMES[nameIndex];
    }
}

/*

=== CREATURE BATTLE SYSTEM ===
1. Battle
2. Quit
Choice: z
Incorrect format detected - Invalid choice. Please select a valid menu option


=== CREATURE BATTLE SYSTEM ===
1. Battle
2. Quit
Choice: 1
Enter army size (1-15): -1
Army size must be between 1 and 15
Enter army size (1-15): 7


=== NEW BATTLE ===

Army Stats Before Battle:
Army #1 Stats
Creature        | Type            |   Strength |     Health
-----------------------------------------------------------------
Boromir         | cyberbahamut    |         69 |        132
Elrond          | macara          |        110 |        196
Galadriel       | macara          |         57 |         97
Dori            | superbahamut    |        172 |         76
Dwalin          | bahamut         |         78 |        115
Oin             | cyberbahamut    |         53 |         95
Faramir         | superbahamut    |        183 |        170
Total Health: 881

Army #2 Stats
Creature        | Type            |   Strength |     Health
-----------------------------------------------------------------
Theoden         | cyberbahamut    |        101 |        175
Nori            | cyberbahamut    |         99 |         64
Legolas         | macara          |        128 |        178
Gloin           | bahamut         |        142 |        142
Frodo           | superbahamut    |         89 |         90
Radagast        | cyberbahamut    |         63 |        193
Balin           | cyberbahamut    |        128 |        157
Total Health: 999


--- BATTLE BEGINS ---

Battle 1: Boromir the cyberbahamut vs Theoden the cyberbahamut
Attacker                  | Damage | Army       | Defender                  | Defender Health | Army
------------------------------------------------------------------------------------------------------------------------
Theoden the cyberbahamut  |     30 | Army #2    | Boromir the cyberbahamut  |             102 | Army #1
Boromir the cyberbahamut  |     29 | Army #1    | Theoden the cyberbahamut  |             146 | Army #2
Theoden the cyberbahamut  |     79 | Army #2    | Boromir the cyberbahamut  |              23 | Army #1
Boromir the cyberbahamut  |     15 | Army #1    | Theoden the cyberbahamut  |             131 | Army #2
Theoden the cyberbahamut  |     25 | Army #2    | Boromir the cyberbahamut  |               0 | Army #1
Winner: Theoden the cyberbahamut

Battle 2: Elrond the macara vs Nori the cyberbahamut
Attacker                  | Damage | Army       | Defender                  | Defender Health | Army
------------------------------------------------------------------------------------------------------------------------
Nori the cyberbahamut     |     72 | Army #2    | Elrond the macara         |             124 | Army #1
Elrond the macara         |     50 | Army #1    | Nori the cyberbahamut     |              14 | Army #2
Nori the cyberbahamut     |     50 | Army #2    | Elrond the macara         |              74 | Army #1
Elrond the macara         |     10 | Army #1    | Nori the cyberbahamut     |               4 | Army #2
Nori the cyberbahamut     |     83 | Army #2    | Elrond the macara         |               0 | Army #1
Winner: Nori the cyberbahamut

Battle 3: Galadriel the macara vs Legolas the macara
Attacker                  | Damage | Army       | Defender                  | Defender Health | Army
------------------------------------------------------------------------------------------------------------------------
Legolas the macara        |     82 | Army #2    | Galadriel the macara      |              15 | Army #1
Galadriel the macara      |     54 | Army #1    | Legolas the macara        |             124 | Army #2
Legolas the macara        |      4 | Army #2    | Galadriel the macara      |              11 | Army #1
Galadriel the macara      |      1 | Army #1    | Legolas the macara        |             123 | Army #2
Legolas the macara        |     70 | Army #2    | Galadriel the macara      |               0 | Army #1
Winner: Legolas the macara

Battle 4: Dori the superbahamut vs Gloin the bahamut
Attacker                  | Damage | Army       | Defender                  | Defender Health | Army
------------------------------------------------------------------------------------------------------------------------
Dori the superbahamut     |    155 | Army #1    | Gloin the bahamut         |               0 | Army #2
Winner: Dori the superbahamut

Battle 5: Dwalin the bahamut vs Frodo the superbahamut
Attacker                  | Damage | Army       | Defender                  | Defender Health | Army
------------------------------------------------------------------------------------------------------------------------
Frodo the superbahamut    |     26 | Army #2    | Dwalin the bahamut        |              89 | Army #1
Frodo the superbahamut (2nd) |     30 | Army #2    | Dwalin the bahamut        |              59 | Army #1
Dwalin the bahamut        |     13 | Army #1    | Frodo the superbahamut    |              77 | Army #2
Frodo the superbahamut    |     76 | Army #2    | Dwalin the bahamut        |               0 | Army #1
Winner: Frodo the superbahamut

Battle 6: Oin the cyberbahamut vs Radagast the cyberbahamut
Attacker                  | Damage | Army       | Defender                  | Defender Health | Army
------------------------------------------------------------------------------------------------------------------------
Oin the cyberbahamut      |      5 | Army #1    | Radagast the cyberbahamut |             188 | Army #2
Radagast the cyberbahamut |     17 | Army #2    | Oin the cyberbahamut      |              78 | Army #1
Oin the cyberbahamut      |      3 | Army #1    | Radagast the cyberbahamut |             185 | Army #2
Radagast the cyberbahamut |     26 | Army #2    | Oin the cyberbahamut      |              52 | Army #1
Oin the cyberbahamut      |     43 | Army #1    | Radagast the cyberbahamut |             142 | Army #2
Radagast the cyberbahamut |     56 | Army #2    | Oin the cyberbahamut      |               0 | Army #1
Winner: Radagast the cyberbahamut

Battle 7: Faramir the superbahamut vs Balin the cyberbahamut
Attacker                  | Damage | Army       | Defender                  | Defender Health | Army
------------------------------------------------------------------------------------------------------------------------
Balin the cyberbahamut    |     13 | Army #2    | Faramir the superbahamut  |             157 | Army #1
Faramir the superbahamut  |      3 | Army #1    | Balin the cyberbahamut    |             154 | Army #2
Faramir the superbahamut (2nd) |    175 | Army #1    | Balin the cyberbahamut    |               0 | Army #2
Winner: Faramir the superbahamut

Army Stats After Battle:
Army #1 Stats
Creature        | Type            |   Strength |     Health
-----------------------------------------------------------------
Boromir         | cyberbahamut    |         69 |          0
Elrond          | macara          |        110 |          0
Galadriel       | macara          |         57 |          0
Dori            | superbahamut    |        172 |         76
Dwalin          | bahamut         |         78 |          0
Oin             | cyberbahamut    |         53 |          0
Faramir         | superbahamut    |        183 |        157
Total Health: 233

Army #2 Stats
Creature        | Type            |   Strength |     Health
-----------------------------------------------------------------
Theoden         | cyberbahamut    |        101 |        131
Nori            | cyberbahamut    |         99 |          4
Legolas         | macara          |        128 |        123
Gloin           | bahamut         |        142 |          0
Frodo           | superbahamut    |         89 |         77
Radagast        | cyberbahamut    |         63 |        142
Balin           | cyberbahamut    |        128 |          0
Total Health: 477



=== FINAL RESULTS ===
Army #1 total health: 233
Army #2 total health: 477
Army #2 wins the war!

Battle results written to file


=== CREATURE BATTLE SYSTEM ===
1. Battle
2. Quit
Choice: 2
Now exiting program...
 */