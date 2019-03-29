package exampleApp.commons;

public interface Mapper<F, T> {


    T map(F from);

    F revers(T to);


}
