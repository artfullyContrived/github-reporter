$(document).ready(function() {
	$("#startdate").prop('disabled', true);
	$("#enddate").datepicker({
		maxDate : new Date()
	});
	$('#example').dataTable();

}), $(function() {
	$("#user").change(
			function() {
				$.ajax({
					type : 'POST',
					url : "/GitHubFlow/getRepositories",
					data : $("#user").val(),
					dataType : 'json',
					success : function(data) {
						var select = $("#repos");
						var options = '';
						select.empty();
						for (var i = 0; i < data.length; i++) {
							options += "<option value='" + data[i].id + "'>"
									+ data[i].name + "</option>";
						}
						select.append(options);
					},
					failure : function(response) {
						alert(response);
					}
				});
			});
}),

$(function() {
	$("#repos").change(function() {
		$.ajax({
			type : 'POST',
			url : "/GitHubFlow/getStartDate",
			data : $("#repos").val(),
			dataType : 'json',
			success : function(data) {
				alert(data);
				$("#startdate").datepicker({
					minDate : data
				});
			},
			failure : function(response) {
				alert(response);
			}
		});
	});
});
