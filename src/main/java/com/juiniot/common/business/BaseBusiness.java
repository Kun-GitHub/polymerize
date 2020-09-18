package com.juiniot.common.business;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Set;

/**
 * 业务逻辑父类
 */
public abstract class BaseBusiness implements java.io.Serializable{
	
	private static final long serialVersionUID = 1L;
	
	// 日志对象
    protected final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	protected abstract void onAdd() throws BusinessException;//外层实现的添加
	protected abstract void onModify() throws BusinessException;//外层实现的修改
	protected abstract void onDelete(Long ids[]) throws BusinessException;//外层实现的删除

    public String getBusinessName(){
        return "";
    }

    public String getModel() {
        return "";
    }

	//增加
	public void add() throws BusinessException{
		onAdd();//回调
		logger.info("业务类:"+this.getClass().getName()+" 进行了数据增加。");//写日志
	}
	
	//修改
	public void modify() throws BusinessException{
		onModify();//回调
		logger.info("业务类:"+this.getClass().getName()+" 进行了数据修改。");//写日志
	}
	
	//删除
	public void delete(Long ids[]) throws BusinessException{
		onDelete(ids);//回调
		logger.info("业务类:"+this.getClass().getName()+" 进行了数据删除。");//写日志
	}
	
	/**
	 * 
	 * @param keyMap
	 * @return
	 */
	protected static <T> HashMap<String, Object> toParamMap(HashMap<T, Object> keyMap) {
		
		HashMap<String, Object> map = new HashMap<>();
		Set<T> keys = keyMap.keySet();
		for (T key : keys) {
			map.put(key.toString(), keyMap.get(key));
		}
		return map;
	}
	
}
