package ru.maryonegames.android.aliasgame;

/**
 * Created by vamar on 17.03.2018.
 */

public class Command {
    String commandName;
    int commandPoints;

    public Command(String commandName, int commandPoints) {
        this.commandName = commandName;
        this.commandPoints = commandPoints;
    }

    public String getCommandName() {
        return commandName;
    }

    public int getCommandPoints() {
        return commandPoints;
    }
}
