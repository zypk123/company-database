package cy.its.device.mybatis.client;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cy.its.device.domain.model.Photo;

public interface PhotoMapper {
    int deleteByPrimaryKey(@Param("photoId") String photoId);

    int insert(Photo record);

    int insertSelective(Photo record);

    Photo selectByPrimaryKey(String photoId);

    int updateByPrimaryKeySelective(Photo record);

    int updateByPrimaryKey(Photo record);
    
    List<Photo> selectPhotoes(@Param("deviceId") String deviceId);
}