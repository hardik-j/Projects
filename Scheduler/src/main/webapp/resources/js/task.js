/**
 * 
 */
function updateTask() {
	console.log('Update task called!!');
	var payload = {};
	payload.status = $('#taskStatus').val();
	payload.comment = $('#taskComment').val();
	$.ajax({
	    url : "http://localhost:8080/hardik/task/update/" + $('#taskId').val() + "?status=" + $('#taskStatus').val() + "&comment="+$('#taskComment').val(),
	    type : "GET",
	    async : true,
	});
//	window.location.replace('http://localhost:8080/hardik/task/mytask');
}

function loadTask(taskId) {
	$('#taskStatus').html('');
	$.ajax({
	    url : "http://localhost:8080/hardik/task/get/" + taskId,
	    type : "GET",
	    async : true,
	    contentType : "application/json",
	    dataType : "json",
	    success : handleData
	});
}

function handleData(data) {
	console.log()
	var task = JSON.parse(JSON.stringify(data));
	$('#taskId').val(task.taskId);
	$('#taskDescription').val(task.description);
	$('#taskComment').val(task.comment);
	if(task.status == 'ASSIGNED') {
		$('<option>').val('INITIATED').text('INITIATED').appendTo('#taskStatus');
		$('<option>').val('PARKED').text('PARKED').appendTo('#taskStatus');
		$('<option>').val('COMPLETED').text('COMPLETED').appendTo('#taskStatus');
	} else if(task.status == 'INITIATED') {
		$('<option>').val('PARKED').text('PARKED').appendTo('#taskStatus');
		$('<option>').val('COMPLETED').text('COMPLETED').appendTo('#taskStatus');
	} else if(task.status == 'PARKED') {
		$('<option>').val('COMPLETED').text('COMPLETED').appendTo('#taskStatus');
	}
};