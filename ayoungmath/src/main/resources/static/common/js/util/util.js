	//비동기 지연
function promiseLate(){
	return new Promise((r) => setTimeout(r, ms));	
}
	//빈 값 체크
function isEmpty(_value){
	_value = (typeof _value === "string")? _value.trim() : _value;
	if(_value == null || _value == "" || _value.length == 0 || _value == undefined) return true;
	else return false;
}
	//이메일 체크
function isEmail(_value){
	let pattern =  /^([\w-]+(?:\.[\w-]+)*)@((?:[\w-]+\.)*\w[\w-]{0,66})\.([a-z]{2,6}(?:\.[a-z]{2})?)$/i;	
	if(pattern.test(_value)) return true;
	else return false;	
}
	//자리수 체크
function isLength(_value, _length){
	if(_value == null || _value == "" || _value == undefined){
		return false;
	} else{
		if(_value.length == _length){
			return true;
		} else{
			return false;
		}
	}
}
//회원가입 아이디 체크
function isRuleId(_value){
	let pattern = /^[a-zA-z0-9`~!@#$%^&*()\-_=+\.]{4,15}$/;
	if(pattern.test(_value)) return true;
	else return false;
}
	//비밀번호 체크 1
function isRulePwd1(_value){
	let pattern = /^[a-zA-Z0-9!@#$%^&*+=-]{4,20}$/;
	if(pattern.test(_value)) return true;
	else return false;
}
	//비밀번호 체크 2
function isRulePwd2(_value){
	let pattern = /^[a-zA-z0-9]{4,20}$/;
	if(pattern.test(_value)) return true;
	else return false;
}

function isRulePwd3(_value){
	let pattern1 = /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)[a-zA-Z\d]{8,16}$/; //영어 소문자 + 영어 대문자 + 숫자
	let pattern2 = /^(?=.*[a-z])(?=.*[A-Z])(?=.*[`~!@#$%^&*()\-_=+])[a-zA-Z`~!@#$%^&*()\-_=+]{8,16}$/; //영어 소문자 + 영어 대문자 + 특수문자
	let pattern3 =/^(?=.*[a-z])(?=.*\d)(?=.*[`~!@#$%^&*()\-_=+])[a-z\d`~!@#$%^&*()\-_=+]{8,16}$/;//영어 소문자 + 숫자 + 특수문자
	let pattern4 =/^(?=.*[A-Z])(?=.*\d)(?=.*[`~!@#$%^&*()\-_=+])[A-Za-z\d`~!@#$%^&*()\-_=+]{8,16}$/;//영어 대문자 + 숫자 + 특수문자
	if(pattern1.test(_value))  {console.log("11");return true;}
	else if(pattern2.test(_value)) {console.log("22");return true;}
	else if(pattern3.test(_value))  {console.log("33");return true;}
	else if(pattern4.test(_value)) {console.log("44");return true;}
	else return false;
}
	//특수문자 체크
function isSpecialChar(_value){
	let pattern = /[`~!@#$%^&*|\\\'\";:\/?]/gi;
	if(pattern.test(_value)) return true;
	else return false;
}
	//숫자인지 체크
function isNumber(_value){
	if (typeof _value != "string") return false
	return !isNaN(_value) && !isNaN(parseFloat(_value))
}
	//전화번호 체크
function isTel(_value){
	let pattern = /^[0-9\-]{8,14}$/;
	if(pattern.text(_value)) return true;
	else return false;
}
	//핸드폰 번호인지 체크
function isMobile(_value){
	let pattern = /^(01\d{1}|02|0505|0502|0506)(\d{3,4})(\d{4})$/;
	if(pattern.test(_value)) return true;
	else return false;
}
//사업자번호 체크
function isBizNo(_value){
	if ((_value = (_value+'').match(/\d{1}/g)).length != 10) { return false; }
	let sum = 0, key = [1, 3, 7, 1, 3, 7, 1, 3, 5];
	for (let i = 0 ; i < 9 ; i++) { sum += (key[i] * Number(_value[i])); }
	let chkSum = 0;
	chkSum = Math.floor(key[8] * Number(_value[8]) / 10);
	sum +=chkSum;
	let reminder = (10 - (sum % 10)) % 10;
	if(reminder==Number(bisNo[9])) return true;
	return false;
}
//url 체크
function isUrl(_value){
	let pattern = /(http|https):\/\/(\w+:{0,1}\w*@)?(\S+)(:[0-9]+)?(\/|\/([\w#!:.?+=&%@!\-\/]))?/;
	if(!pattern.test(_value)){
		return false;
	}
	return true;
}
	//날짜형식 체크 YY-mm-DD
function isDate(_value){
	let pattern = /[0-9]{4}-(0?[1-9]|1[012])-(0?[1-9]|[12][0-9]|3[01])/;
	if(!pattern.test(_value)){
		return false;
	}
	return true;
}
	
	//한글 받침여부 체크
function isEndWithConsonant(_value){
	var strGa = 44032; // 가
	var strHih = 55203; // 힣
	 
	var lastStrCode = _value.charCodeAt(_value.length-1);
	 
	if(lastStrCode < strGa || lastStrCode > strHih) {
		return false; //한글이 아닐 경우 false 반환
	}
	return (( lastStrCode - strGa ) % 28 != 0)
}

function checkPlatform(ua){
	if (ua === undefined) {
			ua = window.navigator.userAgent;
		}

		ua = ua.toLowerCase();
		var platform = {};
		var matched = {};
		var userPlatform = "pc";
		var platform_match = /(ipad)/.exec(ua) || /(ipod)/.exec(ua)
			|| /(windows phone)/.exec(ua) || /(iphone)/.exec(ua)
			|| /(kindle)/.exec(ua) || /(silk)/.exec(ua) || /(android)/.exec(ua)
			|| /(win)/.exec(ua) || /(mac)/.exec(ua) || /(linux)/.exec(ua)
			|| /(cros)/.exec(ua) || /(playbook)/.exec(ua)
			|| /(bb)/.exec(ua) || /(blackberry)/.exec(ua)
			|| [];

		matched.platform = platform_match[0] || "";

		if (matched.platform) {
			platform[matched.platform] = true;
		}

		if (platform.android || platform.bb || platform.blackberry
			|| platform.ipad || platform.iphone
			|| platform.ipod || platform.kindle
			|| platform.playbook || platform.silk
			|| platform["windows phone"]) {
			userPlatform = "mobile";
		}

		if (platform.cros || platform.mac || platform.linux || platform.win) {
			userPlatform = "pc";
		}

		return userPlatform;
}
//공백 제거
function conTrim(_value){
	if(_value != null && _value != "" && _value != undefined){
		return _value.trim();
	}
}

//xss Filter
function conXssFilter(_value){
	let tmpValue = _value.replaceAll("<", "&lt;");
	tmpValue = tmpValue.replaceAll(">", "&gt;");
	return tmpValue;
}
//xss Decoding
function conXssDecoding(_value){
	let tmpValue = _value.replaceAll("&lt;", "<");
	tmpValue = tmpValue.replaceAll("&gt;", ">");
	tmpValue = tmpValue.replace(/<\/?script("[^"]*"|'[^']*'|[^>])*(>|$)/ig, "");
	return tmpValue;
}

//태그 제거
function conDelTag(_value){
	return _value.replace(/(<([^>]+)>)/ig,"");
}

//스크립트 태그 제거
function conDelScriptTag(_value){
	return _value.replace(/<\/?script("[^"]*"|'[^']*'|[^>])*(>|$)/ig, "");
}


//콤마 제거
function conDelCommas(_value){
	return _value.replaceAll(",", "");
}

//숫자에 콤마 삽입
function conWithCommas(_value){	
	return _value.toString().replace(/[^0-9]/g, "").replace(/\B(?=(\d{3})+(?!\d))/g, ",");
}

//소수 콤마 삽입
function confloatNumberWithCommas(_value) {
	let number = _value.replace(/,/g, '');

	let regx = new RegExp(/(-?\d+)(\d{3})/);
	let bExists = number.indexOf(".", 0);//0번째부터 .을 찾는다.
	let strArr = number.split('.');
	
	while (regx.test(strArr[0])) {//문자열에 정규식 특수문자가 포함되어 있는지 체크
		//정수 부분에만 콤마 달기 
		strArr[0] = strArr[0].replace(regx, "$1,$2");//콤마추가하기
	}
	if (bExists > -1) {
	//. 소수점 문자열이 발견되지 않을 경우 -1 반환
		number = strArr[0] + "." + strArr[1];
	} else { //정수만 있을경우 //소수점 문자열 존재하면 양수 반환 
		number = strArr[0];
	}
	return number;
}

//숫자만 입력
function conNumber(_value){
	return _value.replace(/[^0-9]/g, "");
}
//숫자,-만 입력
function conTel(_value){
	return _value.replace(/[^0-9-]/g, "");
}

function conNumberNoLeftZero(_value){
	return _value.toString().replace(/(^0+)/, "").replace(/[^0-9]/g, "").replace(/\B(?=(\d{3})+(?!\d))/g, ",");	
}
//숫자와 소숫점만 입력
function conNumberPoint(_value){
	return _value.replace(/[^-\.0-9]/g,'');
}
//소수점 2자리까지 입력
function conFloat1Fix(_value){
	//return _value.replace(/(\.\d{1})\d+/g, '$1');
	
	if(_value.indexOf(".") != -1&&(_value.charAt(_value.length - 1)!= '.')){
		return _value.match(/^[^0]\d*\.{1}\d{1}/g).join("");
	}else if(_value.split(".").length-1 >1){
		return  _value.slice(0,_value.indexOf(".")+1);
	}else{
		return  _value.replace(/[^-\.0-9]/g,'');
	}
	
}

function isFloat1Fix(_value){
	return _value.match(/^[^0]\d*\.{1}\d*[^0]$/g);
}

	//전화번호 하이픈
function conHypenMobile(_value){
	let number = _value.replace(/[^0-9]/g, '');
	if(number.substring(0, 2) == '02'){
		if(number.length == 10)
			return number.substring(0, 2) + '-' + number.substring(2, 4) + '-' + number.substring(6);
		else if(number.length == 9)
			return number.substring(0, 2) + '-' + number.substring(2, 3) + '-' + number.substring(5);
	} else if(number.substring(0, 3) == '010') {
		if(number.length < 11)
			return number.substring(0, 3) + '-' + number.substring(3, 6) + '-' + number.substring(6);
		else
			return number.substring(0, 3) + '-' + number.substring(3, 7) + '-' + number.substring(7);
	} 	
}
function conHypenDate(_value){
	let number = _value.replace(/[^0-9]/g, ''); //숫자 이외는 제외
	let result; //리턴값
	let year; //연도
	let month; //월
	let day; //일
	let maxDay; //최대 일


	if(number.length > 8){ //8자리 이상일 경우 제외
		number = number.substring(0, 8);
	}
	year = number.substring(0, 4);
	month = number.substring(4, 6);
	day = number.substring(6);
	result = year;
	if(month != ''){
		result += "-"+month
	}
	if(day != ''){
		result += "-"+day;
	}
	return result;
	
}
	//사업자 번호 하이픈
function conHypenBizNo(_value){
	let number = _value.replace(/[^0-9]/g, '');
	
	if(number.length == 12){
		return number.substring(0, 3) + '-' + number.substring(3, 2) + '-' + number.substring(5);
	}
}
	//이메일 마스킹
function conMaskingEmail(_value){
	let len = _value.split('@')[0].length-3;
	return _value.replace(new RegExp('.(?=.{0,' + len + '}@)', 'g'), '*');
}
//이메일 마스킹
function conNewLineTextToTag(_value){
	let len = _value.replace('\r\n','<br/>').replace('\n','<br/>');
	return len;
}

	//이름 마스킹
function conMaskingName(_value){
	var pattern = /.$/;
	return _value.replace(pattern, "*");
}
	//핸드폰 번호 마스킹
function conMaskingMobile(_value){
	let number = _value.replace(/[^0-9]/g, '');
	if(number.length < 11){
		let maskingNumber = number.substring(0, 3) + '-' + number.substring(3, 3) + '-' + number.suberstring(6);
		return maskingNumber.replace(/-[0-9]{3}-/g, "-***-");
	} else {
		let maskingNumber = number.substring(0, 3) + '-' + number.substring(3, 4) + '-' + number.suberstring(7);
		return maskingNumber.replace(/-[0-9]{4}-/g, "-****-")
	}
}
//UUID 생성
function conUuid() {
	return 'xxxxxxxx-xxxx-4xxx-yxxx-xxxxxxxxxxxx'.replace(/[xy]/g, function(c) {
		let r = Math.random() * 16 | 0, v = c == 'x' ? r : (r & 0x3 | 0x8);
		return v.toString(16);
	});
}

function getRandomCode(num=6){
  const characters ='0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ';
  let result = '';
  const charactersLength = characters.length;
  for (let i = 0; i < num; i++) {
      result += characters.charAt(Math.floor(Math.random() * charactersLength));
  }

  return result;
}

//이미지 확장자 체크
function fileImgExtChk(_file){
	let file = _file.files;
	if(!/\.(bmp|jfif|gif|png|jpg|jpeg)$/i.test(file[0].name.toLowerCase())){
		_value.value = "";
		return false;		
	}
	return true;
}
	//파일 용량 체크
function fileSizeChk(_file, _size){
	let fileSize = 0;
	fileSize = _file.files[0].size;
	if(fileSize > _size){
		return false;
	}
	return true;
}
	//파일 용량 표기
function fileByteCal(_file){
	var bytes = parseInt(_file);
	var s = ['bytes', 'KB', 'MB', 'GB', 'TB', 'PB'];
	var e = Math.floor(Math.log(bytes)/Math.log(1024));

	if(e == "-Infinity")
		return "0 "+s[0];
	else
		return (bytes/Math.pow(1024, Math.floor(e))).toFixed(2)+" "+s[e];
}

// 기존 배열과 비교하여 사라진 값 출력
function getRemovedValue(_orig,_chan){
	let leng1 = _orig.length;
	let leng2 = _chan.length;
	for(var i=0;i<leng1;i++){
		for(var j=0;j<leng2;j++){
			if(_orig[i]==_chan[j]){
				_orig.splice(i, 1);
				i--;
				break;
			}
		}
	}		
	return _orig;
}
	//버튼 연타 방지
function limitActionButton(_isCheck, _time = 1000 * 2.5){
	if(_isCheck == true){
		_isCheck = false;
		
		setTimeout(function(){
			return _isCheck = true;
		}, _time);
		return _isCheck;
	}
}
	//날짜 변환
function getDateStr(date) {
	var year = date.getFullYear();
	var month = date.getMonth() + 1;
	var day = date.getDate();
	var strYear = String(year);
	var strMonth = String(month);
	var strDay = String(day);
	
	if (strMonth.length == 1) {
		strMonth = "0" + strMonth;
	}

	if (strDay.length == 1) {
		strDay = "0" + strDay;
	}
	var alldate = strYear +"-"+ strMonth +"-"+  strDay;
	
	return alldate;
}
	
function today(){
	var date = new Date();
	return this.getDateStr(date);
}
	
	
function getDate(num){
	var date = new Date();
	var dayOfMonth = date.getDate();
	date.setDate(dayOfMonth - num);
	return this.getDateStr(date);
}


	// 일주일 전
function lastWeek() {
	var date = new Date();
	var dayOfMonth = date.getDate();
	date.setDate(dayOfMonth - 7);
	return this.getDateStr(date);
}
	
	
	// 한달 전
function lastMonth() {
	var date = new Date();
	var monthOfYear = date.getMonth();
	date.setMonth(monthOfYear - 1);
	return this.getDateStr(date);
}
	
	// 3개월 전
function threeLastMonth() {
	var date = new Date();
	var monthOfYear = date.getMonth();
	date.setMonth(monthOfYear - 3);
	return this.getDateStr(date);
}
	
	// 6개월 전
function sixLastMonth() {
	var date = new Date();
	var monthOfYear = date.getMonth();
	date.setMonth(monthOfYear - 6);
	return this.getDateStr(date);
}
	
function lastYear(){
	var date = new Date();
	var monthOfYear = date.getFullYear();
	date.setFullYear(monthOfYear - 1);
	return this.getDateStr(date);
}
function getTimeDiff(start,end){
	var start_arr = start.split("-");
	var end_arr =end.split("-");
	
	var stDate  = new Date (start_arr[0],start_arr[1],start_arr[2]);
	var endDate = new Date (end_arr[0],end_arr[1],end_arr[2]);
	var diff = endDate.getTime() - stDate.getTime();
	
	return diff
}

function setCookie(_name, _value, exDays) {
	let exDate = new Date();
	exDate.setDate(exDate.getDate() + parseInt(exDays));
	let cookieValue = escape(_value) + ((exDays == null) ? "" : "; expires=" + exDate.toGMTString());
	document.cookie = _name + "=" + cookieValue;
}
		
function deleteCookie(_name) {
	let expireDate = new Date();
	expireDate.setDate(expireDate.getDate() - 1);
	document.cookie = _name + "= " + "; expires="+ expireDate.toGMTString();
}
		
function getCookie(_name) {
	_name = _name + '=';
	let cookieData = document.cookie;
	let start = cookieData.indexOf(_name);
	let cookieValue = '';
	if (start != -1) {
		start += _name.length;
		let end = cookieData.indexOf(';', start);
		if (end == -1)
			end = cookieData.length;
		cookieValue = cookieData.substring(start, end);
	}
	return unescape(cookieValue);
}
