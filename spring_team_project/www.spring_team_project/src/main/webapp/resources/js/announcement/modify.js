
$(document).ready(function() {
	$('#summernote').summernote({
        width : 900,
        height: 600, 
        focus: true,                  // 에디터 로딩후 포커스를 맞출지 여부
        lang: "ko-KR",					// 한글 설정
        placeholder: '내용',
        disableResizeEditor: true,	// 크기 조절 기능 삭제
        toolbar: [
          ['fontname', ['fontname']],
          ['fontsize', ['fontsize']],
          ['style', ['bold', 'italic', 'underline','strikethrough', 'clear']],
          ['color', ['forecolor','color']],
          ['table', ['table']],
          ['para', ['ul', 'ol', 'paragraph']],
          ['height', ['height']],
          ['insert',['picture','link','video']],
          ['view', ['fullscreen', 'help']]
        ],
      fontNames: ['Arial', 'Arial Black', 'Comic Sans MS', 'Courier New','맑은 고딕','궁서','굴림체','굴림','돋움체','바탕체'],
      fontSizes: ['8','9','10','11','12','14','16','18','20','22','24','28','30','36','50','72'],
      callbacks: {
        onImageUpload: function(files) {
            uploadImage(files[0]);
        }
    }
    })
    function uploadImage(file) {
      var formData = new FormData();
      formData.append('file', file);

      $.ajax({
          url: '/announcement/update',
          method: 'POST',
          data: formData,
          contentType: false,
          processData: false,
          enctype : 'multipart/form-data',
          success: function(url) {
            console.log('Image uploaded successfully. URL:', url);
            var imgTag = '<img src="' + url + '" />';
            $('#summernote').summernote('pasteHTML', imgTag);
          },
          error: function() {
              console.error('Error uploading image');
          }
      });
    }

});
