/*!
 * jqtimeline Plugin
 * http://goto.io/jqtimeline
 *
 * Copyright 2013 goto.io
 * Released under the MIT license
 *
 */
;
(function($) {
    var gap = getWinWidth() <= 1250 ? 40 : 50;
	var pluginName = 'jqTimeline',
		defaults = {
			startYear : (new Date()).getFullYear() -1 , // Start with one less year by default
			numYears : 1,
			gap : gap, // gap between lines
			showToolTip : true,
			groupEventWithinPx : 6, // Will show common tooltip for events within this range of px
			events: [],
			timeline: [],
			click : null //Handler for click event for event
		},
	aMonths = ['0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '10', '11', '12', '13', '14', '15', '16', '17', '18', '19', '20', '21', '22', '23', '24'];

	function jqTimeLine(element, options) {
		this.options = $.extend({}, defaults, options);
		this.$el = $(element);
		this._defaults = defaults;
		this._name = pluginName;
		this._offset_x = 14; // Starting position of the line
		this._current_offset_x = 14; // var used for laying out months to the hor line
		this._gap = this.options.gap; 
		this._eDotWidth = 20; // Width of the event dot shown in the ui
		this._$toolTip = null; // use to have reference of the tooltip
		this._a$Events = []; // will store all jquery elements of events, marked on the timeline
		this._aEvents = []; //array of events obj {id,name,on}
		this._counter = 0 ; // Use to generate id for events without ids
		this.$mainContainer;
		this.init();
	}

	jqTimeLine.prototype.init = function() {
		_this = this;
		this._generateMarkup();
		//Attach a event handler to global container
		if(_this.options.click){
			_this.$mainContainer.on('click',function(e){
				var $t = $(e.target);
				if($t.hasClass('event') || $t.hasClass('msg')){
					//In both the cases eventId is stored in the format msg_eventid or event_eventid
					var eventId = $t.attr('id').split("_")[1];
					_this.options.click(e,_this._aEvents[eventId]);
				}
				if($t.hasClass('closeTooltip')){
					//we may need to close the tooltip
					var eventId = $t.attr('id').split("_")[1];
					var $tgt = $('#'+eventId);
					_this._addEventListner($tgt,'mouseleave');
					var $tooltipEl = $('#tooltip_' + eventId);
					$tooltipEl.remove();
				}
			});
		}
	};

	jqTimeLine.prototype._generateMarkup = function() {
		var _this = this;
		var i = 0,j=0;
		var totalWidth = _this.options.numYears * this._gap * 24 + 3;
		var containerWidth = totalWidth + 30;
		var $mainContainer = this.$mainContainer = $(
			'<div class="gt-timeline" style="width:'+containerWidth+'px">' + 
				'<div class="main_line" title="点击回放此时间点视频" style="width:'+totalWidth+'px"></div>' +
			'</div>'
		);
		for(j=0;j<_this.options.numYears;j++){
			for(i=0;i<24;i++){
				$mainContainer.append(_this._getMonthMarkup(i,_this.options.startYear + j));
			}
		}
		$mainContainer.append(_this._getMonthMarkup(24,_this.options.startYear + _this.options.numYears));

		//Start adding events
		for(var k=0;k<_this.options.events.length;k++){
			var e = _this.options.events[k];
			var d = e.on;
			
			$mainContainer.append(_this._getEventMarkup(e));

			//if(d.getFullYear() >= _this.options.startYear && d.getFullYear() < _this.options.startYear + _this.options.numYears){
			//	$mainContainer.append(_this._getEventMarkup(e));
			//}
		}
		
		//timeline
		for (var n = 0; n < _this.options.timeline.length; n++) {
			var t = _this.options.timeline[n];
			//alert(_this._offset_x);
			var startLeftVal = Math.ceil(_this._offset_x + 24 * _this.options.gap * ((t.startTime.getHours() + t.startTime.getMinutes() / 60) / 24));
			var endLeftVal = Math.ceil(_this._offset_x + 24 * _this.options.gap * ((t.endTime.getHours() + t.endTime.getMinutes() / 60) / 24));
			
			var $tHtml = $('<div class="hv" style="cursor: pointer;position:absolute;left:' + startLeftVal + 'px;width:' + (endLeftVal - startLeftVal) + 'px;top:50px;height:5px;background-color:#00EC00;"></div>')
			$mainContainer.append($tHtml);
		}
		
		_this.$el.append($mainContainer);
		
		$(".main_line").click(function(e){
			var offsetX = $(this).offset().left;
			var width = e.pageX - offsetX;
			
			
			var h = parseInt(width / _this.options.gap);
			var m = parseInt((width % _this.options.gap) / _this.options.gap * 60);
			h = h<10 ? "0"+h : h;
			m = m<10 ? "0"+m : m;
			
			if(h>=24){
				startTime = date + " 23:59:59";
			}
			else{
				startTime = date + " " + h+":"+m+":00";
			}
			
			$("#h").val(h);
			$("#m").val(m);

			playback();
			
		});

        $(".main_line").mousemove(function(e){
            var offsetX = $(this).offset().left;
            var width = e.pageX - offsetX;


            var h = parseInt(width / _this.options.gap);
            var m = parseInt((width % _this.options.gap) / _this.options.gap * 60);
            h = h<10 ? "0"+h : h;
            m = m<10 ? "0"+m : m;

            if(h>=24){
                startTime = " 23:59:59";
            }
            else{
                startTime = h+":"+m+":00";
            }

            $("#h").val(h);
            $("#m").val(m);

            $(".main_line").attr("title",startTime);

        });
	};

	jqTimeLine.prototype._getMonthMarkup = function(num,year){
		var _this = this;
		var retStr = "";
		retStr = '<div class="horizontal-line month-line even-month" style="left:'+_this._current_offset_x+'px"><div class="month">'+aMonths[num]+'h</div></div>';
		_this._current_offset_x += _this._gap;
		return retStr;
	}

	jqTimeLine.prototype._getGenId = function(){
		var _this = this;
		while(_this._counter in this._aEvents){
			_this._counter ++;
		}
		return _this._counter;
	}

	jqTimeLine.prototype._showToolTip=function(nLeft,strToolTip,eventId,closable){
		var _this = this;
		_this._$toolTip  = $(
								'<div class="e-message" id="tooltip_'+eventId+'" style="top:15px;left:'+ nLeft +'px">' +
									//'<div class="message-pointer"></div>' +
									strToolTip + 
								'</div>'
							);
		_this.$mainContainer.append(_this._$toolTip);
		
		
	}

	jqTimeLine.prototype._getAllNeighborEvents = function(nLeft){
		var _this = this;
		//Get all event within 10 px range. Group all event within 
		var neighborEvents = $('.event',_this.$mainContainer).filter(function(){
			var nCurrentElLeftVal = parseInt($(this).css('left'));
			return (nCurrentElLeftVal <= nLeft +  _this.options.groupEventWithinPx) && (nCurrentElLeftVal >= nLeft -  _this.options.groupEventWithinPx);
		});
		return neighborEvents;
	}



	jqTimeLine.prototype._getEventMarkup = function(e){
		var _this = this;
		//Attach id if not there
		if(typeof e.id === 'undefined') e.id = _this._getGenId();
		_this._aEvents[e.id] = e; //Add event to event array
		var eName = e.name;
		var d = e.on;
		var n = d.getHours();
		var yn = d.getFullYear() - _this.options.startYear;
		var mn = d.getMonth();
		var totalMonths = 24;//+ mn;
		var m=d.getMinutes();

		//var leftVal = Math.ceil(_this._offset_x + totalMonths * _this.options.gap + (_this.options.gap / 24) * n - _this._eDotWidth / 2);
		var leftVal = Math.ceil(_this._offset_x + totalMonths * _this.options.gap * ((n + m / 60) / 24) - _this._eDotWidth / 2);

		var $retHtml = $('<div class="event" id="event_' + e.id + '" style="left:' + leftVal + 'px;">&nbsp;</div>').data('event', e);
		$retHtml.data('eventInfo',_this._aEvents[e.id]);
		if(_this.options.click){
			_this._addEventListner($retHtml,'click');
		}
		if(_this.options.showToolTip){
			_this._addEventListner($retHtml,'mouseover');
			_this._addEventListner($retHtml,'mouseleave');
		}
		_this._a$Events[e.id] = $retHtml;
		return $retHtml;
	}

	jqTimeLine.prototype._addEventListner = function($retHtml,sEvent){
		var _this = this;
		if(sEvent == 'mouseover'){
			$retHtml.mouseover( 
				function(e){
					var $t = $(e.target);
					var nLeft = parseInt($t.css('left'));
					var eObj = $t.data('event');
					if(_this._$toolTip){
						if(_this._$toolTip.data('state') && _this._$toolTip.data('state') === 'static'){
							var eventId = _this._$toolTip.data('eventId');
							var $tgt = $('#'+eventId);
							// _this._addEventListner($tgt,'mouseover');
							_this._addEventListner($tgt,'mouseleave');
							_this._$toolTip.data('state','dynamic');
						}
						_this._$toolTip.remove();
					} 

					var neighborEvents = _this._getAllNeighborEvents(nLeft);
					var strToolTip = "" ;
					for (var i = 0; i < neighborEvents.length; i++) {
						var $temp = $(neighborEvents[i]);
						var oData = $temp.data('event');
						//strToolTip = strToolTip + '<div class="msg" id="msg_'+oData.id+'">'+oData.on.toDateString()+' : '+ oData.name +'</div>';
						strToolTip = strToolTip + '<div class="msg" style="font-size:14px;background-color:red;" id="msg_' + oData.id + '">' + oData.name + '</div>';
					};
					_this._showToolTip(nLeft,strToolTip,eObj.id,false);
				}
			);
		}
		if(sEvent == 'mouseleave'){
			$retHtml.mouseleave(function(e){
				var $targetObj = $(this);
				var eventId = $targetObj.data('event').id;
				var $tooltipEl = $('#tooltip_' + eventId);
				e.stopImmediatePropagation();
				var fixed = setTimeout(function(){
					$tooltipEl.remove();
				}, 500);
				$tooltipEl.hover(
					function(){clearTimeout (fixed);},
				    function(){$tooltipEl.remove();}
				);
			});
		}
		if(sEvent == 'click'){
		// Attach a click event handler to event objects
			$retHtml.click(function(e){
				var $targetObj = $(this);
				var eventId = $targetObj.data('event').id;
				var $tooltipEl = $('#tooltip_' + eventId);
				var $msgs = $('.msg',$tooltipEl);
				if($msgs.length == 1){
					// Do not stop the propogation .. let the parent handles the click event
					//_this.options.click();
				}else if($msgs.length > 1){
					// If the tooltip has more than one message make it non-dynamic
					e.stopPropagation(); // Stop the propogation so that the parent wont get notified
					var markup =	$('<div class="info">' + 
										'<div>Select one even from below : </div>' + 
										'<div class="icon-close closeTooltip" id="eCloseButton_'+eventId+'"></div>' + 
									'</div>');
					$tooltipEl.prepend(markup);
					// $retHtml.off('mouseover');
					$retHtml.off('mouseleave');
					$tooltipEl.data('state','static');
					$tooltipEl.data('eventId',eventId);
				}
			});
		}	
	}


	var isArray = function(a){
		return Object.prototype.toString.apply(a) === '[object Array]';
	}

	// public methods start from here 
	jqTimeLine.prototype.addEvent = function(e){
		var arr = [],i=0;
		arr = $.isArray(e) ? e : [e];
		for(i=0;i<arr.length;i++){
			var markup = this._getEventMarkup(arr[i]);
			this.$mainContainer.append(markup);
		}
	}

	jqTimeLine.prototype.deleteEvent = function(eIds){
		var _this = this;
		if(typeof eIds === 'undefined') return;
		var arr = [],i;
		if(typeof eIds === "number" || typeof eIds === "string"){
			arr = [eIds]; // ids can be string too
		}else if (isArray(eIds)){
			arr = eIds; // This can be array of objects 
		}else{
			arr = [eIds];// This can be object itself
		}
		for(i=0; i < arr.length;i++){
			var key = arr[i]; // This can be a event object itself
			if(typeof key === 'object'){
				if(typeof key.id === 'undefined') continue;
				else key = key.id;
			}
			var $obj = _this._a$Events[key];
			if(typeof $obj === 'undefined') continue;
			$obj.remove();
			delete _this._a$Events[key];
			delete _this._aEvents[key]; 
		}
	}

	jqTimeLine.prototype.getAllEvents = function(){
		var _this = this;
		var retArr = [];
		for(key in _this._aEvents){
			retArr.push(_this._aEvents[key])
		}
		return retArr;
	}

	jqTimeLine.prototype.getAllEventsElements = function(){
		var _this = this;
		var retArr = [];
		for(key in _this._a$Events){
			retArr.push(_this._a$Events[key])
		}
		return this._a$Events;
	}

	$.fn.jqtimeline = function(options) {
		return this.each(function() {
			var element = $(this);
			if(element.data('timeline')) return;
			var timeline = new jqTimeLine(this, options);
			element.data('jqtimeline', timeline);
		});
	};

}(jQuery, window));