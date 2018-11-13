package com.huntkey.rx.base;


public interface ServiceInterface<T> {
	/**
	 * 根据 id 加载数据
	 * @return
	 */
	public T load();
	
	/**
	 * 根据ID 删除数据
	 * @param uuid
	 * @return
	 */
	public boolean delete(String uuid);
	
	/**
	 * 更新当个对象数据
	 * @param t
	 * @return
	 */
	public boolean update(T t);
	
	/**
	 * 新增单个对象数据
	 * @param t
	 * @return
	 */
	public boolean add(T t);
	
}
