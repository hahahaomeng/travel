/**
 * Created by fanhao on 2017/4/11.
 */
//登陆控制器
var myContrl=angular.module('controllers',['ui.bootstrap']);
myContrl.controller("loginCtrl",function ($scope,$httpParamSerializer,$http,$state) {
    $scope.submits = function () {
        $http({
            url: "user_login.json",
            method: "post",
            data: $httpParamSerializer({username: $scope.username, password: $scope.password}),
            headers: {
                'Content-Type': 'application/x-www-form-urlencoded;charset=utf-8'
            }
        }).then(function (data) {
            if(data.data.code==200) {
                $scope.user=data.data.data;
                $state.go('homeSuccess.product',{username:$scope.user.username});
            }else if(data.data.code==300){
                $scope.user=data.data.data;
                $state.go('adminSuccess.welcome',{username:$scope.user.username});
            }else{
                $scope.errmsg=data.data.errMsg;
            }
        })
    };
    $scope.cancel=function () {
        $state.go('index');
    }
});
//注册控制器
myContrl.controller("registerCtrl",function ($scope,$httpParamSerializer,$http,$state) {
    $scope.name_err=true;
    $scope.isdisabled=true;
	$scope.register=function () {

		$http({
			url:"user_register.json",
            method:"post",
            data: $httpParamSerializer({username:$scope.username,password:$scope.password,phone:$scope.phone,email:$scope.email,checkcode:$scope.checkcode}),
            headers: {
                'Content-Type': 'application/x-www-form-urlencoded;charset=utf-8'
            }
		}).then(function (data) {
		    if(data.data.code==200) {
		        console.log(data.data.code);
                $state.go('index');
            }
        })
    }
    $scope.checkUserName=function () {
		$http({
            url:"user_checkUserName.json",
            method:"post",
            data: $httpParamSerializer({username:$scope.username}),
            headers: {
                'Content-Type': 'application/x-www-form-urlencoded;charset=utf-8'
            }
        }).then(function (data) {
            if(data.data.code==300){
                $('.mmm').popover('show')
			}
			else{
                $('.mmm').popover('hide')
			}
            console.log($scope.name_err)
        })
    }
    $scope.sendCode=function () {

        $http({
            url:"user_sendEmail.json",
            method:"post",
            data: $httpParamSerializer({email:$scope.email}),
            headers: {
                'Content-Type': 'application/x-www-form-urlencoded;charset=utf-8'
            }
        }).then(function (data) {
            console.log(data);
        })
    }
    //驗證激活碼
    $scope.check=function () {
        $scope.isdisabled=true;
        if($scope.checkcode!=null) {
            $http({
                url: "user_checkCode.json",
                method: "post",
                data: $httpParamSerializer({checkcode: $scope.checkcode}),
                headers: {
                    'Content-Type': 'application/x-www-form-urlencoded;charset=utf-8'
                }
            }).then(function (data) {
                if (data.data.code == 300) {
                    $('.checkcode_err').popover('show')
                    $scope.name_err = true;
                }
                else {
                    $('.checkcode_err').popover('hide')
                    $scope.name_err = false;
                    if($scope.name_err==false&&$scope.username!=null&&$scope.password!=null&&$scope.ensurepwd!=null&&$scope.phone!=null&&$scope.email!=null&&$scope.checkcode!=null){
                        $scope.isdisabled=false;
                    }
                }
            })
        }
    }
    $scope.ensurePws=function () {

		if($scope.password!=$scope.ensurepwd){
			$(".password_err").popover('show')
		}
		else{
            $(".password_err").popover('hide')
		}
    }
    $scope.checkphone=function () {

		if(!(/^1[34578]\d{9}$/.test($scope.phone))){
            $(".phone_err").popover('show')
        }else{
            $(".phone_err").popover('hide')
		}
    }
    $scope.checkemail=function () {

        if(!(/^[A-Za-z0-9\u4e00-\u9fa5]+@[a-zA-Z0-9_-]+(\.[a-zA-Z0-9_-]+)+$/.test($scope.email))){
            $(".email_err").popover('show')
        }else{
            $(".email_err").popover('hide')
        }
    }
    $scope.cancel=function () {
        $state.go('index');
    }
})
//登陆成功控制器
myContrl.controller("logSucCtrl",function ($scope,$httpParamSerializer,$http,$state,$stateParams,$location) {
   $scope.username=$stateParams.username;
   if($scope.username) {
       $http({
           url: "user_getPersonInfo.json",
           method: "post",
           headers: {
               'Content-Type': 'application/x-www-form-urlencoded;charset=utf-8'
           }
       }).then(function (data) {
           if (data.data.code == 200) {
               $scope.user = data.data.data;
           } else {
               $scope.user = null;
           }
       })
   }else{

   }
   $scope.getAllOrder=function () {
       $state.go('homeSuccess.order');
   }
   $scope.getnopayOrder=function () {
       $state.go('homeSuccess.nopayorder')
   }
   $scope.getpayOrder=function () {
       $state.go('homeSuccess.payedorder')
   }
    $scope.getfinishOrder=function () {
        $state.go('homeSuccess.finishorder');
    }
    $scope.goindex=function () {
        $state.go('homeSuccess.product',{username:$scope.username});
    }
    $scope.getRebackApp=function () {
       $state.go('homeSuccess.appreback');
    }
    $scope.checkComment=function () {
        $state.go('homeSuccess.comment');
    }
    $scope.checkUserInfo=function () {
        $http({
            url: "user_modifyUserInfo.json",
            method: "post",
            data: $httpParamSerializer({username: $scope.user.username, phone: $scope.user.phone,email:$scope.user.email}),
            headers: {
                'Content-Type': 'application/x-www-form-urlencoded;charset=utf-8'
            }
        }).then(function (data) {

        })
    }
    $scope.userlogout=function () {
        $state.go('index');
    }

})
//获取产品列表控制器
myContrl.controller("getProCtrl",function ($scope,$http,$state,$stateParams,$httpParamSerializer) {
    $scope.productdetail = function (productid) {
        $state.go('homeSuccess.productdetail', {productid: productid});
    }
    $http({
        url: "product_findAllPro.json",
        method: "post",
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded;charset=utf-8'
        }
    }).then(function (data) {
        if (data.data.code == 200) {
            $scope.product = data.data.data;
            $scope.slides = $scope.product;
        } else {
            $scope.product = null;
        }
    })
    $scope.myInterval = 5000;
    $scope.noWrapSlides = false;
    $scope.active = 0;
    $scope.currIndex = 0;
    $scope.getsomeproduct=function (place) {
        $http({
            url: "product_findSomePro.json",
            method: "post",
            data: $httpParamSerializer({proplace:place}),
            headers: {
                'Content-Type': 'application/x-www-form-urlencoded;charset=utf-8'
            }
        }).then(function (data) {
            if (data.data.code == 200) {
                $scope.product = data.data.data;
            } else {
                $scope.product = null;
            }
        })
    }
})
//产品详情页面控制器
myContrl.controller("prodetailCtrl",function ($scope,$state,$stateParams,$httpParamSerializer,$http,$window,$timeout) {
    $scope.productid=$stateParams.productid;
    $scope.username=$stateParams.username;
    $scope.applyorder=function () {
        if($scope.username) {
            $http({
                url: "order_applyOrder.json",
                method: "post",
                data: $httpParamSerializer({
                    productid: $scope.productid,
                    price: $scope.product.price,
                    gonumber: $scope.gonumber,
                    godate: $scope.godate
                }),
                headers: {
                    'Content-Type': 'application/x-www-form-urlencoded;charset=utf-8'
                }
            }).then(function (data) {
                if (data.data.code == 200) {
                    $scope.order = data.data.data;
                    // $state.go('homeSuccess.payorder', {orderid: $scope.order.orderid});
                } else {
                    $scope.order = null;
                }
            })
        }else{
            $window.alert("请登录后开始预定!");
        }
    }
    $http({
        url: "product_findProById.json",
        method: "post",
        data: $httpParamSerializer({productid:$scope.productid}),
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded;charset=utf-8'
        }
    }).then(function (data) {
        if(data.data.code==200) {
            $scope.product=data.data.data;
        }else{
            $scope.product=null;
        }
    })

    $scope.getComment = function() {
        $http({
            url: "comment_getProductComment.json",
            method: "post",
            data: $httpParamSerializer({productid:$scope.productid}),
            headers: {
                'Content-Type': 'application/x-www-form-urlencoded;charset=utf-8'
            }
        }).then(function (data) {
            if(data.data.code==200) {
                $scope.comment=data.data.data;
            }else{
                $scope.comment=null;
            }
        })
    }
    $scope.payMoney=function (orderid) {
        $('#yuding').modal('hide');
        var yuding = $timeout(function () {
            $http({
                url: "order_payOrder.json",
                method: "post",
                data: $httpParamSerializer({orderid: orderid}),
                headers: {
                    'Content-Type': 'application/x-www-form-urlencoded;charset=utf-8'
                }
            }).then(function (data) {
                if (data.data.code == 200) {
                    $state.go('homeSuccess.product');
                } else {
                    console.log("error");
                }
            })
        }, 500)
    }
    $scope.cacelpay=function () {
        var cacel = $timeout(function () {
            $state.go('homeSuccess.product');
        }, 500)
    }
})
//查询所有订单页面控制器
myContrl.controller("orderCtrl",function ($scope,$state,$stateParams,$httpParamSerializer,$http) {
    $http({
        url: "order_findAllOrder.json",
        method: "post",
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded;charset=utf-8'
        }
    }).then(function (data) {
        if(data.data.code==200) {
            $scope.order=data.data.data;

        }else{
            $scope.order=null;
        }
    })
})
//查询未付款页面
myContrl.controller("nopayorderCtrl",function ($scope,$state,$stateParams,$httpParamSerializer,$http) {
    $http({
        url: "order_findnoPayOrder.json",
        method: "post",
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded;charset=utf-8'
        }
    }).then(function (data) {
        if(data.data.code==200) {
            $scope.order=data.data.data;

        }else{
            $scope.order=null;
        }
    })
    $scope.pay=function (orderid) {
        $http({
            url: "order_payOrder.json",
            method: "post",
            data: $httpParamSerializer({orderid:orderid}),
            headers: {
                'Content-Type': 'application/x-www-form-urlencoded;charset=utf-8'
            }
        }).then(function (data) {
            if(data.data.code==200) {
                $state.reload('homeSuccess.nopayorder');
            }else{
               console.log("error");
            }
        })
    }
    $scope.deleteOrder=function (orderid) {
        $http({
            url: "order_deleteOrder.json",
            method: "post",
            data: $httpParamSerializer({orderid:orderid}),
            headers: {
                'Content-Type': 'application/x-www-form-urlencoded;charset=utf-8'
            }
        }).then(function (data) {
            if(data.data.code==200) {
                $state.reload('homeSuccess.nopayorder');
            }else{
                console.log("error");
            }
        })
    }
})
//查询已付款页面
myContrl.controller("payedorderCtrl",function ($scope,$state,$stateParams,$httpParamSerializer,$http,$timeout) {
    $http({
        url: "order_findPayOrder.json",
        method: "post",
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded;charset=utf-8'
        }
    }).then(function (data) {
        if(data.data.code==200) {
            $scope.order=data.data.data;

        }else{
            $scope.order=null;
        }
    })
    $scope.endOrder=function (orderid) {
        $http({
            url: "order_endOrder.json",
            method: "post",
            data: $httpParamSerializer({orderid:orderid}),
            headers: {
                'Content-Type': 'application/x-www-form-urlencoded;charset=utf-8'
            }
        }).then(function (data) {
            if(data.data.code==200) {
                $state.reload('homeSuccess.payedorder');
            }else{
                console.log("error");
            }
        })
    }
    $scope.rebackparam=function(orderid){
        $scope.orderid=orderid;
    }
    $scope.reback=function () {
        $('#apply').modal('hide');
        var tuiding=$timeout(function () {
        $http({
            url: "order_reback.json",
            method: "post",
            data: $httpParamSerializer({orderid:$scope.orderid,appnotice:$scope.reason}),
            headers: {
                'Content-Type': 'application/x-www-form-urlencoded;charset=utf-8'
            }
        }).then(function (data) {
            if(data.data.code==200) {
                $state.reload('homeSuccess.payedorder');
            }else{
                console.log("error");
            }
        }) },500)
    }
})
//查询已完成页面
myContrl.controller("finishorderCtrl",function ($scope,$state,$stateParams,$httpParamSerializer,$http,$timeout) {
    $scope.rate = 0;
    $scope.max = 5;
    $scope.isReadonly = false;

    $scope.hoveringOver = function(value) {
        $scope.overStar = value;
        $scope.percent = 100 * (value / $scope.max);
    };

    $scope.ratingStates = [
        {stateOn: 'glyphicon-ok-sign', stateOff: 'glyphicon-ok-circle'},
        {stateOn: 'glyphicon-star', stateOff: 'glyphicon-star-empty'},
        {stateOn: 'glyphicon-heart', stateOff: 'glyphicon-ban-circle'},
        {stateOn: 'glyphicon-heart'},
        {stateOff: 'glyphicon-off'}
    ];
    $http({
        url: "order_finishOrder.json",
        method: "post",
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded;charset=utf-8'
        }
    }).then(function (data) {
        if(data.data.code==200) {
            $scope.order=data.data.data;
        }else{
            $scope.order=null;
        }
    })
    $scope.comment=function(orderid){
        $scope.orderid=orderid;
    }
    $scope.commentOrder=function () {
        $('#comment').modal('hide');
        var pinglun=$timeout(function () {
            var file=angular.element("#exampleInputFile")[0].files[0];
            var data=new FormData();
            data.append("commenturl",file);
            data.append("orderid",$scope.orderid);
            data.append("content",$scope.content);
            data.append("commentstate",$scope.rate);
            //data.append("commentdate",new Date());

            $http({
                url: "comment_addComment.json",
                method: "post",
                //data: $httpParamSerializer({orderid:$scope.orderid}),
                data:data,
                headers: {
                     // 'Content-Type': 'application/x-www-form-urlencoded;charset=utf-8'
                    'Content-Type':undefined
                },
                transformRequest:angular.identity
            }).then(function (data) {
                if(data.data.code==200) {
                    $state.reload('homeSuccess.finishorder');
                }else{
                    console.log("error");
                }
            }) },500)
    }
})
//忘记密码页面控制器
myContrl.controller("forgetpsdCtrl",function ($scope,$state,$stateParams,$httpParamSerializer,$http,$timeout) {
    $scope.modPasswordEmail=function () {
        $http({
            url:"user_modPasswordEmail.json",
            method:"post",
            data: $httpParamSerializer({username:$scope.username}),
            headers: {
                'Content-Type': 'application/x-www-form-urlencoded;charset=utf-8'
            }
        }).then(function (data) {
            console.log(data.data.code);
        })
    }
    //驗證激活碼
    $scope.check=function () {
        $http({
            url:"user_checkCode.json",
            method:"post",
            data: $httpParamSerializer({checkcode:$scope.checkcode}),
            headers: {
                'Content-Type': 'application/x-www-form-urlencoded;charset=utf-8'
            }
        }).then(function (data) {
            if(data.data.code==300){
                $('.checkcode_err').popover('show')
            }
            else{
                $('.checkcode_err').popover('hide')
            }
            console.log($scope.name_err)
        })
    }
    $scope.ensurePws=function () {

        if($scope.password!=$scope.ensurepwd){
            $(".password_err").popover('show')
        }
        else{
            $(".password_err").popover('hide')
        }
    }
    $scope.checkemail=function () {
        if(!(/^[A-Za-z0-9\u4e00-\u9fa5]+@[a-zA-Z0-9_-]+(\.[a-zA-Z0-9_-]+)+$/.test($scope.email))){
            $(".email_err").popover('show')
        }else{
            $(".email_err").popover('hide')
        }
    }
    $scope.modPassword=function () {
        $http({
            url: "user_modPassword.json",
            method: "post",
            data: $httpParamSerializer({password: $scope.password}),
            headers: {
                'Content-Type': 'application/x-www-form-urlencoded;charset=utf-8'
            }
        }).then(function (data) {
            if (data.data.code == 200) {
                $('#myModal').modal('show');
                console.log("修改成功");
            } else {
                console.log("修改失败");
            }
        })
    }
    $scope.homePage=function () {
       $('#myModal').modal('hide');
       var setTimeout=$timeout(function () {
           $state.go('index')
       },500);
    }
})
//付款页面控制器
// myContrl.controller("payorderCtrl",function ($scope,$state,$stateParams,$httpParamSerializer,$http) {
//     $scope.orderid = $stateParams.orderid;
//     $http({
//         url: "order_findOrderById.json",
//         method: "post",
//         data: $httpParamSerializer({orderid: $scope.orderid}),
//         headers: {
//             'Content-Type': 'application/x-www-form-urlencoded;charset=utf-8'
//         }
//     }).then(function (data) {
//         if (data.data.code == 200) {
//             $scope.order = data.data.data;
//         } else {
//             $scope.order = null;
//         }
//     })
//     $scope.payMoney=function (orderid) {
//         $http({
//             url: "order_payOrder.json",
//             method: "post",
//             data: $httpParamSerializer({orderid:orderid}),
//             headers: {
//                 'Content-Type': 'application/x-www-form-urlencoded;charset=utf-8'
//             }
//         }).then(function (data) {
//             if(data.data.code==200) {
//                 $state.go('homeSuccess.product');
//             }else{
//                 console.log("error");
//             }
//         })
//     }
// })
//申请页面控制器
myContrl.controller("apprebackCtrl",function ($scope,$state,$stateParams,$httpParamSerializer,$http){
    $http({
        url: "application_getRebackApp.json",
        method: "post",
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded;charset=utf-8'
        }
    }).then(function (data) {
        if(data.data.code==200) {
            $scope.app=data.data.data;
        }else{
            $scope.app=null;
        }
    })
})
//评论页面控制器
myContrl.controller("commentCtrl",function ($scope,$state,$stateParams,$httpParamSerializer,$http){
    $http({
        url: "comment_checkComment.json",
        method: "post",
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded;charset=utf-8'
        }
    }).then(function (data) {
        if(data.data.code==200) {
            $scope.comment=data.data.data;
        }else{
            $scope.comment=null;
        }
    })
})

