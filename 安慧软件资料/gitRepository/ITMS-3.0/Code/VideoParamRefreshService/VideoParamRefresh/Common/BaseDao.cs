using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Data.Common;
using System.Diagnostics;
using System.Text.RegularExpressions;
using System.Data.SqlClient;
using Oracle.ManagedDataAccess.Client;
using System.Data;

namespace VideoParamRefresh.Common
{

    public class BaseDAO1 : IDisposable
    {
        #region SQL语句操作
        //public IDataReader ExcuteReader(string sql, DbParameter[] parms)
        //{
        //    return DbExecuter.ExcuteReader(sql, parms);
        //}

        //public DataSet ExcuteDataSet(string sql, DbParameter[] parms)
        //{
        //    return DbExecuter.ExcuteDataSet(sql, parms);
        //}

        //public object ExecuteScalar(string sql, DbParameter[] parms)
        //{
        //    return DbExecuter.ExecuteScalar(sql, parms);
        //}

        //public int ExcuteNonQuery(string sql, DbParameter[] parms)
        //{
        //    return DbExecuter.ExcuteNonQuery(sql, parms);
        //}

        //public DataSet ExcuteDataSet(string[] sql, DbParameter[][] parms)
        //{
        //    return DbExecuter.ExcuteDataSet(sql, parms);
        //}
        #endregion

        #region IDisposable 成员

        public void Dispose()
        {
        }

        #endregion
    }

    public class BaseDAO : IDisposable
    {
        public string Db_Type = "Oracle";//ConfigValue.Value["DbType"];
        public string ConnStr =  Config.Instance.ConnStr;//ConfigValue.Value["ConnectString"];
        //static Logger log = CY.ITS_GZ601.Common.Log.LogManager.GetLogger("", "Common.DbExecuter");
        public DbConnection _Connection = null;
        #region IDisposable 成员

        public void Dispose()
        { 
            if (_Connection != null)
            {
                if (_Connection.State == ConnectionState.Open)
                {
                    _Connection.Close();
                }
                _Connection.Dispose();
                
            }
        }

        #endregion

        #region 对项目内公开的方法
        /// <summary>
        /// 读取数据库
        /// </summary>
        /// <param name="sql">数据库操作指令</param>
        /// <param name="parms">命令参数</param>
        /// <returns></returns>
        public IDataReader ExcuteReader(string sql, DbParameter[] parms)
        { 
            try
            {
                InitConnection();
                DbCommand cmd = PrepCmd(sql, parms, _Connection);
                IDataReader dr = cmd.ExecuteReader(CommandBehavior.CloseConnection);
                return dr;
            }
            catch (Exception ex)
            {
                WriteLog(sql, parms, ex.Message);
                throw ex;
            }
        }

        /// <summary>
        /// 读取数据库，返回DataSet
        /// </summary>
        /// <param name="sql">数据库操作指令</param>
        /// <param name="parms">命令参数</param>
        /// <returns></returns>
        public DataSet ExcuteDataSet(string sql, DbParameter[] parms)
        { 
            DataSet ds = new DataSet();
            try
            {
                InitConnection();
                DbCommand cmd = PrepCmd(sql, parms, _Connection);
                DbDataAdapter da = CreateDbAdapter(cmd);
                da.Fill(ds);
                cmd.Parameters.Clear();
            }
            catch (Exception ex)
            {
                WriteLog(sql, parms, ex.Message);
                throw ex;
            } 

            return ds;
        }

        /// <summary>
        /// 
        /// </summary>
        /// <param name="sqlArr"></param>
        /// <param name="parms"></param>
        /// <param name="conn"></param>
        /// <returns></returns>
        public DataSet ExcuteDataSet(string[] sqlArr, DbParameter[][] parms)
        { 
            DataSet ds = new DataSet();
            int i = 0;
            try
            {
                InitConnection();
                DbCommand cmd = _Connection.CreateCommand();
                cmd.CommandType = CommandType.Text;
                cmd.Parameters.Clear();
                DbDataAdapter da;
                DataTable dt;
                for (i = 0; i < sqlArr.Length; i++)
                {
                    cmd.CommandText = sqlArr[i];
                    if (parms != null)
                    {
                        if (parms[i] != null)
                        {
                            foreach (DbParameter parameter in parms[i])
                            {
                                if (parameter != null)
                                {
                                    cmd.Parameters.Add(parameter);
                                }
                            }
                        }
                    }

                    dt = new DataTable();
                    da = CreateDbAdapter(cmd);
                    da.Fill(dt);
                    ds.Tables.Add(dt);
                    cmd.Parameters.Clear();
                }

                return ds;
            }
            catch (Exception ex)
            {
                if (parms != null)
                {
                    WriteLog(sqlArr[i], parms[i], ex.Message);
                }
                else
                {
                    WriteLog(sqlArr[i], null, ex.Message);
                }

                throw ex;
            } 
        }

