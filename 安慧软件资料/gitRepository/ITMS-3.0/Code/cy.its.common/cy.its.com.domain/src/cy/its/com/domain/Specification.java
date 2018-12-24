package cy.its.com.domain;

public interface Specification<T> {

  boolean isSatisfiedBy(T t);

  Specification<T> and(Specification<T> specification);

  Specification<T> or(Specification<T> specification);

  Specification<T> not(Specification<T> specification);
}
