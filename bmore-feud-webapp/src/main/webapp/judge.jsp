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
    <link rel="stylesheet" type="text/css" href="resources/css/judge.css" />
    <link rel="stylesheet" type="text/css" href="resources/css/font-awesome/css/font-awesome.min.css" />
</head>
<body ng-controller="Judge" class="gameboard">
  <span id="site" class="ng-cloak">
    <div class="container">
        <container>
            <div class="row top-section">
                <div class="span12">
                    <div class="span3 team-scoreboard">
                        <div ng-hide="team1NameEdit" ng-dblclick="editTeamName(1,true)">{{gameboard.team1Name}}</div>
                        <input ng-show="team1NameEdit" ng-model="team1Name" class="team-edit-field" id="team1NameInput" type="text" onblur="angular.element(this).scope().onTeamNameChange(1)" focus-me="team1NameEditFocus" ng-enter="blurField()">
                        <div ng-hide="team1ScoreEdit" ng-dblclick="editTeamScore(1,true)">{{gameboard.team1Score}}</div>
                        <input ng-show="team1ScoreEdit" ng-model="team1Score" class="team-edit-field" id="team1ScoreInput" type="text"  onblur="angular.element(this).scope().onTeamScoreChange(1)" focus-me="team1ScoreEditFocus" ng-enter="blurField()">
                    </div>
                    <div class="span2 team-arrow" ng-click="setTeamPlaying(1)">
                        <div ng-class="{'team-arrow-enabled':gameboard.teamPlaying==1,'team-arrow-disabled':gameboard.teamPlaying!=1}">
                            <i class="icon-caret-left icon-3x"></i>
                        </div>
                    </div>
                    <div ng-class="{'span2 game-mode':true, 'game-mode-faceoff':gameboard.gameMode=='FACEOFF', 'game-mode-normal':gameboard.gameMode=='NORMAL', 'game-mode-rebuttal':gameboard.gameMode=='REBUTTAL'}">
                        {{gameboard.gameMode}}
                    </div>
                    <div class="span2 team-arrow" ng-click="setTeamPlaying(2)">
                        <div ng-class="{'team-arrow-enabled':gameboard.teamPlaying==2,'team-arrow-disabled':gameboard.teamPlaying!=2}">
                            <i class="icon-caret-right icon-3x"></i>
                        </div>
                    </div>
                    <div class="span3 team-scoreboard">
                        <div ng-hide="team2NameEdit" ng-dblclick="editTeamName(2,true)">{{gameboard.team2Name}}</div>
                        <input ng-show="team2NameEdit" ng-model="team2Name" class="team-edit-field" id="team2NameInput" type="text" onblur="angular.element(this).scope().onTeamNameChange(2)" focus-me="team2NameEditFocus" ng-enter="blurField()">
                        <div ng-hide="team2ScoreEdit" ng-dblclick="editTeamScore(2,true)">{{gameboard.team2Score}}</div>
                        <input ng-show="team2ScoreEdit" ng-model="team2Score" class="team-edit-field" id="team2ScoreInput" type="text"  onblur="angular.element(this).scope().onTeamScoreChange(2)" focus-me="team2ScoreEditFocus" ng-enter="blurField()">
                    </div>
                </div>
            </div>
            <div class="row category-title text-center">
                {{'Category: '+gameboard.category.name}}
            </div>
            <div class="row main-section">
                <div class="span12">
                    <div class="span6 field-container">
                        <div ng-show="valueField.active" class="row field-button-row" ng-repeat="valueField in valueFields">
                            <div ng-class="{'span3 field-button-area':true}">
                                <row>
                                    <button type="button" ng-click="flipField(valueField)" ng-class="{'btn field-button':true, 'btn-primary':valueField.active, 'btn-success':valueField.visible, 'btn-info':!valueField.active}">
                                        {{valueField.value}}
                                    </button>
                                </row>
                            </div>
                        </div>
                    </div>
                    <div class="span6 field-container">
                        <div class="incorrect-area">
                            <div ng-class="{'row incorrect':true, 'incorrect-count-2':incorrectCountXs.length==2,'incorrect-count-3':incorrectCountXs.length==3}">
                                <div class="span2 incorrect-x" ng-repeat="i in incorrectCountXs">X</div>
                            </div>
                            <div class="row incorrect-button-row text-center">
                                <button type="button" ng-click="subIncorrect()" ng-class="{'btn btn-small btn-danger':true,'disabled':incorrectEmpty()}">
                                    -
                                </button>
                                <button type="button" ng-click="markIncorrect()" ng-class="{'btn btn-large btn-danger':true,'disabled':incorrectFull()}">
                                    Incorrect Answer
                                </button>
                                <button type="button" ng-click="markIncorrect(true)" ng-class="{'btn btn-small btn-danger':true,'disabled':incorrectFull()}">
                                    +
                                </button>
                            </div>
                        </div>
                        <div class="game-options-area">
                            <div class="row options-button-row text-center">
                                <button type="button" ng-click="newCategory()" class="btn btn-large btn-primary">
                                    New Category
                                </button>
                            </div>
                        </div>
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
  <script type="text/javascript" src="resources/js/judge/judge.js"></script>




</body>
</html>
