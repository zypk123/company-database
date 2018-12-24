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
// Generated from file `PlateImageStore.ice'
//
// Warning: do not edit this file.
//
// </auto-generated>


public abstract class Callback_PlateImageStore_RetrieveVehicleImages extends Ice.TwowayCallback
{
    public abstract void response(VehicleImage[] __ret);

    public final void __completed(Ice.AsyncResult __result)
    {
        PlateImageStorePrx __proxy = (PlateImageStorePrx)__result.getProxy();
        VehicleImage[] __ret = null;
        try
        {
            __ret = __proxy.end_RetrieveVehicleImages(__result);
        }
        catch(Ice.LocalException __ex)
        {
            exception(__ex);
            return;
        }
        response(__ret);
    }
}
