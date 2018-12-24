package cy.its.service.imageQuery.cfg;

import java.util.List;

import cy.its.service.imageQuery.cfg.dataAccess.DbExecuter;

public class ServerCfg {

	public static ImageServer getServer(String deviceNbr) throws Exception {
		List<ImageServer> lstServer = DbExecuter.executeDataset(ImageServer.class, sql, deviceNbr, deviceNbr);
		return lstServer != null && lstServer.size() > 0 ? lstServer.get(0) : null;
	}

	final static String sql = 
	        "select distinct n.serverType, n.serverIp,               "+
			"       n.serverPort, n.context, n.suffix                "+
			"  from (                                                "+
			"        select d.server_plat_id                         "+
			"          from t_device_sys_component c, t_device_sys d "+
			"         where c.device_id = d.device_id                "+
			"           and c.device_nbr = ?                         "+
			"        union all                                       "+
			"        select d.server_plat_id                         "+
			"          from t_device_sys d                           "+
			"         where d.device_sys_nbr = ?                     "+
			"        ) m,                                            "+
			"       (select distinct p.server_plat_id server_plat_id,"+
			"                        a.app_type       serverType,    "+
			"                        s.server_ip      serverIp,      "+
			"                        a.server_port    serverPort,    "+
			"                        a.contex         context,       "+
			"                        a.suffix         suffix         "+
			"          from t_device_server_plat         p,          "+
			"               t_device_server_app_sso_plat pa,         "+
			"               t_device_server_application  a,          "+
			"               t_device_server              s           "+
			"         where p.server_plat_id = pa.server_plat_id     "+
			"           and pa.server_app_id = a.server_app_id       "+
			"           and a.app_type in ('4', '11')                "+
			"           and a.server_id = s.server_id) n             "+
			" where m.server_plat_id = n.server_plat_id              ";
	
//	final static String sql = 
//	                  "select                                          "
//	    			+ "       s.server_type serverType,                " 
//	    			+ "       s.server_ip serverIp,                    "
//	    			+ "       s.server_port serverPort,                "
//	    			+ "       s.contex context,                        "
//	    			+ "       s.suffix suffix                          "
//	    			+ "  from t_device_sys_component c,                "
//	    			+ "       t_device_sys d,                          " 
//	    			+ "       t_device_asso_server_type ds,            "
//	    			+ "       t_device_server_type s                   "
//	    			+ " where c.device_nbr = ?                         "
//	    			+ "       and c.device_id = d.device_id            "
//	    			+ "       and d.device_id = ds.device_id           "
//	    			+ "       and ds.server_type_id = s.server_type_id "
//	    			+ "       and s.server_type in ('4', '11')         "
//	    			+ "union                                           "
//	    			+ "select                                          "
//	    			+ "       s.server_type serverType,                "
//	    			+ "       s.server_ip serverIp,                    "
//	    			+ "       s.server_port serverPort,                "
//	    			+ "       s.contex context,                        "
//	    			+ "       s.suffix suffix                          "
//	    			+ "  from t_device_sys d,                          "
//	    			+ "       t_device_asso_server_type ds,            "
//	    			+ "       t_device_server_type s                   "
//	    			+ " where d.device_sys_nbr = ?                     "
//	    			+ "       and d.device_id = ds.device_id           "
//	    			+ "       and ds.server_type_id = s.server_type_id "
//	    			+ "       and s.server_type in ('4', '11')         ";
}
