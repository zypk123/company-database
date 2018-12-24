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


public class SpeedLimit implements java.lang.Cloneable, java.io.Serializable
{
    public String vehicleType;

    public int lane;

    public int roadOverSpeedLimit;

    public int roadUnderSpeedLimit;

    public int overSpeedMargin;

    public int underSpeedMargin;

    public SpeedLimit()
    {
    }

    public SpeedLimit(String vehicleType, int lane, int roadOverSpeedLimit, int roadUnderSpeedLimit, int overSpeedMargin, int underSpeedMargin)
    {
        this.vehicleType = vehicleType;
        this.lane = lane;
        this.roadOverSpeedLimit = roadOverSpeedLimit;
        this.roadUnderSpeedLimit = roadUnderSpeedLimit;
        this.overSpeedMargin = overSpeedMargin;
        this.underSpeedMargin = underSpeedMargin;
    }

    public boolean
    equals(java.lang.Object rhs)
    {
        if(this == rhs)
        {
            return true;
        }
        SpeedLimit _r = null;
        try
        {
            _r = (SpeedLimit)rhs;
        }
        catch(ClassCastException ex)
        {
        }

        if(_r != null)
        {
            if(vehicleType != _r.vehicleType && vehicleType != null && !vehicleType.equals(_r.vehicleType))
            {
                return false;
            }
            if(lane != _r.lane)
            {
                return false;
            }
            if(roadOverSpeedLimit != _r.roadOverSpeedLimit)
            {
                return false;
            }
            if(roadUnderSpeedLimit != _r.roadUnderSpeedLimit)
            {
                return false;
            }
            if(overSpeedMargin != _r.overSpeedMargin)
            {
                return false;
            }
            if(underSpeedMargin != _r.underSpeedMargin)
            {
                return false;
            }

            return true;
        }

        return false;
    }

    public int
    hashCode()
    {
        int __h = 0;
        if(vehicleType != null)
        {
            __h = 5 * __h + vehicleType.hashCode();
        }
        __h = 5 * __h + lane;
        __h = 5 * __h + roadOverSpeedLimit;
        __h = 5 * __h + roadUnderSpeedLimit;
        __h = 5 * __h + overSpeedMargin;
        __h = 5 * __h + underSpeedMargin;
        return __h;
    }

    public java.lang.Object
    clone()
    {
        java.lang.Object o = null;
        try
        {
            o = super.clone();
        }
        catch(CloneNotSupportedException ex)
        {
            assert false; // impossible
        }
        return o;
    }

    public void
    __write(IceInternal.BasicStream __os)
    {
        __os.writeString(vehicleType);
        __os.writeInt(lane);
        __os.writeInt(roadOverSpeedLimit);
        __os.writeInt(roadUnderSpeedLimit);
        __os.writeInt(overSpeedMargin);
        __os.writeInt(underSpeedMargin);
    }

    public void
    __read(IceInternal.BasicStream __is)
    {
        vehicleType = __is.readString();
        lane = __is.readInt();
        roadOverSpeedLimit = __is.readInt();
        roadUnderSpeedLimit = __is.readInt();
        overSpeedMargin = __is.readInt();
        underSpeedMargin = __is.readInt();
    }
}
