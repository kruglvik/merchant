app.controller('ProductController', ['$mdEditDialog', '$mdDialog', '$scope', '$productResource', function ($mdEditDialog, $mdDialog, $scope, $productResource) {
      'use strict';

      var bookmark;

      $scope.limitOptions = [10, 20, 30];

      $scope.query = {
        order: 'name',
        limit: 10,
        page: 1
      };

      $scope.addProduct = function (event) {
          $mdDialog.show({
            clickOutsideToClose: true,
            controller: 'AddProductController',
            controllerAs: 'ctrl',
            focusOnOpen: false,
            targetEvent: event,
            templateUrl: 'templates/addProduct.html',
          }).then($scope.getProducts);
      };

      $scope.getProducts = function () {
          $scope.promise = $productResource.query($scope.query, success).$promise;
      };

      function success(products) {
        $scope.products = products;
      }

      $scope.$watch('query.filter', function (newValue, oldValue) {
          if(!oldValue) {
            bookmark = $scope.query.page;
          }

          if(newValue !== oldValue) {
            $scope.query.page = 1;
          }

          if(!newValue) {
            $scope.query.page = bookmark;
          }

          $scope.getProducts();
        });

      var minExpirationDate = new Date()

      $scope.daysToExpiry = function(product) {
          var timeDiff = product.expirationDate - minExpirationDate.getTime();
          return Math.ceil(timeDiff / (1000 * 3600 * 24))
      }

      $scope.showOnlyExpired = false

      $scope.expirationFilter = function (product) {
          return !$scope.showOnlyExpired || $scope.expired(product);
      };

      $scope.expired = function (product) {
          return $scope.daysToExpiry(product) <= 0
      }

      $scope.deleteAllExpired = function () {
          var productIds = $scope.products.filter($scope.expired).map(function(product) {
              return product.id
          }).join();
          console.info("Deleting expired products with id: " + productIds);
          $productResource.delete({q: productIds}, $scope.getProducts);
      }

      $scope.setDiscount = function (event, product) {
          var editDialog = {
            modelValue: product.discountRatio,
            save: function (input) {
              product.discountRatio = input.$modelValue;
              $productResource.update({ id: product.id }, product);
            },
            targetEvent: event,
            type: 'number',
            validators: {
              'min': 0,
              'max': 100
            }
          };

          var promise = $mdEditDialog.small(editDialog);
        };
}]);

app.controller('AddProductController', ['$mdDialog', '$productResource', '$scope', function ($mdDialog, $productResource, $scope) {
  'use strict';

    this.cancel = $mdDialog.cancel;

    function success(product) {
        $mdDialog.hide(product);
    }

    this.addItem = function () {
        $scope.item.form.$setSubmitted();

        if($scope.item.form.$valid) {
          $productResource.save($scope.product, success);
        }
    };

    $scope.minExpirationDate = new Date()
}]);

app.factory('$productResource', ['$resource', function ($resource) {
  'use strict';
  return $resource('/product/:id', { id: '@id' }, { create: {method: 'POST'}, update: {method: 'PUT'}});
}]);
