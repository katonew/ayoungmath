/*
function ajaxSendLogin(_ajaxData, _timeout=10000){
	alertLoading();
	$.ajax({
		type : _ajaxData.type,
		url : _ajaxData.url,
		dataType : _ajaxData.dataType,
		data : _ajaxData.data,
		timeout: _timeout,
		processData: false,
		contentType: false,
		cache: false,
		success: function (_data){
			try{
				let json = JSON.parse(_data);					
				if(json.sleep == null || json.sleep == "" || json.sleep == undefined){
					location.replace(_ajaxData.successLink);
				} else {
					if(json.sleep == 'login'){
						location.replace(_ajaxData.sleepLoginLink);
					} else if(json.sleep == 'password'){
						location.replace(_ajaxData.sleepPasswordLink);
					} else{
						location.replace(_ajaxData.successLink);
					}
				}
			} catch(e){
				location.replace(_ajaxData.successLink);
			}			
		},
		error: function(_request, _status, _e){
			$("input[type='password']").val("");
			ajaxErrorAlert(_request, _status, _ajaxData.failLink);		
		}
	});
}
*/
function ajaxSendSuccessNoAlertNoLink(_ajaxData, _timeout=10000){
	$.ajax({
		type : _ajaxData.type,
		url : _ajaxData.url,
		dataType : _ajaxData.dataType,
		data : _ajaxData.data,
		timeout: _timeout,
		processData: false,
		contentType: false,
		cache: false,
		success: function (_data){
			console.log("ajaxSendNoAlertNoLink Success");		
		},
		error: function(_request, _status, _e){
			ajaxErrorAlert(_request, _status, _ajaxData.failLink);		
		}
	});
}	
function ajaxSendSuccessLink(_ajaxData, _timeout=10000){
	alertLoading();
	$.ajax({
		type : _ajaxData.type,
		url : _ajaxData.url,
		dataType : _ajaxData.dataType,
		data : _ajaxData.data,
		timeout : _timeout,
		processData: false,
		contentType: false,
		cache: false,
		success: function (_data){
			let json = JSON.parse(_data);
			location.href = encodeURI(json.link);
		},
		error: function(_request, _status, _e){
			ajaxErrorAlert(_request, _status, _ajaxData.failLink);	
		}
	})
}
function ajaxSendPopupDefault(_ajaxData, _timeout=10000){
	alertLoading();
	$.ajax({
		type : _ajaxData.type,
		url : _ajaxData.url,
		dataType : _ajaxData.dataType,
		data : _ajaxData.data,
		timeout : _timeout,
		processData: false,
		contentType: false,
		cache: false,
		success: function (_data){
			let json = JSON.parse(_data);
			Swal.fire({
			  title: '<strong>' + json.title +'</strong>',
			  html: '<div style="font-size:14px;">' + json.message + '<br><br></div>' +
			  		'<div style="font-weight:bold;font-size:14px;">' + json.data + '</div>',
			  showCloseButton: true,
			  showCancelButton: false,
			  focusConfirm: true,
			  allowOutsideClick: false,
			  confirmButtonColor:'#00A651',
			  cancelButtonColor:'#928172',
			  confirmButtonText:'확인'
			});
		},
		error: function(_request, _status, _e){
			ajaxErrorAlert(_request, _status, _ajaxData.failLink);	
		}
	})
}

function ajaxSendAlertLink(_ajaxData, _timeout=10000){
	alertLoading();
	$.ajax({
		type : _ajaxData.type,
		url : _ajaxData.url,
		dataType : _ajaxData.dataType,
		data : _ajaxData.data,
		timeout: _timeout,
		processData: false,
		contentType: false,
		cache: false,
		success: function (_data){
			ajaxSuceessAlert(_ajaxData.successMsg,_ajaxData.successLink);
		},
		error: function(_request, _status, _e){
			ajaxErrorAlert(_request, _status, _ajaxData.failLink);		
		}
	});
}

function ajaxSendQuestionAlertLink(_title, _msg, _ajaxData, _timeout=10000){
	Swal.fire({
		title: _title,
		html: _msg,
		icon: 'question',
		confirmButtonText: '확인',
		confirmButtonColor: '#4573f2',
		showCancelButton: true,
		cancelButtonText: '취소',
		allowOutsideClick: false
	}).then(function(result){
		if(result.value){
			alertLoading();
			$.ajax({
				type : _ajaxData.type,
				url : _ajaxData.url,
				dataType : _ajaxData.dataType,
				data : _ajaxData.data,
				timeout: _timeout,
				processData: false,
				contentType: false,
				cache: false,
				success: function (_data){
					ajaxSuceessAlert(undefined, _ajaxData.successLink);
				},
				error: function(_request, _status, _e){
					ajaxErrorAlert(_request, _status, _ajaxData.failLink);		
				}
			});
		}
	});
}

