package exampleApp.commons;

//@Component pozwala na wstrzykiwanie

public interface Mapper<F, T> {


    T map(F from);

    F revers(T to);



}
