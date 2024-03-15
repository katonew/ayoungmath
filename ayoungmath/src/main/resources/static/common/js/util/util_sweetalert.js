function alertLoading(){
	Swal.fire({
		title: "",
		html: "서버에 요청 중입니다.",
		allowOutsideClick: false	
	});
	Swal.showLoading();
}
function alertSuccess(_title, _msg){
	Swal.fire({
		title: _title,
		html: _msg,
		icon: 'success',
		confirmButtonText:'확인',
		confirmButtonColor: '#00A651'
	});
}
	
function alertInfo(_title, _msg){
	Swal.fire({
		title: _title,
		html: _msg,
		icon: 'info',
		confirmButtonText: '확인',
		confirmButtonColor: '#00A651'
	});
}

function alertWarning(_title, _msg){
	Swal.fire({
		title: _title,
		html: _msg,
		icon: 'warning',
		confirmButtonText: '확인',
		confirmButtonColor: '#00A651'
	});
}

function alertWarningFocus(_title, _msg,_selector,_type="id"){
	Swal.fire({
		title: _title,
		html: _msg,
		icon: 'warning',
		confirmButtonText: '확인',
		confirmButtonColor: '#00A651',
		didClose: () => {
	     	if(_selector){
				if(_type=="id"){
					$('#'+_selector).focus();
				}else if(_type=="summernote"){
					$('#'+_selector).summernote('focus');
				}else if("obj"){
					_selector.focus();
				}
			}
	     }
	}); 
}

function alertReady(){
	Swal.fire({
		title: "준비중",
		html: "준비중입니다.",
		icon: 'warning',
		confirmButtonText: '확인',
		confirmButtonColor: '#00A651'
	}); 
}

function alertError(_title, _msg){
	Swal.fire({
		title: _title,
		html: _msg,
		icon: 'error',
		confirmButtonText: '확인',
		confirmButtonColor: '#00A651'
	});
}

function alertSuccessLink(_title, _msg, _path){
	Swal.fire({
		title: _title,
		html: _msg,
		icon: 'success',
		confirmButtonText: '확인',
		confirmButtonColor: '#00A651',
		allowOutsideClick: false
	})
	.then(function(result){
		if(result.value){
			if(_path){
				location.href = _path;
			}
		}
	});
}

function alertErrorLink(_title, _msg, _path){
	Swal.fire({
		title: _title,
		html: _msg,
		icon: 'error',
		confirmButtonText: '확인',
		confirmButtonColor: '#00A651',
		allowOutsideClick: false
	})
	.then(function(result){
		if(result.value){
			if(_path){
				location.href = _path;
			}
		}
	});
}