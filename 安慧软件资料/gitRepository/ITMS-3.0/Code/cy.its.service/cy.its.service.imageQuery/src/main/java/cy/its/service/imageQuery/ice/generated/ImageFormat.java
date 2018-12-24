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


public enum ImageFormat implements java.io.Serializable
{
    
    Gray,
    
    Bayer8GBRG,
    
    Bayer8GRBG,
    
    Bayer8BGGR,
    
    Bayer8RGGB,
    
    RGB24RGB,
    
    RGB24BGR,
    
    Data24YCBCR,
    
    YUV422UYVY,
    
    YUV422YUYV,
    
    Jpeg,
    
    Jpeg2000,
    
    Raw8,
    
    Raw10,
    
    Raw12,
    
    Raw16,
    
    RecordingClip;

    public void
    __write(IceInternal.BasicStream __os)
    {
        __os.writeByte((byte)ordinal());
    }

    public static ImageFormat
    __read(IceInternal.BasicStream __is)
    {
        int __v = __is.readByte(17);
        return values()[__v];
    }
}
