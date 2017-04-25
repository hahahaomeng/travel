/**
 * Created by fanhao on 2017/4/11.
 */
//登陆控制器
var myContrl=angular.module('controllers',[]);
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
            }else{
                $state.go('index');
            }
        })
    };
    $scope.cancel=function () {
        $state.go('index');
    }
});
//注册控制器
myContrl.controller("registerCtrl",function ($scope,$httpParamSerializer,$http,$location) {
    $scope.name_err=false;
	$scope.register=function () {

		$http({
			url:"user_register.json",
            method:"post",
            data: $httpParamSerializer({username:$scope.username,password:$scope.password,phone:$scope.phone,email:$scope.email,checkcode:$scope.checkcode}),
            headers: {
                'Content-Type': 'application/x-www-form-urlencoded;charset=utf-8'
            }
		}).then(function (data) {
			console.log(data);
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
        $location.path("/")
    }
})
//登陆成功控制器
myContrl.controller("logSucCtrl",function ($scope,$httpParamSerializer,$http,$state,$stateParams,$location) {
   $scope.username=$stateParams.username;
   $scope.getAllOrder=function () {
       $state.go('homeSuccess.order');
   }
   $scope.getnopayOrder=function () {
       $state.go('homeSuccess.nopayorder')
   }
   $scope.getpayOrder=function () {
       $state.go('homeSuccess.payedorder')
   }
    $scope.goPersonInfo=function () {
       $state.go('homeSuccess.personinfo');
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
})
//获取产品列表控制器
myContrl.controller("getProCtrl",function ($scope,$http,$state,$stateParams) {
    $scope.productdetail=function(productid) {
        $state.go('homeSuccess.productdetail',{productid:productid});
    }
    $http({
            url: "product_findAllPro.json",
            method: "post",
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

})
//产品详情页面控制器
myContrl.controller("prodetailCtrl",function ($scope,$state,$stateParams,$httpParamSerializer,$http) {
    $scope.productid=$stateParams.productid;
    $scope.applyorder=function () {
        $http({
            url: "order_applyOrder.json",
            method: "post",
            data: $httpParamSerializer({productid:$scope.productid,price:$scope.product.price,gonumber:$scope.gonumber,godate:$scope.godate}),
            headers: {
                'Content-Type': 'application/x-www-form-urlencoded;charset=utf-8'
            }
        }).then(function (data) {
            if(data.data.code==200) {
                $scope.order=data.data.data;
                $state.go('homeSuccess.payorder',{orderid:$scope.order.orderid});
            }else{
                $scope.order=null;
            }
        })
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
myContrl.controller("finishorderCtrl",function ($scope,$state,$stateParams,$httpParamSerializer,$http) {
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
myContrl.controller("payorderCtrl",function ($scope,$state,$stateParams,$httpParamSerializer,$http) {
    $scope.orderid = $stateParams.orderid;
    $http({
        url: "order_findOrderById.json",
        method: "post",
        data: $httpParamSerializer({orderid: $scope.orderid}),
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded;charset=utf-8'
        }
    }).then(function (data) {
        if (data.data.code == 200) {
            $scope.order = data.data.data;
        } else {
            $scope.order = null;
        }
    })
    $scope.payMoney=function (orderid) {
        $http({
            url: "order_payOrder.json",
            method: "post",
            data: $httpParamSerializer({orderid:orderid}),
            headers: {
                'Content-Type': 'application/x-www-form-urlencoded;charset=utf-8'
            }
        }).then(function (data) {
            if(data.data.code==200) {
                $state.go('homeSuccess');
            }else{
                console.log("error");
            }
        })
    }
})
//个人信息页面控制器
myContrl.controller("personinfoCtrl",function ($scope,$state,$stateParams,$httpParamSerializer,$http) {
    $http({
        url: "user_getPersonInfo.json",
        method: "post",
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded;charset=utf-8'
        }
    }).then(function (data) {
        if (data.data.code == 200) {
            $scope.user = data.data.data;
            console.log($scope.user);
        } else {
            $scope.user = null;
        }
    })
})
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
            $scope.product=data.data.data;
        }else{
            $scope.product=null;
        }
    })
})
