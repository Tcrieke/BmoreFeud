<!DOCTYPE HTML>
<html xmlns:ng="http://angularjs.org" id="ng-app" ng-app="app" charset="UTF-8">
<head>
    <meta charset="UTF-8">
    <title>Bmore Feud (Team 2 Scoreboard)</title>
    <link rel="stylesheet" type="text/css" href="resources/css/bootstrap-responsive.min.css" />
    <%--<link rel="stylesheet" type="text/css" href="resources/css/flatybootstrap.css" />--%>
    <%--<link rel="stylesheet" type="text/css" href="resources/css/bootstrap3-panel.css" />--%>
    <%--<link rel="stylesheet" type="text/css" href="resources/css/main.css" />--%>
    <link rel="stylesheet" type="text/css" href="resources/css/flatybootstrap-old.css" />
    <link rel="stylesheet" type="text/css" href="resources/css/scoreboard.css" />
    <link rel="stylesheet" type="text/css" href="resources/css/font-awesome/css/font-awesome.min.css" />
</head>
<body ng-controller="Scoreboard" class="scoreboard">
<span id="site" class="jumbotron">
    <div class="team-scoreboard text-center vertical-center">
        <row>
            <div ng-class="{'span12 team-name':true,'team-name-sm':gameboard.team2Name.length>9,'team-name-md':gameboard.team2Name.length>13,'team-name-lg':gameboard.team2Name.length>19}">
                {{gameboard.team2Name}}
            </div>
        </row>
        <row>
            <div class="span12 team-score">
                {{gameboard.team2Score}}
            </div>
        </row>
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
<script type="text/javascript" src="resources/js/scoreboard/scoreboard.js"></script>


</body>
</html>
