//숫자만
function setNumberOnly(e){
    e.value = e.value.replace(/[^0-9]/gi, "");
}
//숫자 + 콤마
function setNumberComma(obj) {
    obj.value = comma(uncomma(obj.value));
}
//콤마생성
function comma(str) {
    str = String(str);
    return str.replace(/(\d)(?=(?:\d{3})+(?!\d))/g, '$1,');
}
//콤마제거
function uncomma(str) {
        str = String(str);
        return str.replace(/[^\d]+/g, '');
}
//핸드폰, 전화번호 하이픈입력
function setPhoneNumber(e) {
    e.value = e.value.replace(/(^02.{0}|^01.{1}|[0-9]{3})([0-9]+)([0-9]{4})/,"$1-$2-$3");
}
//한글만
function setKoOnly(e){
    e.value = e.value.replace(/[a-z0-9]|[ \[\]{}()<>?|`~!@#$%^&*-_+=,.;:\"'\\]/g, "");
}
//영문만
function setEnOnly(e){
    e.value = e.value.replace(/[ㄱ-ㅎ|ㅏ-ㅣ|가-힣]|[ \[\]{}()<>?|`~!@#$%^&*-_+=,.;:\"'\\]/g, "");
}
//문자만입력
function setTxtOnly(e){
    e.value = e.value.replace(/[ \[\]{}()<>?|`~!@#$%^&*-_+=,.;:\"'\\]/g, "");
}

//뒤로하기
function goBack(){
    window.history.back();
}


/* 
sweet alert 
success(v), error(X), warning(!), info(i), question(?)
*/
//기본alert
function alertBasic(title, text){
    Swal.fire({
        title : title,
        text : text,
    })
}
//기본confirm
function alertConfirm(title, text, link){
    Swal.fire({
        title : title,
        text : text,
        showCancelButton: true, 
    })
    .then(function(result){
        if(result.isConfirmed){
            //이벤트
        }     
    })   
}
//준비중
function alertReady(){
    Swal.fire({
        icon : 'error',
        title : "준비중",
        text : "페이지 준비중입니다",
    })
}

//경고
function alertError(text){
    Swal.fire({
        icon : 'error',
        title : "오류",
        text : text,
    })
}