package cy.its.device.mybatis.client.led;

import java.util.List;
import cy.its.device.mybatis.model.ZAB;

public interface ZinkMapper {
    List<ZAB> select();
    int insert(ZAB record);
	int update(ZAB record);	
}
