let summerNoteFileList = new Array();
let summerNoteFileCount = 0;

function customSummerNote1(){
		$('#summernote').summernote({
			toolbar: [
			    // [groupName, [list of button]]
			    ['fontname', ['fontname']],
			    ['fontsize', ['fontsize']],
			    ['style', ['bold', 'italic', 'underline','strikethrough', 'clear']],
			    ['color', ['forecolor','color']],
			    ['table', ['table']],
			    ['para', ['ul', 'ol', 'paragraph']],
			    ['height', ['height']],
			    ['insert',['picture','link']],
			    ['view', ['codeview','help']]
			],
			placeholder: '내용을 입력해 주세요.',
			height: 300,                 // 에디터 높이
			minHeight: null,             // 최소 높이
			maxHeight: null,             // 최대 높이
			focus: false,                  // 에디터 로딩후 포커스를 맞출지 여부
			lang: "ko-KR",					// 한글 설정
			callbacks :{
				onImageUpload : function(files) {
					let maxSize = 1024 * 1024 * 10; //5MB

					for(let i = 0; i < files.length; i ++){
						if(files[i]['type'] != 'image/jpg'){
							if(files[i]['type'] != 'image/jpeg'){
								if(files[i]['type'] != 'image/png'){
									if(files[i]['type'] != 'image/gif'){
										if(files[i]['type'] != 'image/bmp'){
											if(files[i] != 'image/jfif'){
												alertWarning('경고', '지원되는 파일이 아닙니다.');
												return;		
											}
										}
									}
								}
							}
						}					
		                  
						if(files[i]['size'] > maxSize){
							alertWarning('경고', '이미지 용량이 초과하였습니다.(제한 10MB)');
							return;
						}
						summerNoteFileList[summerNoteFileCount] = files[0];
					}

					let standByDoc = $('#summernote').summernote('code') + '<img id="img_index' + summerNoteFileCount + '" class="note_img">';
					$('#summernote').summernote('code', standByDoc);
					
					let fr = new FileReader();
			        fr.onload = function () {
			        	let noteImgLength = $('.note_img').length == 0 ? 0 : $('.note_img').length - 1;
			            let summernoteImg = $('.note_img').eq(noteImgLength).attr('src', fr.result);
			            summernoteImg.attr('width', '100%');
			        }
			        fr.readAsDataURL(files[0]);
					summerNoteFileCount++;				
				},
				onPaste: function (e) {
					let clipboardData = e.originalEvent.clipboardData;
					if (clipboardData && clipboardData.items && clipboardData.items.length) {
						let item = clipboardData.items[0];

						if (item.kind === 'file' && item.type.indexOf('image/') !== -1) {
							e.preventDefault();
						}
					}
				}
			}
		});
	}
function getContents(){
	let contents = $('#summernote').summernote('code');
	contents = contents.replace(/<\/?script("[^"]*"|'[^']*'|[^>])*(>|$)/ig, "");
	return contents;
}
function getOnlyTextContents(){
	let contents = this.getContents().replace(/<\/?[^img]("[^"]*"|'[^']*'|[^>])*(>|$)/ig, "");
	return contents
}

function getFileList(){
	for(let i = 0; i < summerNoteFileList.length; i ++){
		if(!($('#img_index' + i).length > 0)){
			summerNoteFileList.splice(i, 1);	
		}
	}

	return summerNoteFileList;	
}
function getFileListLength(){
	let imgLength = $('.note_img').length
	return imgLength;
}