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
        }).state("forgetpsd",{
            url:'/forgetpsd',
            templateUrl:'./views/forgetpsd.html',
            controller:'forgetpsdCtrl'
        }).state("product", {
            url:'product',
            templateUrl: "./views/product.html",
            controller:'getProCtrl'
        }).state("homeSuccess",{
            url:'/homeSuccess?username&productid&orderid',
            templateUrl:'views/homeSuccess.html',
            abstract:true,
            controller:'logSucCtrl'
        }).state("productdetail",{
            url:'/productdetail?productid',
            templateUrl:'views/productdetail.html',
            controller:'getProCtrl'
        }).state("homeSuccess.product",{
            url:"/homeSuccess/product",
            templateUrl:'views/product.html'
        }).state("homeSuccess.productdetail",{
            url:"/homeSuccess/productdetail",
            templateUrl:'views/productdetail.html'
        }).state("homeSuccess.order",{
            url:"/homeSuccess/order",
            templateUrl:'views/order.html'
        }).state("homeSuccess.payorder",{
            url:"/homeSuccess/payorder",
            templateUrl:'views/payorder.html'
        }).state("homeSuccess.nopayorder",{
            url:"/homeSuccess/nopayorder",
            templateUrl:'views/nopayorder.html'
        }).state("homeSuccess.payedorder",{
            url:"/homeSuccess/payedorder",
            templateUrl:'views/payedorder.html'
        }).state("homeSuccess.personinfo",{
            url:"/homeSuccess/personinfo",
            templateUrl:'views/personinfo.html'
        }).state("homeSuccess.finishorder",{
            url:"/homeSuccess/finishorder",
            templateUrl:'views/finishorder.html'
        }).state("homeSuccess.appreback",{
            url:"/homeSuccess/appreback",
            templateUrl:'views/appreback.html'
        }).state("homeSuccess.comment",{
            url:"/homeSuccess/comment",
            templateUrl:'views/comment.html'
        }).state("adminSuccess", {
            url: '/homeSuccess?username',
            templateUrl: 'adminviews/adminSuccess.html',
            abstract:true,
            controller: 'adminlogSucCtrl'
        }).state("adminSuccess.welcome", {
            url: '/adminSuccess/welcome',
            templateUrl: 'adminviews/welcome.html'
        }).state("adminSuccess.addproduct", {
            url: '/adminSuccess/addproduct',
            templateUrl: 'adminviews/addproduct.html'
        }).state("adminSuccess.fixproduct", {
            url: '/adminSuccess/fixproduct',
            templateUrl: 'adminviews/fixproduct.html'
        }).state("adminSuccess.findorder", {
            url: '/adminSuccess/findorder',
            templateUrl: 'adminviews/findorder.html'
        })
    });