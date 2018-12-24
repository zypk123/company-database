/**
 *
 */
OpenLayers.Layer.ArcGISTileMapLayer = OpenLayers.Class(OpenLayers.Layer.TMS, {

	tileOriginCorner : 'tl',

	type : 'png',

	initialize : function(name, url, options) {

		OpenLayers.Layer.TMS.prototype.initialize.apply(this, [name, url, options]);
	},
	tileOrigin : new OpenLayers.LonLat(-400, 400),
	
	getURL : function(bounds) {
		var z = this.map.getZoom();
		var res = this.map.getResolution(z);
		var x = parseInt((bounds.getCenterLonLat().lon - this.tileOrigin.lon) / (256 * res));
		var y = parseInt((this.tileOrigin.lat - bounds.getCenterLonLat().lat) / (256 * res));

		x = 'C' + OpenLayers.Number.zeroPad(x, 8, 16);
		y = 'R' + OpenLayers.Number.zeroPad(y, 8, 16);
		z = 'L' + OpenLayers.Number.zeroPad(z, 2, 10);

		return this.url + z + '/' + y + '/' + x + '.' + this.type;
	},

	CLASS_NAME : "OpenLayers.Layer.ArcGISTileMapLayer"
});