function ajaxSendAfterConfirmAlertLink(_ajaxData, _timeout=10000){
	Swal.fire({
	title: "",
	html: _ajaxData.confirmMsg,
	icon: 'success',
	confirmButtonText: '확인',
	confirmButtonColor: '#00A651',
	showCancelButton: true,
	cancelButtonText: '취소',
	allowOutsideClick: false
	})
	.then(function(result){
		if(result.value){
			alertLoading();
			$.ajax({
				type : _ajaxData.type,
				url : _ajaxData.url,
				dataType : _ajaxData.dataType,
				data : _ajaxData.data,
				timeout: _timeout,
				processData: false,
				contentType: false,
				cache: false,
				success: function (_data){
					ajaxSuceessAlert(_ajaxData.successMsg,_ajaxData.successLink);
				},
				error: function(_request, _status, _e){
					ajaxErrorAlert(_request, _status, _ajaxData.failLink);		
				}
			});
		}
	});	
}

function ajaxSendAlertLinkFile(_ajaxData, _timeout=10000){
	alertLoading();
	$.ajax({
		type : _ajaxData.type,
		url : _ajaxData.url,
		dataType : _ajaxData.dataType,
		data : _ajaxData.data,
		timeout: _timeout,
		enctype: 'multipart/form-data',
		processData: false,
		contentType: false,
		cache: false,
		success: function (_data){
			ajaxSuceessAlert(_ajaxData.successMsg,_ajaxData.successLink);
		},
		error: function(_request, _status, _e){
			ajaxErrorAlert(_request, _status, _ajaxData.failLink);
		}
	});
}		


function ajaxSuceessAlert(_alertMsg ="저장을 완료헀습니다.",_successLink){
	if(_successLink != null && _successLink != "" && _successLink != undefined){
		alertSuccessLink("성공", _alertMsg, _successLink);
	} else{
		alertSuccess("성공", _alertMsg);
	}	
}

function ajaxErrorAlert(_request, _status, _failLink){
	if(_request.status != null && _request.status != "" && _request.status != undefined){
		if(_request.status == 401){
			alertErrorLink("에러", "인증이 만료되었습니다.", _failLink);
		} else if(_request.status == 403){ 
			alertError("에러", "실행 권한이 없습니다.");
		} else if(_request.status == 404){
			alertError("에러", "잘못된 호출 입니다.");
		} else {
			if(_request.responseText != null && _request.responseText != "" 
				&& _request.responseText != undefined){
					try{
						let json = JSON.parse(_request.responseText);
						alertError("에러", json.message);
					} catch(e){
						alertError("에러", _request.responseText);
					}			
			} else{
				alertError("에러", "전송에 실패 했습니다.");
			}
		} 
	} else{
		alertErrorLink("에러", "전송에 실패 했습니다.");
	}	
}

let timer;
let isTimerRunning = false; // 인증번호 발송하고 타이머 함수 실행

function sendAuthNum(){    	// 남은 시간	
	let leftSec = 180,	
	display = document.querySelector('#timer');	
	// 이미 타이머가 작동중이면 중지	
	if (isTimerRunning){	   
		clearInterval(timer);	
	} else {    	
		isTimerRunning = true;    
	}     
	startTimer(leftSec, display);
} 

function startTimer(count, display) {        
	let minutes, seconds;        
	timer = setInterval(function () {        
		minutes = parseInt(count / 60, 10);        
		seconds = parseInt(count % 60, 10);         
		minutes = minutes < 10 ? "0" + minutes : minutes;        
		seconds = seconds < 10 ? "0" + seconds : seconds;         
		display.textContent = minutes + ":" + seconds;         // 타이머 끝        
		if (--count < 0) {	     
			clearInterval(timer);	     
			display.textContent = "";	     
			isTimerRunning = false;        
		}    
	}, 1000);
}

function swalInput(_value){
	let value = _value.value;
	if(value.length > 0){
		$('#swal2-validation-message').hide();
	}
}