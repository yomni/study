package io.yomni.designpatterninspring.observer;

import java.util.Observable;
import java.util.Observer;

public class MailSubscriber implements Observer {
    @Override
    public void update(Observable blog, Object blogPostTitle) {
        System.out.println(getClass().getSimpleName() + " 수신 ||||| " + blogPostTitle);
    }
}
