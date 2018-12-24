using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Diagnostics;
using System.Linq;
using System.ServiceProcess;
using System.Text;
using VideoParamRefresh; 

namespace VideoRefreshService
{
    public partial class Service1 : ServiceBase
    {
        public Service1()
        {
            InitializeComponent();
        }

        protected override void OnStart(string[] args)
        {
            VideoParamRefreshService.GetInsntace().Start();
        }

        protected override void OnStop()
        {

        }
    }
}
