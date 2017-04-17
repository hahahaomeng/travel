/**
 * Created by fanhao on 2017/4/12.
 */
var app=angular.module('myApp',['ui.router','controllers','directives']);
app.run(function($rootScope, $state, $stateParams) {
    $rootScope.$state = $state;
    $rootScope.$stateParams = $stateParams;
});
   app.config(function ($stateProvider,$urlRouterProvider) {
       $urlRouterProvider.otherwise('/index');
        $stateProvider.state("index", {
            url:"/index",
            views:{
                '':{
                    templateUrl:'views/index.html'
                },
                'home@index':{
                  templateUrl:'views/home.html'
                },
                'product@index':{
                    templateUrl:'views/product.html',
                    controller:'getProCtrl'
                }
            }
        }).state("login", {
            url:'/login',
            templateUrl: "./views/login.html",
            controller:'loginCtrl'
        }).state("register", {
            url:'/register',
            templateUrl: "./views/register.html",
            controller:'registerCtrl'
        }).state("product", {
            url:'product',
            templateUrl: "./views/product.html",
            controller:'getProCtrl'
        }).state("homeSuccess",{
            url:'/homeSuccess?username',
            templateUrl:'views/homeSuccess.html',
            controller:'logSucCtrl'
        }).state("productdetail",{
            url:'/productdetail?productid',
            templateUrl:'views/productdetail.html',
            controller:'getProCtrl'
        })
    });