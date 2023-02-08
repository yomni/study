package io.yomni.designpatterninspring.command;

public interface Command {
    void execute();

    void undo();
}
