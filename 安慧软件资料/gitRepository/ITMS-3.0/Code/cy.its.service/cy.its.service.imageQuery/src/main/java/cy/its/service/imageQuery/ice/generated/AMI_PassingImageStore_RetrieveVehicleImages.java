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
// Generated from file `PassingImageStore.ice'
//
// Warning: do not edit this file.
//
// </auto-generated>


public abstract class AMI_PassingImageStore_RetrieveVehicleImages extends Callback_PassingImageStore_RetrieveVehicleImages
{
    public abstract void ice_response(VehicleImage[] __ret);

    /**
     * ice_exception indicates to the caller that
     * the operation completed with an exception.
     * @param ex The Ice run-time exception to be raised.
     **/
    public abstract void ice_exception(Ice.LocalException ex);

    public final void response(VehicleImage[] __ret)
    {
        ice_response(__ret);
    }

    public final void exception(Ice.LocalException __ex)
    {
        ice_exception(__ex);
    }

    @Override public final void sent(boolean sentSynchronously)
    {
        if(!sentSynchronously && this instanceof Ice.AMISentCallback)
        {
            ((Ice.AMISentCallback)this).ice_sent();
        }
    }
}
