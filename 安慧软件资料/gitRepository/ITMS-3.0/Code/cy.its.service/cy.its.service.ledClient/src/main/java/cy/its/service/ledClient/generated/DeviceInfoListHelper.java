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
// Generated from file `tgcs_base.ice'
//
// Warning: do not edit this file.
//
// </auto-generated>


public final class DeviceInfoListHelper
{
    public static void
    write(IceInternal.BasicStream __os, DeviceInfo[] __v)
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

    public static DeviceInfo[]
    read(IceInternal.BasicStream __is)
    {
        DeviceInfo[] __v;
        final int __len0 = __is.readAndCheckSeqSize(16);
        __v = new DeviceInfo[__len0];
        for(int __i0 = 0; __i0 < __len0; __i0++)
        {
            __v[__i0] = new DeviceInfo();
            __v[__i0].__read(__is);
        }
        return __v;
    }
}
