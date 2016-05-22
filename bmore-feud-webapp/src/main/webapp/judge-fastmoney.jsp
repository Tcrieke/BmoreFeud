<!DOCTYPE HTML>
<html xmlns:ng="http://angularjs.org" id="ng-app" ng-app="app" charset="UTF-8">
<head>
    <meta charset="UTF-8">
    <title>Bmore Feud (Admin)</title>
    <link rel="stylesheet" type="text/css" href="resources/css/bootstrap-responsive.min.css" />
    <%--<link rel="stylesheet" type="text/css" href="resources/css/flatybootstrap.css" />--%>
    <%--<link rel="stylesheet" type="text/css" href="resources/css/bootstrap3-panel.css" />--%>
    <%--<link rel="stylesheet" type="text/css" href="resources/css/main.css" />--%>
    <link rel="stylesheet" type="text/css" href="resources/css/flatybootstrap-old.css" />
    <link rel="stylesheet" type="text/css" href="resources/css/judge-fastmoney.css" />
    <link rel="stylesheet" type="text/css" href="resources/css/font-awesome/css/font-awesome.min.css" />
</head>
<body ng-controller="JudgeFastMoney" class="gameboard">
  <span id="site" class="ng-cloak">
        <div class="top-section">
            <div class="player-button-row">
                <div class="col-md-4 text-center">
                    <i ng-class="{'icon-check icon-3x':currentPlayer.id==1,'icon-check-empty icon-3x':currentPlayer.id!=1,'player-view-circle':true}" ng-click="toggleCurrentPlayer(1)"></i>
                    <button type="button" ng-click="togglePlayer(1)" ng-class="{'btn player-button':true, 'btn-primary':!player1, 'btn-info':player1}">
                        P1
                    </button>
                    <button type="button" ng-click="togglePlayer(2)" ng-class="{'btn player-button':true, 'btn-primary':!player2, 'btn-info':player2}">
                        P2
                    </button>
                    <i ng-class="{'icon-check icon-3x':currentPlayer.id==2,'icon-check-empty icon-3x':currentPlayer.id!=2,'player-view-circle':true}" ng-click="toggleCurrentPlayer(2)"></i>
                    <a ng-click="toFamilyFeud()" >
                        Back to Family Feud
                    </a>
                </div>
            </div>
        </div>
        <div class="category-title text-center">
            {{'Category: '+categories[selectedCategoryIndex].name}}
        </div>
        <div class="main-section">
            <div class="field-container">
                <div ng-class="{'row text-center':true,'selected-field-container':$index==selectedCategoryIndex}" ng-repeat="category in categories">
                    <div class="row-fluid">
                        <i ng-class="{'icon-check icon-2x':$index==selectedCategoryIndex,'icon-check-empty icon-2x':$index!=selectedCategoryIndex,'category-select-circle':true}" ng-click="selectCategory($index)"></i>
                        <button type="button" ng-click="flipField(valueField,category)" ng-class="{'col-md-1 btn field-button':true, 'btn-primary':!valueField.visible, 'btn-info':valueField.visible}" ng-repeat="valueField in category.values">
                            {{valueField.value}}
                        </button>
                        <div class="field-button answer-field" ng-dblclick="editAnswer($index,true)" ng-hide="{{'answer'+$index+'Edit'}}" >{{answerFields[$index]}}</div>
                        <input ng-show="{{'answer'+$index+'Edit'}}" ng-model="answerFields[$index]" class="field-button answer-field" id="{{ 'answer'+$index+'Input' }}" type="text" onblur="angular.element(this).scope().onAnswerChange()" focus-me="{{'answer'+$index+'EditFocus'}}" ng-enter="blurField()">
                        <i ng-class="{'icon-check icon-2x':answers[$index].visible,'icon-check-empty icon-2x':!answers[$index].visible,'category-select-circle':true}" ng-click="toggleAnswerVisible($index)"></i>
                        <i ng-class="{'icon-check icon-2x':answers[$index].valueVisible,'icon-check-empty icon-2x':!answers[$index].valueVisible,'category-select-circle':true}" ng-click="toggleAnswerValueVisible($index)"></i>
                    </div>
                </div>
            </div>

        </div>
        <button type="button" ng-click="newGame()" ng-class="{'btn field-button':true}">
            New Game
        </button>

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
  <script type="text/javascript" src="resources/js/judge-fastmoney/judge-fastmoney.js"></script>




</body>
</html>
