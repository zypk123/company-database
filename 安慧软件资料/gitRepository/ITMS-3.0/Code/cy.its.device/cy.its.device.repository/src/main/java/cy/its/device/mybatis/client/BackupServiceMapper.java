package cy.its.device.mybatis.client;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cy.its.device.domain.model.BackupService;

public interface BackupServiceMapper {
    int deleteByPrimaryKey(String serviceId);

    int insert(BackupService record);

    int insertSelective(BackupService record);

    BackupService selectByPrimaryKey(String serviceId);

    int updateByPrimaryKeySelective(BackupService record);

    int updateByPrimaryKey(BackupService record);
    
    List<BackupService> selectBackupServices(@Param("serverId") String serverId);
}