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


final class _AMD_TgcsDeviceMgr_ModifyDevice extends IceInternal.IncomingAsync implements AMD_TgcsDeviceMgr_ModifyDevice
{
    public
    _AMD_TgcsDeviceMgr_ModifyDevice(IceInternal.Incoming in)
    {
        super(in);
    }

    public void
    ice_response(int __ret)
    {
        if(__validateResponse(true))
        {
            try
            {
                IceInternal.BasicStream __os = this.__os();
                __os.writeInt(__ret);
            }
            catch(Ice.LocalException __ex)
            {
                ice_exception(__ex);
            }
            __response(true);
        }
    }
}
