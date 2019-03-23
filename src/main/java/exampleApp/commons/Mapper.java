package exampleApp.commons;

import org.springframework.stereotype.Component;

//@Component pozwala na wstrzykiwanie

public interface Mapper<F, T> {


    T map(F from);

    F revers(T to);



}
