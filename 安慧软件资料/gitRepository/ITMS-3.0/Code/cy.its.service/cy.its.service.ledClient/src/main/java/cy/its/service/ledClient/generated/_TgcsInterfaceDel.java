// **********************************************************************
//
// Copyright (c) 2003-2010 ZeroC, Inc. All rights reserved.
//
// This copy of Ice is licensed to you under the terms described in the
// ICE_LICENSE file included in this distribution.
//
// **********************************************************************

// Ice version 3.4.0

package cy.its.service.ledClient.generated;

// <auto-generated>
//
// Generated from file `tgcs_interface.ice'
//
// Warning: do not edit this file.
//
// </auto-generated>


public interface _TgcsInterfaceDel extends Ice._ObjectDel
{
    OnlineStatusResponse GetOnlineStatus(DeviceInfo devInfo, java.util.Map<String, String> __ctx)
        throws IceInternal.LocalExceptionWrapper;

    ScreenStatusResponse GetScreenStatus(DeviceInfo devInfo, java.util.Map<String, String> __ctx)
        throws IceInternal.LocalExceptionWrapper;

    LedErrorResponse GetLedError(DeviceInfo devInfo, java.util.Map<String, String> __ctx)
        throws IceInternal.LocalExceptionWrapper;

    int SetLedPower(DeviceInfo devInfo, int power, java.util.Map<String, String> __ctx)
        throws IceInternal.LocalExceptionWrapper;

    int SetLedPowerTimed(DeviceInfo devInfo, PowerGroup[] listPower, java.util.Map<String, String> __ctx)
        throws IceInternal.LocalExceptionWrapper;

    int SetLedBrightness(DeviceInfo devInfo, int brightness, java.util.Map<String, String> __ctx)
        throws IceInternal.LocalExceptionWrapper;

    int SetLedBrightnessTimed(DeviceInfo devInfo, BrightnessGroup[] listBrightness, java.util.Map<String, String> __ctx)
        throws IceInternal.LocalExceptionWrapper;

    int SyncLedTime(DeviceInfo devInfo, java.util.Map<String, String> __ctx)
        throws IceInternal.LocalExceptionWrapper;

    ProgramResponse AddProgram(DeviceInfo devInfo, Program prog, java.util.Map<String, String> __ctx)
        throws IceInternal.LocalExceptionWrapper;

    ProgramResponse GetPlayingProgram(DeviceInfo devInfo, java.util.Map<String, String> __ctx)
        throws IceInternal.LocalExceptionWrapper;

    ProgramResponse ModifyProgram(DeviceInfo devInfo, Program prog, java.util.Map<String, String> __ctx)
        throws IceInternal.LocalExceptionWrapper;

    ProgramResponse DeleteProgram(DeviceInfo devInfo, int programNo, java.util.Map<String, String> __ctx)
        throws IceInternal.LocalExceptionWrapper;

    ProgramResponse GetPrograms(DeviceInfo devInfo, java.util.Map<String, String> __ctx)
        throws IceInternal.LocalExceptionWrapper;

    ProgramResponse ClearProgram(DeviceInfo devInfo, java.util.Map<String, String> __ctx)
        throws IceInternal.LocalExceptionWrapper;

    ProgramResponse ExcludeProgram(DeviceInfo devInfo, Program prog, java.util.Map<String, String> __ctx)
        throws IceInternal.LocalExceptionWrapper;

    int AddDevice(DeviceInfo[] listDev, java.util.Map<String, String> __ctx)
        throws IceInternal.LocalExceptionWrapper;

    int ModifyDevice(DeviceInfo[] listDev, java.util.Map<String, String> __ctx)
        throws IceInternal.LocalExceptionWrapper;

    int DeleteDevice(DeviceInfo[] listDev, java.util.Map<String, String> __ctx)
        throws IceInternal.LocalExceptionWrapper;

    DeviceInfo[] GetDevice(java.util.Map<String, String> __ctx)
        throws IceInternal.LocalExceptionWrapper;
}
