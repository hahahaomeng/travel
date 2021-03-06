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
           url: "/index",
           views: {
               '': {
                   templateUrl: 'views/index.html'
               },
               'home@index': {
                   templateUrl: 'views/home.html'
               },
               'product@index': {
                   templateUrl: 'views/product.html',
                   controller: 'getProCtrl'
               }
           }
       }).state("login", {
           url: '/login',
           templateUrl: "./views/login.html",
           controller: 'loginCtrl'
       }).state("register", {
           url: '/register',
           templateUrl: "./views/register.html",
           controller: 'registerCtrl'
       }).state("forgetpsd", {
           url: '/forgetpsd',
           templateUrl: './views/forgetpsd.html',
           controller: 'forgetpsdCtrl'
       }).state("product", {
           url: 'product',
           templateUrl: "./views/product.html",
           controller: 'getProCtrl'
       }).state("homeSuccess", {
           url: '/homeSuccess?username&productid&orderid',
           templateUrl: 'views/homeSuccess.html',
           abstract: true,
           controller: 'logSucCtrl'
       }).state("productdetail", {
           url: '/productdetail?productid',
           templateUrl: 'views/productdetail.html',
           controller: 'getProCtrl'
       }).state("homeSuccess.product", {
           url: "/product",
           templateUrl: 'views/product.html'
       }).state("homeSuccess.productdetail", {
           url: "/productdetail",
           templateUrl: 'views/productdetail.html'
       }).state("homeSuccess.order", {
           url: "/order",
           templateUrl: 'views/order.html'
       }).state("homeSuccess.payorder", {
           url: "/payorder",
           templateUrl: 'views/payorder.html'
       }).state("homeSuccess.nopayorder", {
           url: "/nopayorder",
           templateUrl: 'views/nopayorder.html'
       }).state("homeSuccess.payedorder", {
           url: "/payedorder",
           templateUrl: 'views/payedorder.html'
       }).state("homeSuccess.personinfo", {
           url: "/personinfo",
           templateUrl: 'views/personinfo.html'
       }).state("homeSuccess.finishorder", {
           url: "/finishorder",
           templateUrl: 'views/finishorder.html'
       }).state("homeSuccess.appreback", {
           url: "/appreback",
           templateUrl: 'views/appreback.html'
       }).state("homeSuccess.comment", {
           url: "/comment",
           templateUrl: 'views/comment.html'
       }).state("adminSuccess", {
           url: '/homeSuccess?username',
           templateUrl: 'adminviews/adminSuccess.html',
           abstract: true,
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
       }).state("adminSuccess.fixprodetail", {
            url: '/adminSuccess/fixprodetail?productid',
            templateUrl: 'adminviews/fixprodetail.html'
       }).state("adminSuccess.findApply", {
            url: '/adminSuccess/findApply',
            templateUrl: 'adminviews/findApply.html'
       }).state("adminSuccess.nopassApply", {
            url: '/adminSuccess/nopassApply',
            templateUrl: 'adminviews/nopassApply.html'
       }).state("adminSuccess.passApply", {
            url: '/adminSuccess/passApply',
            templateUrl: 'adminviews/passApply.html'
       }).state("adminSuccess.findnormalUser", {
            url: '/adminSuccess/findnormalUser',
            templateUrl: 'adminviews/findnormalUser.html'
       }).state("adminSuccess.findUnnormalUser", {
            url: '/adminSuccess/findUnnormalUser',
            templateUrl: 'adminviews/findUnnormalUser.html'
       })
    });