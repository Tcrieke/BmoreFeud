<!DOCTYPE HTML>
<html xmlns:ng="http://angularjs.org" id="ng-app" ng-app="app" charset="UTF-8">
<head>
    <meta charset="UTF-8">
    <title>Bmore Feud</title>
    <link rel="stylesheet" type="text/css" href="resources/css/bootstrap-responsive.min.css" />
    <%--<link rel="stylesheet" type="text/css" href="resources/css/flatybootstrap.css" />--%>
    <%--<link rel="stylesheet" type="text/css" href="resources/css/bootstrap3-panel.css" />--%>
    <%--<link rel="stylesheet" type="text/css" href="resources/css/main.css" />--%>
    <link rel="stylesheet" type="text/css" href="resources/css/flatybootstrap-old.css" />
    <link rel="stylesheet" type="text/css" href="resources/css/gameboard.css" />
    <link rel="stylesheet" type="text/css" href="resources/css/font-awesome/css/font-awesome.min.css" />
</head>
<body ng-controller="Gameboard" class="gameboard">
  <span id="site" class="ng-cloak">
    <div class="container container-navbar-pad">
        <container>
            <div class="value-section">
                <div class="row value-row " style="position:relative" ng-repeat="valueRow in valueRows">
                    <div ng-class="{'span6':true, 'value-visible': valueField.visible, 'value-available':valueField.active && !valueField.visible,'value-not-available':!valueField.active}" style="text-align: center" ng-repeat="valueField in valueRow.valueFields">
                        <row ng-show="valueField.active">
                            <div ng-class="{'span5':true, 'value-value-sm':valueField.value.length<9,'value-value-md':valueField.value.length>11 && valueField.value.length<15,'value-value-lg':valueField.value.length>14 && valueField.value.length<18,'value-value-xl':valueField.value.length>17}" ng-show="valueField.visible">{{valueField.value}}</div>
                            <div class="span1 value-points" ng-show="valueField.visible">{{valueField.points}}</div>
                            <div class="span2 value-available-number" ng-hide="valueField.visible">{{valueField.number}}</div>
                        </row>
                        <row ng-hide="valueField.active">
                            <div class="span5" ng-hide="valueField.visible">{{blankText}}</div>
                        </row>
                    </div>
                </div>
            </div>
            <div class="score-section">
                <div class="score-board">
                    {{roundScore}}
                </div>
            </div>
            <div ng-show="showX" ng-class="{'row incorrect':true, 'incorrect-count-2':incorrectCountXs.length==2,'incorrect-count-3':incorrectCountXs.length==3}">
                <div>
                    <div class="row">
                        <div class="span3 incorrect-x" ng-repeat="i in incorrectCountXs">X</div>
                    </div>
                </div>
            </div>
        </container>
    </div>

  </span>

  <script type="text/javascript" src="resources/js/thirdparty/jquery.min.js"></script>
  <script type="text/javascript" src="resources/js/thirdparty/bootstrap.min.js"></script>
  <script type="text/javascript" src="resources/js/thirdparty/angular.min.js"></script>
  <script type="text/javascript" src="resources/js/thirdparty/angular-cookies.min.js"></script>
  <script type="text/javascript" src="resources/js/thirdparty/jquery.ui.widget.js"></script>
  <script type="text/javascript" src="resources/js/thirdparty/jquery.iframe-transport.js"></script>
  <script type="text/javascript" src="resources/js/thirdparty/jquery.fileupload.js"></script>
  <script type="text/javascript" src="resources/js/thirdparty/soundmanager2-jsmin.js"></script>
  <script type="text/javascript" src="resources/js/thirdparty/spin.min.js"></script>
  <script type="text/javascript" src="resources/js/globals.js"></script>
  <script type="text/javascript" src="resources/js/controller.js"></script>
  <script type="text/javascript" src="resources/js/gameboard/gameboard.js"></script>




</body>
</html>
