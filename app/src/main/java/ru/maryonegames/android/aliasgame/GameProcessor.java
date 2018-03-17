package ru.maryonegames.android.aliasgame;

import java.util.ArrayList;

/**
 * Created by vamar on 17.03.2018.
 */

public class GameProcessor
{

    static private  ArrayList<Command> commands = new ArrayList<Command>();

    public void setCommands(ArrayList<String> commands) {
        for (String s: commands
             ) {
            this.commands.add(new Command(s, 0));
        }
    }
}
