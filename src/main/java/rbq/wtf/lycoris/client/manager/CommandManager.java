package rbq.wtf.lycoris.client.manager;

import rbq.wtf.lycoris.client.command.Command;
import rbq.wtf.lycoris.client.command.Commands.VClip;
import rbq.wtf.lycoris.client.utils.Render.ChatUtils;
import rbq.wtf.lycoris.client.wrapper.Wrapper;

import java.util.ArrayList;

public class CommandManager {
    public static ArrayList<Command> commands = new ArrayList<Command>();
    private volatile static CommandManager instance;

    public static char cmdPrefix = ';';

    public CommandManager()
    {
        commands.add(new VClip());
    }

    public boolean runCommands(String s)
    {
        String readString = s.trim().substring(Character.toString(cmdPrefix).length()).trim();
        boolean commandResolved = false;
        boolean hasArgs = readString.trim().contains(" ");
        String commandName = hasArgs ? readString.split(" ")[0] : readString.trim();
        String[] args = hasArgs ? readString.substring(commandName.length()).trim().split(" ") : new String[0];

        for(Command command: commands)
        {
            if(command.getCommand().trim().equalsIgnoreCase(commandName.trim()))
            {
                command.runCommand(readString, args);
                commandResolved = true;
                break;
            }
        }
        if(!commandResolved){
            ChatUtils.error("Cannot resolve internal command: \u00a7c"+commandName);

        }
        return commandResolved;
    }

    public static void onKeyPressed(int key) {
        if (Wrapper.INSTANCE.mc().currentScreen != null) return;
        for(Command cmd : commands)
            if(cmd.getKey() == key)
                CommandManager.getInstance().runCommands("." + cmd.getExecute());
    }

    public static CommandManager getInstance(){
        if(instance == null){
            instance = new CommandManager();
        }
        return instance;
    }
}
