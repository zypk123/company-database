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


final class _AMD_ViolationImageStore_StoreViolation2 extends IceInternal.IncomingAsync implements AMD_ViolationImageStore_StoreViolation2
{
    public
    _AMD_ViolationImageStore_StoreViolation2(IceInternal.Incoming in)
    {
        super(in);
    }

    public void
    ice_response(boolean __ret, ViolationVehicle savedViolation)
    {
        if(__validateResponse(true))
        {
            try
            {
                IceInternal.BasicStream __os = this.__os();
                savedViolation.__write(__os);
                __os.writeBool(__ret);
            }
            catch(Ice.LocalException __ex)
            {
                ice_exception(__ex);
            }
            __response(true);
        }
    }
}
