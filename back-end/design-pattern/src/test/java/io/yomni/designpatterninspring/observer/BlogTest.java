package io.yomni.designpatterninspring.observer;

import org.junit.jupiter.api.Test;

import java.util.Observer;

class BlogTest {

    @Test
    void observerTest() throws Exception {
        Blog myBlog = new Blog();
        Observer subscriber = new Subscriber();
        Observer rssSubscriber = new RssSubscriber();
        Observer mailSubscriber = new MailSubscriber();

        // Register the observer with the subject.
        myBlog.addObserver(subscriber);
        myBlog.addObserver(rssSubscriber);
        myBlog.addObserver(mailSubscriber);

        // Add some blog post titles.
        myBlog.addBlogPost("Docker in 5 Steps");
        myBlog.addBlogPost("Angular vs React");
    }
}