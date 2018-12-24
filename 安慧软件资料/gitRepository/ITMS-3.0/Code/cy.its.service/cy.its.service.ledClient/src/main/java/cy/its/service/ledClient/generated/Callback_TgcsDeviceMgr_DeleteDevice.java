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


public abstract class Callback_TgcsDeviceMgr_DeleteDevice extends Ice.TwowayCallback
{
    public abstract void response(int __ret);

    public final void __completed(Ice.AsyncResult __result)
    {
        TgcsDeviceMgrPrx __proxy = (TgcsDeviceMgrPrx)__result.getProxy();
        int __ret = 0;
        try
        {
            __ret = __proxy.end_DeleteDevice(__result);
        }
        catch(Ice.LocalException __ex)
        {
            exception(__ex);
            return;
        }
        response(__ret);
    }
}
