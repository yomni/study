package step1;

@FunctionalInterface
public interface RunSomething {

    void doIt();

    static void printName() {
        System.out.println("yomni");
    }

    // interface에서 default 메소드란?
    default void printAge() {
        System.out.println("32");
    }
}
