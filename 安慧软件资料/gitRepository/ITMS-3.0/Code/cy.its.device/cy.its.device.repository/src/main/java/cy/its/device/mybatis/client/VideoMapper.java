package cy.its.device.mybatis.client;

import cy.its.device.domain.model.Video;

public interface VideoMapper {
    int deleteByPrimaryKey(String deviceId);

    int insert(Video record);

    int insertSelective(Video record);

    Video selectByPrimaryKey(String deviceId);

    int updateByPrimaryKeySelective(Video record);

    int updateByPrimaryKey(Video record);

	Video sysVideoOfCameraSn(String cameraSn);
}