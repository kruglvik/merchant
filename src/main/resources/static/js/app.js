var app = angular.module('app', ['ngMaterial', 'md.data.table','ngResource'])
.config(['$mdThemingProvider', function ($mdThemingProvider) {
    'use strict';

    $mdThemingProvider.theme('default')
      .primaryPalette('blue');
}]);