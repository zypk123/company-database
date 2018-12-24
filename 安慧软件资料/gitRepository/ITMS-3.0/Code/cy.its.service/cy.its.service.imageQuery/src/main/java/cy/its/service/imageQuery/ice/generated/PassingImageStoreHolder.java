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


public final class PassingImageStoreHolder extends Ice.ObjectHolderBase<PassingImageStore>
{
    public
    PassingImageStoreHolder()
    {
    }

    public
    PassingImageStoreHolder(PassingImageStore value)
    {
        this.value = value;
    }

    public void
    patch(Ice.Object v)
    {
        try
        {
            value = (PassingImageStore)v;
        }
        catch(ClassCastException ex)
        {
            IceInternal.Ex.throwUOE(type(), v.ice_id());
        }
    }

    public String
    type()
    {
        return _PassingImageStoreDisp.ice_staticId();
    }
}
