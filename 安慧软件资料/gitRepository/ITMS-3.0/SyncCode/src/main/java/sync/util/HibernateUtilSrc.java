package sync.util;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

/**
 * 供Hibernate V4.3.8版 使用
 * 
 * @author mahc
 * 
 */
public class HibernateUtilSrc {

	private static SessionFactory sessionFactory;
	private static Session session;

	static {
		// 创建Configuration,该对象用于读取hibernate.cfg.xml，并完成初始化
		Configuration config = new Configuration().configure("/hibernate.src.cfg.xml");
		StandardServiceRegistryBuilder ssrb = new StandardServiceRegistryBuilder()
				.applySettings(config.getProperties());
		StandardServiceRegistry ssr = ssrb.build();
		sessionFactory = config.buildSessionFactory(ssr);
	}

	/**
	 * 获取SessionFactory
	 * 
	 * @return
	 */
	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public static Session getCurrentSession() {
		session = sessionFactory.openSession();
		return session;
	}

	public static void closeSession(Session session) {

		if (null != session) {
			session.close();
		}
	}
}