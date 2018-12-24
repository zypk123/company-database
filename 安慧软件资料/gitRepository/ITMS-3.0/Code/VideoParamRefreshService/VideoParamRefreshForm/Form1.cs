using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Windows.Forms;
using VideoParamRefresh;

namespace VideoParamRefreshForm
{
    public partial class Form1 : Form
    {
        public Form1()
        {
            try
            {
                VideoParamRefreshService.GetInsntace().Start();
            }
            catch (Exception ex)
            {
                tbMessage.Text = ex.Message;
            }

            InitializeComponent();
        }
    }
}
