import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Comparator;

public class Main {

    // 1. Создание аннотации @Version внутри главного класса
    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.METHOD)
    public @interface Version {
        String number();
        String author();
    }

    // 2. Класс Software сделан вложенным (static)
    public static class Software {
        @Version(number = "1.0", author = "Alice")
        public void featureOne() {
            System.out.println("Feature One");
        }

        @Version(number = "1.1", author = "Bob")
        public void featureTwo() {
            System.out.println("Feature Two");
        }

        // Третий метод без аннотации (требование самостоятельной работы)
        public void unannotatedMethod() {
            System.out.println("This method has no version metadata");
        }
    }

    // 3. Класс без аннотаций для проверки крайнего случая
    public static class NoAnnotationsClass {
        public void anyMethod() {
            System.out.println("Just a method");
        }
    }

    // 4. Логика выполнения программы
    public static void main(String[] args) {
        System.out.println("=== Проверка управления версиями (Software) ===");
        checkSoftwareVersions(Software.class);

        System.out.println("\n=== Проверка класса БЕЗ аннотаций (Крайний случай) ===");
        checkSoftwareVersions(NoAnnotationsClass.class);
    }

    private static void checkSoftwareVersions(Class<?> clazz) {
        System.out.println("Анализ класса: " + clazz.getSimpleName());
        Method[] methods = clazz.getDeclaredMethods();
        
        // Сортировка методов по имени для стабильности вывода в браузере
        Arrays.sort(methods, Comparator.comparing(Method::getName));

        int unannotatedCount = 0;

        for (Method method : methods) {
            if (method.isAnnotationPresent(Version.class)) {
                Version version = method.getAnnotation(Version.class);
                System.out.println("Method: " + method.getName() 
                        + ", Version: " + version.number() 
                        + ", Author: " + version.author());
            } else {
                unannotatedCount++;
            }
        }
        System.out.println("Количество методов без аннотации @Version: " + unannotatedCount);
    }
}
