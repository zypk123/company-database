package cy.its.com.util;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import cy.its.com.bus.Event;
import cy.its.com.bus.EventBus;
import cy.its.com.bus.Receiver;
import cy.its.com.domain.Criteria;

public abstract class Data<Mapper, T> implements Receiver {

	private Mapper mapper;
	private String topic;
	private Map<String, T> dataMap;
	private Object sync = new Object();
	

	protected Data(Mapper mapper, String topic, EventBus eventBus) {
		this.mapper = mapper;
		this.topic = topic;
		load();
		eventBus.subscribe(topic, this);
	}

	protected void load() {
		List<T> lstSite = readMapper(mapper);
		Map<String, T> map = groupList(lstSite);
		synchronized (sync) {
			dataMap = map;
		}
	}

	public void accept(Event event) {
		load();
	}
	
	void validate() {
		if (dataMap == null) {
			load();
		}
	}

	public T dataOfId(String id) {
		validate();
		synchronized (sync) {
			return dataMap != null ? dataMap.get(id) : null;
		}
	}

	public String getTopic() {
		return topic;
	}

//	protected List<T> findDatas(Predicate<? super T> predicate, int fromIndex,
//			int toIndex, Comparator<T> comparator) {
//		List<T> lst = allDatas();
//		if (lst == null || lst.size() == 0) {
//			return lst;
//		}
//		lst = lst.stream().filter(predicate).collect(Collectors.toList());
//
//		return subAndSortList(lst, fromIndex, toIndex, comparator);
//	}

	protected List<T> findDatas(Predicate<? super T> predicate, Comparator<T> comparator,
			Criteria criteria) {
		int fromIndex = criteria.getFromIndex();
		int toIndex = criteria.getToIndex();
		List<T> lst = allDatas();
		if (lst == null || lst.size() == 0) {
			criteria.setTotal(criteria.getNeedTotal() ? 0 : Integer.MIN_VALUE);
			return lst;
		}
		
		lst = lst.stream().filter(predicate).collect(Collectors.toList());
		criteria.setTotal(criteria.getNeedTotal() ? lst.size() : Integer.MIN_VALUE);
		return subAndSortList(lst, fromIndex, toIndex, comparator);
	}

	protected List<T> allDatas() {
		validate();
		List<T> lst = null;
		synchronized (sync) {
			if (dataMap != null) {
				lst = dataMap.values().stream().collect(Collectors.toList());
			}
		}
		return lst;
	}

	List<T> subAndSortList(List<T> lst, int fromIndex, int toIndex,
			Comparator<T> comparator) {
		if (fromIndex + 1 > lst.size()) {
			return null;
		}

		if (comparator != null) {
			lst.sort(comparator);
		}

		return lst.stream().skip(fromIndex).limit(toIndex - fromIndex + 1)
				.collect(Collectors.toList());
	}

	protected abstract List<T> readMapper(Mapper mapper);

	protected abstract Map<String, T> groupList(List<T> lstData);
}

