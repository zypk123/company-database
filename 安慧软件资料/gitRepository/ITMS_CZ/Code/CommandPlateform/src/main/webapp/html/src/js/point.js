/**
 * 点对象，继承OpenLayers.Geometry.Point对象
 */

function Point (x, y) {
    this.x = parseFloat(x);
    this.y = parseFloat(y);
}

Point.prototype = new OpenLayers.Geometry.Point(0,0);

Point.prototype.isValid = function() {
    if((this.x != null) && (this.y != null) && (this.x != NaN) && (this.y != NaN))
        return( true );
    else
        return( false );
};

Point.prototype.geoIsValid = function() {
    if( this.isValid() && (this.x >= -180) && (this.x <= 180) && (this.y >= -90) && (this.y <= 90)) 
        return( true );
    else
        return( false );
};

/**
 *    Geo Constants
 */
// Point.EARTH_RADIUS = 3958.75;    // in miles
Point.EARTH_RADIUS = 6370.856; // in km
Point.DEG2RAD =  0.01745329252;  // factor to convert degrees to radians (PI/180)
Point.RAD2DEG = 57.29577951308;

/**
 *    Method: geoDistanceTo
 *
 *    Parameters:
 *    point - {<Point>}
 *
 *    Returns:
 *    Great Circle distance (in miles) to Point. 
 *    Coordinates must be in decimal degrees.
 *    
 *    Reference:
 *    Williams, Ed, 2000, "Aviation Formulary V1.43" web page
 *    http://williams.best.vwh.net/avform.htm
 */
Point.prototype.geoDistanceTo = function( point ) {
var x = [];
var y = [];

    if( this.geoIsValid() && point.geoIsValid()) {
        x[0] = this.x * Point.DEG2RAD;    y[0] = this.y * Point.DEG2RAD;
        x[1] = point.x * Point.DEG2RAD;    y[1] = point.y * Point.DEG2RAD;
        
        var a = Math.pow( Math.sin(( y[1]-y[0] ) / 2.0 ), 2);
        var b = Math.pow( Math.sin(( x[1]-x[0] ) / 2.0 ), 2);
        var c = Math.pow(( a + Math.cos( y[1] ) * Math.cos( y[0] ) * b ), 0.5);
    
        return ( 2 * Math.asin( c ) * Point.EARTH_RADIUS );
    } else
        return null;
};


Point.prototype.geoBearingTo = function( point ) {
var x = new Array(2);
var y = new Array(2);
var bearing;
var adjust;

    if( this.geoIsValid() && point.geoIsValid()) {
        x[0] = this.x * Point.DEG2RAD;    y[0] = this.y * Point.DEG2RAD;
        x[1] = point.x * Point.DEG2RAD;    y[1] = point.y * Point.DEG2RAD;

         var a = Math.cos(y[1]) * Math.sin(x[1] - x[0]);
        var b = Math.cos(y[0]) * Math.sin(y[1]) - Math.sin(y[0]) 
            * Math.cos(y[1]) * Math.cos(x[1] - x[0]);
        
        if((a == 0) && (b == 0)) {
            bearing = 0;
            return bearing;
        }
        
        if( b == 0) {
            if( a < 0)  
                bearing = 270;
            else
                bearing = 90;
            return bearing;
        }
         
        if( b < 0) 
            adjust = Math.PI;
        else {
            if( a < 0) 
                adjust = 2 * Math.PI;
            else
                adjust = 0;
        }
        bearing = (Math.atan(a/b) + adjust) * Point.RAD2DEG;
        return bearing;
    } else
        return null;
};


/**
 *
 */
Point.prototype.geoWaypoint = function( distance, bearing ) {
var wp = new Point( 0, 0 );

    // Math.* trig functions require angles to be in radians
    var x = this.x * Point.DEG2RAD;
    var y = this.y * Point.DEG2RAD;
    var radBearing = bearing * Point.DEG2RAD;

    // Convert arc distance to radians
    var c = distance / Point.EARTH_RADIUS;

    wp.y = Math.asin( Math.sin(y) * Math.cos(c) + Math.cos(y) * Math.sin(c) * Math.cos(radBearing)) * Point.RAD2DEG;

    var a = Math.sin(c) * Math.sin(radBearing);
    var b = Math.cos(y) * Math.cos(c) - Math.sin(y) * Math.sin(c) * Math.cos(radBearing);

    if( b == 0 ) 
        wp.x = this.x;
    else
        wp.x = this.x + Math.atan(a/b) * Point.RAD2DEG;

    return wp;
};

/**********公共方法*******************************************************************************/
function getNextPoint  (currentPoint, stopPoint) {
    var nextPoint = getPointOnLineByDistance(currentPoint, stopPoint, 3000);//3000米，表示每隔3000米找下一个点

    var x_current_stop = Math.abs(stopPoint.x - currentPoint.x);
    var x_current_next = Math.abs(nextPoint.x - currentPoint.x);
    var y_current_stop = Math.abs(stopPoint.y - currentPoint.y);
    var y_current_next = Math.abs(nextPoint.y - currentPoint.y);

    if(x_current_next > x_current_stop || y_current_next > y_current_stop) {
        return stopPoint;
    }
    return nextPoint;
}

function getPointOnLineByDistance (p1, p2, distance) {

    var ppp1 = new Point(p1.x, p1.y);
    var ppp2 = new Point(p2.x, p2.y);

    var bearing = ppp1.geoBearingTo(ppp2);
    var ppp3 = ppp1.geoWaypoint(distance / 1000, bearing);

    return new OpenLayers.Geometry.Point(ppp3.x, ppp3.y);
}

