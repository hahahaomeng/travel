/**
 * Created by fanhao on 2017/4/12.
 */
var app=angular.module("myApp",["ngRoute"])
    .config(function ($routeProvider) {

        //$locationProvider.hashPrefix('');
        $routeProvider.when("/",{
            templateUrl:"./views/home.html"
        }).when("/login",{
            templateUrl:"./views/login.html"
        }).when("/register",{
            templateUrl:"./views/register.html"
        })
    })