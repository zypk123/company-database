installutil.exe VideoRefreshService.exe
Net Start Service1
echo sc config Service1 start= auto