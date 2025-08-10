// Garrett Brunsch
// Lab #7 - Abstract & Interface
// Due 8/4/25

import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

class Game implements Constants
{
    private StringBuilder battleLog;

    public Game()
    {
        battleLog = new StringBuilder();
    }

    private void printAndAppend(String message)
    {
        System.out.println(message);
        battleLog.append(message + "\n");
    }

    public void playGame(Scanner mainScanner) throws IOException
    {
        battleLog.setLength(0);
        int armySize = getArmySize(mainScanner);

        Main.resetUsedNames();

        Army army1 = new Army(ARMY_1_NAME, armySize);
        Army army2 = new Army(ARMY_2_NAME, armySize);

        printAndAppend("\n=== NEW BATTLE ===");
        printAndAppend("\nArmy Stats Before Battle:");
        printAndAppend(army1.toString());
        printAndAppend(army2.toString());

        conductBattle(army1, army2);

        printAndAppend("\nArmy Stats After Battle:");
        printAndAppend(army1.toString());
        printAndAppend(army2.toString());

        announceWinner(army1, army2);

        writeAllToFile();

        System.out.println("\nBattle results written to file");
    }

    private int getArmySize(Scanner mainScanner)
    {
        int size = 0;
        boolean validInput = false;

        while (!validInput)
        {
            System.out.print("Enter army size (1-15): ");
            String input = mainScanner.nextLine().trim();

            try
            {
                if (input != null && !input.isEmpty())
                {
                    size = Integer.parseInt(input);
                    if (size >= MIN_ARMY_SIZE && size <= MAX_ARMY_SIZE)
                    {
                        validInput = true;
                        System.out.println();
                    }
                    else
                    {
                        System.out.println("Army size must be between " + MIN_ARMY_SIZE + " and " + MAX_ARMY_SIZE);
                    }
                }
                else
                {
                    System.out.println("Please enter a valid number");
                }
            }
            catch (NumberFormatException e)
            {
                System.out.println("Please enter a valid number");
            }
        }

        return size;
    }

    private void conductBattle(Army army1, Army army2)
    {
        System.out.println("\n--- BATTLE BEGINS ---");
        battleLog.append("\n--- BATTLE LOG ---\n");

        int armySize = army1.getArmySize();

        for (int position = 0; position < armySize; position++)
        {
            Creature creature1 = army1.getCreature(position);
            Creature creature2 = army2.getCreature(position);

            if (creature1 != null && creature2 != null && creature1.getHealth() > 0 && creature2.getHealth() > 0)
            {
                printAndAppend("\nBattle " + (position + 1) + ": " + creature1.getName() +
                        " vs " + creature2.getName());

                String battleHeader = String.format("%-25s | %-6s | %-10s | %-25s | %-15s | %-10s",
                        "Attacker", "Damage", "Army", "Defender", "Defender Health", "Army");
                String separator = "\n" + "-".repeat(120);

                printAndAppend(battleHeader + separator);

                battleCreatures(creature1, creature2, army1.getArmyName(), army2.getArmyName());
            }
        }
    }

    private void battleCreatures(Creature creature1, Creature creature2, String army1Name, String army2Name)
    {
        int currentTurn = rand.nextInt(2);

        while (creature1.getHealth() > 0 && creature2.getHealth() > 0)
        {
            Creature attacker = (currentTurn == 0) ? creature1 : creature2;
            Creature defender = (currentTurn == 0) ? creature2 : creature1;
            String attackerArmy = (currentTurn == 0) ? army1Name : army2Name;
            String defenderArmy = (currentTurn == 0) ? army2Name : army1Name;

            if (attacker instanceof Superbahamut)
            {
                int damage1 = attacker.getDamage();
                defender.takeDamage(damage1);

                String battleLine1 = String.format("%-25s | %6d | %-10s | %-25s | %15d | %-10s",
                        attacker.getName(), damage1, attackerArmy,
                        defender.getName(), defender.getHealth(), defenderArmy);
                printAndAppend(battleLine1);

                if (defender.getHealth() > 0)
                {
                    int damage2 = attacker.getDamage();
                    defender.takeDamage(damage2);

                    String battleLine2 = String.format("%-25s | %6d | %-10s | %-25s | %15d | %-10s",
                            attacker.getName() + " (2nd)", damage2, attackerArmy,
                            defender.getName(), defender.getHealth(), defenderArmy);
                    printAndAppend(battleLine2);
                }
            }
            else
            {
                int damage = attacker.getDamage();
                defender.takeDamage(damage);

                String battleLine = String.format("%-25s | %6d | %-10s | %-25s | %15d | %-10s",
                        attacker.getName(), damage, attackerArmy,
                        defender.getName(), defender.getHealth(), defenderArmy);
                printAndAppend(battleLine);
            }

            currentTurn = (currentTurn + 1) % 2;
        }

        Creature winner = (creature1.getHealth() > 0) ? creature1 : creature2;
        printAndAppend("Winner: " + winner.getName());
    }

    private void announceWinner(Army army1, Army army2)
    {
        int army1Health = army1.getTotalHealth();
        int army2Health = army2.getTotalHealth();

        String winnerMessage = (army1Health > army2Health) ? army1.getArmyName() + " wins the war!" :
                (army2Health > army1Health) ? army2.getArmyName() + " wins the war!" :
                        "The war ends in a tie!";

        String finalResults = "\n\n=== FINAL RESULTS ===\n" +
                army1.getArmyName() + " total health: " + army1Health + "\n" +
                army2.getArmyName() + " total health: " + army2Health + "\n" +
                winnerMessage;

        printAndAppend(finalResults);
    }

    private void writeAllToFile() throws IOException
    {
        FileWriter writer = new FileWriter(OUTPUT_FILE, true);
        writer.write(battleLog.toString());
        writer.close();
    }

}
