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
// Generated from file `tgcs_device_mgr.ice'
//
// Warning: do not edit this file.
//
// </auto-generated>


public interface _TgcsDeviceMgrDel extends Ice._ObjectDel
{
    int AddDevice(TgcsDevice[] listDev, java.util.Map<String, String> __ctx)
        throws IceInternal.LocalExceptionWrapper;

    int ModifyDevice(TgcsDevice[] listDev, java.util.Map<String, String> __ctx)
        throws IceInternal.LocalExceptionWrapper;

    int DeleteDevice(TgcsDevice[] listDev, java.util.Map<String, String> __ctx)
        throws IceInternal.LocalExceptionWrapper;

    TgcsDevice[] GetDevices(java.util.Map<String, String> __ctx)
        throws IceInternal.LocalExceptionWrapper;
}