//管理员登录成功控制器
myContrl.controller("adminlogSucCtrl",function ($scope,$state,$stateParams,$httpParamSerializer,$http){
    $scope.username=$stateParams.username;
    $scope.adminlogout=function () {
        $state.go('index');
    }
})
//管理员添加商品控制器
myContrl.controller("addproductCtrl",function ($scope,$state,$stateParams,$httpParamSerializer,$http){
    $scope.canceladd=function () {
        $state.go('adminSuccess.welcome');
    }
    $scope.addpro=function () {
        var file = angular.element("#productimage")[0].files[0];
        var data = new FormData();
        data.append("imageurl", file);
        data.append("productname", $scope.productname);
        data.append("prodescribe", $scope.prodescribe);
        data.append("price", $scope.price);
        data.append("proplace", $scope.proplace);
        data.append("goplace", $scope.goplace);
        data.append("prodetail", $scope.prodetail);
        data.append("hoteldetail", $scope.hoteldetail);

        $http({
            url: "product_addProduct.json",
            method: "post",
            //data: $httpParamSerializer({orderid:$scope.orderid}),
            data: data,
            headers: {
                // 'Content-Type': 'application/x-www-form-urlencoded;charset=utf-8'
                'Content-Type': undefined
            },
            transformRequest: angular.identity
        }).then(function (data) {
            if (data.data.code == 200) {
                $state.go('adminSuccess.welcome');
            } else {
                console.log("error");
            }
        })
    }
})
//管理员查看订单控制器
myContrl.controller("findorderCtrl",function ($scope,$state,$stateParams,$httpParamSerializer,$http){
    var myChart = echarts.init(document.getElementById('chartorder'));
    $http({
        url: "order_findOrderByTime.json",
        method: "post",
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded;charset=utf-8'
        }
    }).then(function (data) {
        if(data.data.code==200) {
            $scope.adminorder=data.data.data;
            myChart.setOption({
                title: {
                    text: '该时段内的订单数柱状图'
                },
                tooltip: {},
                legend: {
                    data:['订单数']
                },
                xAxis: {
                    data: data.data.name
                },
                yAxis: {},
                series: [{
                    name: '订单数',
                    type: 'bar',
                    data:data.data.value
                }]
            });
        }else{
            $scope.adminorder=null;
        }
    })
    $scope.findorderbytime=function () {
        $http({
            url: "order_findOrderByTime.json",
            method: "post",
            data: $httpParamSerializer({kaishitime:$scope.begintime,jieshutime:$scope.endtime}),
            headers: {
                 'Content-Type': 'application/x-www-form-urlencoded;charset=utf-8'
            }
        }).then(function (data) {
            if (data.data.code == 200) {
               $scope.adminorder=data.data.data;

                myChart.setOption({
                    title: {
                        text: '该时段内的订单数柱状图'
                    },
                    tooltip: {},
                    legend: {
                        data:['订单数']
                    },
                    xAxis: {
                        data: data.data.name
                    },
                    yAxis: {},
                    series: [{
                        name: '订单数',
                        type: 'bar',
                        data:data.data.value
                    }]
                });
            } else {
                console.log("error");
            }
        })
    }
})
//管理员修改景点控制器
myContrl.controller("fixproductCtrl",function ($scope,$state,$stateParams,$httpParamSerializer,$http){
    $http({
        url: "product_findAllPro.json",
        method: "post",
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded;charset=utf-8'
        }
    }).then(function (data) {
        if (data.data.code == 200) {
            $scope.product = data.data.data;
        } else {
            $scope.product = null;
        }
    })
    $scope.deleteProdcut=function(productid){
        $http({
            url: "product_delProduct.json",
            method: "post",
            data: $httpParamSerializer({productid:productid}),
            headers: {
                'Content-Type': 'application/x-www-form-urlencoded;charset=utf-8'
            }
        }).then(function (data) {
            if (data.data.code == 200) {
                $state.reload();
            } else {

            }
        })
    }
    $scope.gofixpage=function(product) {
        $state.go('adminSuccess.fixprodetail',{productid:product})
    }
})
//管理员查看申请控制器
myContrl.controller("findApplyCtrl",function ($scope,$state,$stateParams,$httpParamSerializer,$http){
    $http({
        url: "application_adminGetApp.json",
        method: "post",
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded;charset=utf-8'
        }
    }).then(function (data) {
        if (data.data.code == 200) {
            $scope.application = data.data.data;
        } else {
            $scope.application = null;
        }
    })
    $scope.pass=function(appid){
        $http({
            url:"application_passApp.json",
            method:"post",
            data: $httpParamSerializer({appid:appid}),
            headers: {
                'Content-Type': 'application/x-www-form-urlencoded;charset=utf-8'
            }
        }).then(function (data) {
            console.log(data.data.code);
            $state.reload();
        })
    }
    $scope.reject=function(appid){
        $http({
            url:"application_rejectApp.json",
            method:"post",
            data: $httpParamSerializer({appid:appid}),
            headers: {
                'Content-Type': 'application/x-www-form-urlencoded;charset=utf-8'
            }
        }).then(function (data) {
            console.log(data.data.code);
            $state.reload();
        })
    }
})
//管理员查看通过申请
myContrl.controller("findpassApplyCtrl",function ($scope,$state,$stateParams,$httpParamSerializer,$http) {
    $http({
        url: "application_adminGetPassapp.json",
        method: "post",
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded;charset=utf-8'
        }
    }).then(function (data) {
        if (data.data.code == 200) {
            $scope.application = data.data.data;
        } else {
            $scope.application = null;
        }
    })
})
//管理员查看未通过申请
myContrl.controller("findnopassCtrl",function ($scope,$state,$stateParams,$httpParamSerializer,$http) {
    $http({
        url: "application_adminGetNopassapp.json",
        method: "post",
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded;charset=utf-8'
        }
    }).then(function (data) {
        if (data.data.code == 200) {
            $scope.application = data.data.data;
        } else {
            $scope.application = null;
        }
    })
})
//管理员查看普通正常用户控制器
myContrl.controller("findnormalUserCtrl",function ($scope,$state,$stateParams,$httpParamSerializer,$http) {
    $http({
        url: "user_findnormalUser.json",
        method: "post",
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded;charset=utf-8'
        }
    }).then(function (data) {
        if (data.data.code == 200) {
            $scope.user = data.data.data;
        } else {
            $scope.user = null;
        }
    })
    $scope.freezer=function (userid) {
        $http({
            url: "user_freezerUser.json",
            method: "post",
            data: $httpParamSerializer({userid:userid}),
            headers: {
                'Content-Type': 'application/x-www-form-urlencoded;charset=utf-8'
            }
        }).then(function (data) {
            if(data.data.code==200) {
                $state.reload();
            }
        })
    }
})

