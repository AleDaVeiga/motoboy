$(document).ready(function(){
	function excludeField(event){
		event.preventDefault();
		var divGroup = $(this).parents("div.form-group");
		divGroup.remove();
	}
	
	function addField(event){
		event.preventDefault();
		var divGroup = $(this).parents("div.form-group");
		var next = parseInt(divGroup.find("input[name='count']").val());
		
		var newDivGroup = divGroup.clone();
		newDivGroup.find("input[name='count']").remove();
		
		var newInputField = newDivGroup.find(".input-group input").first();
		if(newInputField.attr("data-mask")) {
			newInputField.mask(newInputField.attr("data-mask"));
		}
		
		var newBtnAction = newDivGroup.find(".input-group-btn a.btn");
		newBtnAction.removeClass("btn-success");
		newBtnAction.removeClass("add-more");
		newBtnAction.addClass("btn-danger");
		newBtnAction.addClass("exclude-me");
		newBtnAction.click(excludeField);
		
		var newImg = newDivGroup.find(".input-group-btn span");
		newImg.removeClass("glyphicon-plus");
		newImg.addClass("glyphicon-minus");
		
		var labelField = divGroup.find(".control-label");
		labelField.text(labelField.text().replace(next + 1, next + 2));
		
		var inputField = divGroup.find(".input-group input").first();
		inputField.attr("id", inputField.attr("id").replace(next, next + 1));
		inputField.attr("name", inputField.attr("name").replace(next, next + 1));
		inputField.val("").trigger('focus');
		if(newInputField.attr("data-mask")) {
			inputField.mask(newInputField.attr("data-mask"));
		}
		
		divGroup.before(newDivGroup);
		
		divGroup.find("input[name='count']").val(next + 1);
	}
	
	$(".add-more").click(addField);
	$('.exclude-me').click(excludeField);
});