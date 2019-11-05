/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
var app = {
    // Application Constructor
    initialize: function() {
        document.addEventListener('deviceready', this.onDeviceReady.bind(this), false);
        onUserNavigation();
    },

    // deviceready Event Handler
    // Bind any cordova events here. Common events are:
    // 'pause', 'resume', etc.
    onDeviceReady: function() {
        this.receivedEvent('deviceready');
            document.addEventListener("deviceready", onUserNavigation, false);
    },

    // Update DOM on a Received Event
    receivedEvent: function(id) {

        var parentElement = document.getElementById(id);
        var listeningElement = parentElement.querySelector('.listening');
        var receivedElement = parentElement.querySelector('.received');

        listeningElement.setAttribute('style', 'display:none;');
        receivedElement.setAttribute('style', 'display:block;');

        console.log('Received Event: ' + id);
    }

};

app.initialize();


function onUserNavigation()
{
console.log('onUserNavigation Called');
 var userJourney = {
  screenName : window.location.href
 }
  ReCordovaPlugin.screenNavigation(userJourney);
}


function onNativeCallBackListeners(data)
{
  var action = data.actionName;

  var action = data.data;

}


function getInnercontent() {
    var maintag = [];

    $('input,button,select,textarea').each(function () {
        console.log($(this).attr('id'));
        var subdiv = new Object();
       // if ($(this).is(':visible') && $(this).attr('id') != '' && $(this).attr('id') != undefined)
            getAtributes(subdiv, maintag, $(this));

    });

    $('embed,iframe').each(function () {
        var subdiv = new Object();
       // if ($(this).is(':visible') && $(this).attr('id') != '' && $(this).attr('id') != undefined) {
            if ($(this).attr('src') != null && $(this).attr('src') != "") {
                subdiv["tagurl"] = $(this).prop("src");
                getAtributes(subdiv, maintag, $(this));
            }
       // }
    });

    $('video,audio').each(function () {
        var subdiv = new Object();
       // if ($(this).is(':visible') && $(this).attr('id') != '' && $(this).attr('id') != undefined) {
            if ($(this).attr('src') != null && $(this).attr('src') != "")
                getAtributes(subdiv, maintag, $(this));
      //  }

    });

    $('object').each(function () {
        var subdiv = new Object();
      //  if ($(this).is(':visible') && $(this).attr('id') != '' && $(this).attr('id') != undefined) {
            if ($(this).attr('data') != null && $(this).attr('data') != "") {
                subdiv["tagurl"] = $(this).prop("data");
                getAtributes(subdiv, maintag, $(this));
            }
      //  }

    });
    $('a').each(function () {
        var subdiv = new Object();
      //  if ($(this).is(':visible') && !$(this).parents('.dropdown-menu').length && $(this).attr('id') != '' && $(this).attr('id') != undefined)
            getAtributes(subdiv, maintag, $(this))


    });
    console.log(maintag);
    return JSON.stringify(maintag);
}


function getAtributes(subdiv, maintag, object) {
    var position = object.offset();
    subdiv["left"] = position.left;
    subdiv["top"] = position.top;
    subdiv["height"] = object.outerHeight();
    subdiv["width"] = object.outerWidth();
    subdiv["viewid"] = object.attr("id");
    subdiv["viewname"] = object.attr("name");
    subdiv["tagname"] = object.prop("tagName");
    subdiv["tagtype"] = object.prop("type");
	subdiv["href"] = object.prop("href");
    maintag.push(subdiv);
}
