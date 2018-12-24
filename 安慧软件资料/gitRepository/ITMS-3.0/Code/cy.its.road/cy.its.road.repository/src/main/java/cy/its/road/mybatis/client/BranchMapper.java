package cy.its.road.mybatis.client;

import cy.its.road.domain.model.road.Branch;

public interface BranchMapper {
    int deleteByPrimaryKey(String roadBranchId);

    int insert(Branch record);

    int insertSelective(Branch record);

    Branch selectByPrimaryKey(String roadBranchId);

    int updateByPrimaryKeySelective(Branch record);

    int updateByPrimaryKey(Branch record);
}