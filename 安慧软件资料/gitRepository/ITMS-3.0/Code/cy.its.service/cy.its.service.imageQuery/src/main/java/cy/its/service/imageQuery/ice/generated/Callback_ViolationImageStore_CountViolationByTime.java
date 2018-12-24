// **********************************************************************
//
// Copyright (c) 2003-2010 ZeroC, Inc. All rights reserved.
//
// This copy of Ice is licensed to you under the terms described in the
// ICE_LICENSE file included in this distribution.
//
// **********************************************************************

// Ice version 3.4.0

package cy.its.service.imageQuery.ice.generated;

// <auto-generated>
//
// Generated from file `ViolationImageStore.ice'
//
// Warning: do not edit this file.
//
// </auto-generated>


public abstract class Callback_ViolationImageStore_CountViolationByTime extends Ice.TwowayCallback
{
    public abstract void response(int __ret);

    public final void __completed(Ice.AsyncResult __result)
    {
        ViolationImageStorePrx __proxy = (ViolationImageStorePrx)__result.getProxy();
        int __ret = 0;
        try
        {
            __ret = __proxy.end_CountViolationByTime(__result);
        }
        catch(Ice.LocalException __ex)
        {
            exception(__ex);
            return;
        }
        response(__ret);
    }
}