        /// <summary>
        /// 单值查询
        /// </summary>
        /// <param name="sql">查询sql</param>
        /// <param name="parms">查询参数</param>
        /// <param name="conn">数据库连接</param>
        /// <returns>单值</returns>
        public object ExecuteScalar(string sql, DbParameter[] parms)
        {
            object rslt = DBNull.Value;
            DbConnection conn = null;
            try
            {
                InitConnection();
                DbCommand cmd = PrepCmd(sql, parms, conn);
                rslt = cmd.ExecuteScalar();
                cmd.Parameters.Clear();
            }
            catch (Exception ex)
            {
                WriteLog(sql, parms, ex.Message);
                throw ex;
            } 
            return rslt;
        }

        /// <summary>
        /// 返回操作所影响的数据库条数
        /// </summary>
        /// <param name="sql">数据库操作指令</param>
        /// <param name="parms">命令参数</param>
        /// <returns></returns>
        public int ExcuteNonQuery(string sql, DbParameter[] parms)
        { 
            try
            {
                InitConnection();
                DbCommand cmd = PrepCmd(sql, parms, _Connection);
                int val = cmd.ExecuteNonQuery();
                return val;
            }
            catch (Exception ex)
            {
                WriteLog(sql, parms, ex.Message);
                throw ex;
            } 
        }
        #endregion

        #region 负责方法 

        /// <summary>
        /// 创建数据库连接
        /// </summary>
        /// <returns></returns>
        public void InitConnection()
        {
            if (_Connection == null)
            {
                if ("SqlServer".Equals(Db_Type))
                {
                    _Connection = new SqlConnection(ConnStr);
                }
                else
                {
                    _Connection = new OracleConnection(ConnStr);
                }
            } 
            if (_Connection.State != ConnectionState.Open)
            {
                _Connection.Open();
            }

            //if (_OracleCommand == null)
            //{
            //    _OracleCommand = _Connection.CreateCommand();
            //}
        }

        /// <summary>
        /// 创建适配器
        /// </summary>
        /// <param name="cmd"></param>
        /// <returns></returns>
        public DbDataAdapter CreateDbAdapter(DbCommand cmd)
        {
            DbDataAdapter da;
            if ("SqlServer".Equals(Db_Type))
            {
                SqlCommand scmd = cmd as SqlCommand;
                da = new SqlDataAdapter(scmd);
            }
            else
            {
                OracleCommand ocmd = cmd as OracleCommand;
                da = new OracleDataAdapter(ocmd);
            }
            return da;
        }

        public DbCommand PrepCmd(string sql, DbParameter[] parms, DbConnection conn)
        {
            DbCommand cmd = conn.CreateCommand();
            cmd.CommandText = sql;
            cmd.CommandType = CommandType.Text;
            cmd.Parameters.Clear();
            if (parms != null)
            {
                foreach (DbParameter param in parms)
                {
                    if (param != null)
                    {
                        cmd.Parameters.Add(param);
                    }
                }
            }
            return cmd;
        }

        #endregion

        #region WriteLog
        public void WriteLog(string sql, DbParameter[] parms, string errMsg)
        {
            StackTrace st = new StackTrace();
            string stInfo = st.ToString();
            string[] strs = Regex.Split(stInfo, "\r\n");
            string path = strs[3];
            string parameter = string.Empty;
            if (parms != null)
            {
                foreach (DbParameter item in parms)
                {
                    parameter += string.Format("{0}:{1}", item.ParameterName, item.Value);
                }
            }

            string logMsg;
            if (string.IsNullOrEmpty(errMsg))
            {
                logMsg = string.Format("运行SQL出错！位置:{0}; sql:{1}; params:{2};", path, sql, parameter);
            }
            else
            {
                logMsg = string.Format("{0} 位置:{1}; sql:{2}; params:{3};", errMsg, path, sql, parameter);
            }

            Logger.Error(logMsg);
        }
        #endregion

    }
}
