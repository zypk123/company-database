package cy.its.service.standardization.validator;

import java.util.function.Predicate;

public class SingleFilter<T>
{
    Predicate<T> filter;
    String errorMessage;

    SingleFilter(Predicate<T> filter, String errorMessage)
    {
        this.filter = filter;
        this.errorMessage = errorMessage;
    }
    
    boolean test(T t){
    	return filter.test(t);
    }
    
    String getErrorMessage(){
    	return errorMessage;
    }
}