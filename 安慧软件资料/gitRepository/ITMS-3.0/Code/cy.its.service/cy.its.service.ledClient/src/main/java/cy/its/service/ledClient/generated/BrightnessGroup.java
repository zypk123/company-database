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


/**
 * 亮度周期调节信息
 **/
@SuppressWarnings("serial")
public class BrightnessGroup implements java.lang.Cloneable, java.io.Serializable
{
    public int hour;

    public int minute;

    public int second;

    public int brightness;

    public BrightnessGroup()
    {
    }

    public BrightnessGroup(int hour, int minute, int second, int brightness)
    {
        this.hour = hour;
        this.minute = minute;
        this.second = second;
        this.brightness = brightness;
    }

    public boolean
    equals(java.lang.Object rhs)
    {
        if(this == rhs)
        {
            return true;
        }
        BrightnessGroup _r = null;
        try
        {
            _r = (BrightnessGroup)rhs;
        }
        catch(ClassCastException ex)
        {
        }

        if(_r != null)
        {
            if(hour != _r.hour)
            {
                return false;
            }
            if(minute != _r.minute)
            {
                return false;
            }
            if(second != _r.second)
            {
                return false;
            }
            if(brightness != _r.brightness)
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
        __h = 5 * __h + hour;
        __h = 5 * __h + minute;
        __h = 5 * __h + second;
        __h = 5 * __h + brightness;
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
        __os.writeInt(hour);
        __os.writeInt(minute);
        __os.writeInt(second);
        __os.writeInt(brightness);
    }

    public void
    __read(IceInternal.BasicStream __is)
    {
        hour = __is.readInt();
        minute = __is.readInt();
        second = __is.readInt();
        brightness = __is.readInt();
    }
}
