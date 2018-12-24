package cy.its.vehTrack.mybatis.client;

import java.util.List;
import java.util.Map;

import cy.its.vehTrack.mybatis.model.Pass;

public interface PassMapper {
    List<Pass> findPassInfo(Map map);
}