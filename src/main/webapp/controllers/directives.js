/**
 * Created by fanhao on 2017/4/17.
 */
var myDirectives=angular.module('directives',[]);
myDirectives.directive('product',function () {
    return{
        templateUrl:'./views/product.html'
    }
})