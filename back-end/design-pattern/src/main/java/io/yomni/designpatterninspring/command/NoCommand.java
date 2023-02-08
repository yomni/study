package io.yomni.designpatterninspring.command;

/**
 * NoCommand 는 null 객체입니다.
 */
public class NoCommand implements Command {

    @Override
    public void execute() {

    }

    @Override
    public void undo() {

    }
}
