/**
 * 
 */

var users;

function validateDate() {
	var today = new Date().toISOString().split('T')[0];
	$(".datepicker").attr('min', today);
}

$(document).ready(function() {
	var i = 1;
	$("#add_row").click(function() {
		$('#addr' + i).html(
				"<td>" + (i + 1) + "</td>" +
				"<td><input type=\"text\" name='description"+i+"' id='description"+i+"' placeholder='Description' class=\"form-control input-lg\" required /></td>" +
				"<td><select name='user"+i+"' id='user"+i+"' class='form-control input-lg' required></select></td>" +
				"<td><input type='date' name='date"+i+"' id='date"+i+"' class='form-control datepicker input-lg' required /></td>" +
				"<td><input type='text' name='comment"+i+"' id='comment"+i+"' placeholder='Comments'class='form-control input-lg' /></td>");
		$.each(JSON.parse(users), function (j, user) {
			$('<option>').val(user.firstName + " " + user.lastName).text(user.firstName + " " + user.lastName).attr('data-user', JSON.stringify(user)).appendTo('#user' + i);
		});
		
		$('#task-table').append('<tr id="addr' + (i + 1)+ '"></tr>');
		i++;
		validateDate();
		}
	);
	
	$("#delete_row").click(function() {
		if (i > 1) {
			$("#addr" + (i - 1)).html('');
			i--;
			}
		}
	);
	var team = $('#team').val();
	var xhttp = new XMLHttpRequest();
	xhttp.onreadystatechange = function() {
		if (this.readyState == 4 && this.status == 200) {
			users = this.responseText;
			$.each(JSON.parse(users), function (i, user) {
				$('<option>').val(user.firstName + " " + user.lastName).text(user.firstName + " " + user.lastName).attr('data-user', JSON.stringify(user)).appendTo('#user0');
			});
			}
		};
		xhttp.open("GET", "http://localhost:8080/hardik/utility/team/" + team, true);
		xhttp.send();
		}
);

function createMeeting() {
	var table = document.getElementById("task-table")
	var rCount = table.rows.length;
	var meeting = {};
	meeting.agenda = $('#agenda').val();
	meeting.team = $('#team').val();
	var tasks = [];
	for (var k = 0; k < rCount - 2; k++) {
		try {
			var task = {
				description : $('#description' + k).val(),
				user : JSON.parse($('#user' + k).find("option:selected").attr('data-user')),
				expectedBy : $('#date' + k).val(),
				status : "ASSIGNED",
				comment : $('#comment' + k).val()
			};
			tasks.push(task);
		} catch (e) {
			alert(e);
		}
	}
	meeting.tasks = tasks;
	console.log(meeting);
	$.ajax({
	    url : "http://localhost:8080/hardik/meeting/add",
	    type : "POST",
	    async : true,
	    contentType : "application/json",
	    dataType : "json",
	    data : JSON.stringify(meeting)
	});
//	window.location.replace("http://localhost:8080/hardik/task/mytask");
}