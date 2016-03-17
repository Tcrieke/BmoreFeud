<!DOCTYPE HTML>
<html xmlns:ng="http://angularjs.org" id="ng-app" ng-app="app" charset="UTF-8">
<head>
  <meta charset="UTF-8">
  <title>Bmore Feud (Audience)</title>
  <link rel="stylesheet" type="text/css" href="resources/css/bootstrap-responsive.min.css" />
  <link rel="stylesheet" type="text/css" href="resources/css/flatybootstrap-old.css" />
  <link rel="stylesheet" type="text/css" href="resources/css/vote.css" />
  <link rel="stylesheet" type="text/css" href="resources/css/font-awesome/css/font-awesome.min.css" />
</head>
<body ng-controller="Vote" class="vote">
<span id="site" class="ng-cloak">
    <div class="container">
      <container>
        <div class="row top-section">
          <div class="span12">

          </div>
        </div>
        <div class="row-fluid main-section">
          <div class="span12 poll-box">
            <row class="row option-row" ng-repeat="option in poll">
              <div class="span12 poll-row">
                <div class="span1 poll-cell poll-count-cell">
                  {{option.votes}}
                </div>
                <div class="span9 poll-cell">
                  {{option.name}}
                </div>
                <div class="span2 poll-cell poll-vote-cell" ng-show="true">
                  <button ng-hide="vote==option.id" type="button" ng-click="voteFor(option.id)" class="btn btn-medium btn-primary">
                    Vote
                  </button>
                  <button ng-show="vote==option.id" type="button" ng-click="voteFor(0)" class="btn btn-medium btn-danger">
                    Selected
                  </button>
                </div>
              </div>
            </row>
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
<script type="text/javascript" src="resources/js/vote/vote.js"></script>




</body>
</html>
