/**
 * Created by fanhao on 2017/4/11.
 */
//登陆控制器
app.controller("loginCtrl",function ($scope,$httpParamSerializer,$http,$location,$rootScope) {
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
                $location.path("/homeSuccess/"+$scope.user.username);
            }else{
                $location.path("/")
            }
        })
    };
    $scope.cancel=function () {
        $location.path("/")
    }
});
app.controller("registerCtrl",function ($scope,$httpParamSerializer,$http,$location) {
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
app.controller("logSucCtrl",function ($scope,$http,$location,$route,$routeParams,$httpParamSerializer) {
    $scope.$on("$routeChangeSuccess",function () {
        if($location.path().indexOf("/homeSuccess/")==0){
            $scope.username=$routeParams["username"];
        }
    })
})