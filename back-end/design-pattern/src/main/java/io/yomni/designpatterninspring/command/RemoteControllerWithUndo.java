package io.yomni.designpatterninspring.command;

import java.util.ArrayList;
import java.util.List;

public class RemoteControllerWithUndo {
    private static final int COMMANDS_SIZE = 7;
    private List<Command> onCommands;
    private List<Command> offCommands;
    private Command undoCommand;

    public RemoteControllerWithUndo() {
        onCommands = new ArrayList<>(COMMANDS_SIZE);
        offCommands = new ArrayList<>(COMMANDS_SIZE);

        Command noCommand = new NoCommand();
        for (int i = 0; i < COMMANDS_SIZE; i++) {
            onCommands.add(noCommand);
            offCommands.add(noCommand);
        }

        undoCommand = noCommand;
    }

    public void setCommand(int slot, Command onCommand, Command offCommand) {
        onCommands.set(slot, onCommand);
        offCommands.set(slot, offCommand);
    }

    public void onButtonWasPushed(int slot) {
        Command onCommand = onCommands.get(slot);
        onCommand.execute();
        undoCommand = onCommand;
    }

    public void offButtonWasPushed(int slot) {
        Command offCommand = offCommands.get(slot);
        offCommand.execute();
        undoCommand = offCommand;
    }

    public void undoButtonWasPushed() {
        undoCommand.undo();
    }
}
