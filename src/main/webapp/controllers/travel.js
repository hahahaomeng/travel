/**
 * Created by fanhao on 2017/4/11.
 */
angular.module("myApp",["ngRoute"])
.config(function ($routeProvider, $locationProvider) {

	//$locationProvider.hashPrefix('');
	$routeProvider.when("/login",{
	    templateUrl:"./views/login.html"
	})
})
.controller("loginCtrl",function ($scope,$httpParamSerializer,$http) {
	$scope.submits = function() {
       $http({
    	   url:"user_login.json",
    	   method:"post",
    	   data: $httpParamSerializer({username:$scope.username,password:$scope.password}),
    	   headers: {
    		    'Content-Type': 'application/x-www-form-urlencoded;charset=utf-8'
    		  }
       }).then(function(data){
    	   console.log(data);
       })
    };
})