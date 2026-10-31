package lab10;

public class Software {

    @Version(number = "1.0", author = "Alice")
    public void featureOne() {
        System.out.println("Feature One");
    }

    @Version(number = "1.1", author = "Bob")
    public void featureTwo() {
        System.out.println("Feature Two");
    }

    // Самостоятельная работа: Третий метод без аннотации @Version
    public void unannotatedMethod() {
        System.out.println("This method has no version metadata");
    }
}
