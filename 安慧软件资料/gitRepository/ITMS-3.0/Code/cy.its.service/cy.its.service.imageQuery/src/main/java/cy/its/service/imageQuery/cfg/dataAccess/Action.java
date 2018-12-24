package cy.its.service.imageQuery.cfg.dataAccess;

@FunctionalInterface
public interface Action<T> {
	void accept(T t) throws Exception;
}
