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
// Generated from file `Base.ice'
//
// Warning: do not edit this file.
//
// </auto-generated>


public final class VehicleImageDescriptionSeqHelper
{
    public static void
    write(IceInternal.BasicStream __os, VehicleImageDescription[] __v)
    {
        if(__v == null)
        {
            __os.writeSize(0);
        }
        else
        {
            __os.writeSize(__v.length);
            for(int __i0 = 0; __i0 < __v.length; __i0++)
            {
                __v[__i0].__write(__os);
            }
        }
    }

    public static VehicleImageDescription[]
    read(IceInternal.BasicStream __is)
    {
        VehicleImageDescription[] __v;
        final int __len0 = __is.readAndCheckSeqSize(37);
        __v = new VehicleImageDescription[__len0];
        for(int __i0 = 0; __i0 < __len0; __i0++)
        {
            __v[__i0] = new VehicleImageDescription();
            __v[__i0].__read(__is);
        }
        return __v;
    }
}