//管理员查看被冻结用户控制器
myContrl.controller("findUnnormalUserCtrl",function ($scope,$state,$stateParams,$httpParamSerializer,$http) {
    $http({
        url: "user_findUnnormalUser.json",
        method: "post",
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded;charset=utf-8'
        }
    }).then(function (data) {
        if (data.data.code == 200) {
            $scope.user = data.data.data;
        } else {
            $scope.user = null;
        }
    })
    $scope.nofreezer=function (userid) {
        $http({
            url: "user_nofreezerUser.json",
            method: "post",
            data: $httpParamSerializer({userid:userid}),
            headers: {
                'Content-Type': 'application/x-www-form-urlencoded;charset=utf-8'
            }
        }).then(function (data) {
            if(data.data.code==200) {
                $state.reload();
            }
        })
    }
})
//管理员修改景点页面控制器
myContrl.controller("fixprodetailCtrl",function ($scope,$state,$stateParams,$httpParamSerializer,$http) {
    $scope.productid=$stateParams.productid;
    $http({
        url: "product_findProById.json",
        method: "post",
        data: $httpParamSerializer({productid:$scope.productid}),
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded;charset=utf-8'
        }
    }).then(function (data) {
        if(data.data.code==200) {
            $scope.product=data.data.data;
        }else{
            $scope.product=null;
        }
    })
    $scope.cancefix=function () {
        $state.go("adminSuccess.fixproduct");
    }
    $scope.fixpro=function () {
        $http({
            url: "product_modProduct.json",
            method: "post",
            data: $httpParamSerializer({productid:$scope.product.productid,prodescribe:$scope.product.prodescribe,price:$scope.product.price,productname:$scope.product.productname,proplace:$scope.product.proplace,goplace:$scope.product.goplace,prodetail:$scope.product.prodetail,hoteldetail:$scope.product.hoteldetail}),
            headers: {
                'Content-Type': 'application/x-www-form-urlencoded;charset=utf-8'
            }
        }).then(function (data) {
            if(data.data.code==200) {
                $state.go("adminSuccess.fixproduct");
            }else{

            }
        })
    }
})
//管理员首页控制器
myContrl.controller("AdminCtrl",function ($scope,$state,$stateParams,$httpParamSerializer,$http) {
    $http({
        url: "order_findSumByPro.json",
        method: "post",
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded;charset=utf-8'
        }
    }).then(function (data) {
        if(data.data.code==200) {
            console.log(data.data.data);
            // 基于准备好的dom，初始化echarts实例
             var myChart = echarts.init(document.getElementById('main'));

            // 指定图表的配置项和数据
            var option = {
                series: [
                    {
                        name: '访问来源',
                        type: 'pie',
                        radius: '55%',
                        data:data.data.data
                    }
                ]
            };
            myChart.setOption(option);
        }
    })
})
