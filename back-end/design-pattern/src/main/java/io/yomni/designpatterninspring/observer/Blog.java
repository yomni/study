package io.yomni.designpatterninspring.observer;

import java.util.Observable;

public class Blog extends Observable {
    public void addBlogPost(String blogPostTitle) {
        setChanged();
        notifyObservers(blogPostTitle);
    }
}
