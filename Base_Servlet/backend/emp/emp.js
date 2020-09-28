$("input.empPhoto").on("change",function() {
		$("img.preview").remove();
		$("span.text1").remove();
		$("div.preview").toggleClass("-on");
		for (let i = 0; i < this.files.length; i++) {
			let reader = new FileReader();
			reader.readAsDataURL(this.files[i]);
			reader.addEventListener("load", function() {
				$("div.preview").append(`<img src="${reader.result}" class ="preview">`);
			});
		};
	});