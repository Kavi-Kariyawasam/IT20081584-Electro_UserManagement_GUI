$(document).ready(function()
{
	if ($("#alertSuccess").text().trim() == "")
{
		$("#alertSuccess").hide();
}
	$("#alertError").hide();
});
// SAVE ============================================
$(document).on("click", "#btnSave", function(event)
{
// Clear alerts---------------------
		$("#alertSuccess").text("");
		$("#alertSuccess").hide();
		$("#alertError").text("");
		$("#alertError").hide();
// Form validation-------------------
var status = validateUserForm();
	if (status != true)
	{
		$("#alertError").text(status);
		$("#alertError").show();
return;

//$("#formUser").submit();
}
// If valid------------------------
//$("#formUser").submit();
var type = ($("#hidUserIDSave").val() == "") ? "POST" : "PUT";

$.ajax(
{
	url : "UsersAPI",
	type : type,
	data : $("#formUser").serialize(),
	dataType : "text",
	complete : function(response, status)
	{
		onUserSaveComplete(response.responseText, status);
	}
});
//$("#formUser").submit();
});

function onUserSaveComplete(response, status)
{
if (status == "success")
{
var resultSet = JSON.parse(response);
if (resultSet.status.trim() == "success")
{
$("#alertSuccess").text("Successfully saved.");
$("#alertSuccess").show();
$("#divUsersGrid").html(resultSet.data);
} else if (resultSet.status.trim() == "error")
{
$("#alertError").text(resultSet.data);
$("#alertError").show();
}
} else if (status == "error")
{
$("#alertError").text("Error while saving.");
$("#alertError").show();
} else
{
$("#alertError").text("Unknown error while saving..");
$("#alertError").show();
}
$("#hidUserIDSave").val("");
$("#formUser")[0].reset();
}
// UPDATE==========================================
$(document).on("click", ".btnUpdate", function(event)
{
$("#hidUserIDSave").val($(this).closest("tr").find('#hidUserIDUpdate').val());
$("#hidUserIDSave").val($(this).data("UID"));
$("#Name").val($(this).closest("tr").find('td:eq(1)').text());
$("#Acc_Num").val($(this).closest("tr").find('td:eq(2)').text());
$("#Email").val($(this).closest("tr").find('td:eq(3)').text());
$("#Password").val($(this).closest("tr").find('td:eq(4)').text());
$("#Phone").val($(this).closest("tr").find('td:eq(5)').text());
});
//DELETE======================================================================
$(document).on("click", ".btnRemove", function(event)
		{
		$.ajax(
		{
		url : "UsersAPI",
		type : "DELETE",
		data : "UID=" + $(this).data("UserID"),
		dataType : "text",
		complete : function(response, status)
		{
		onUserDeleteComplete(response.responseText, status);
		//window.location.reload(true);
		$("#alertSuccess").fadeTo(2000, 500).slideUp(500, function() {
			$("#alertSuccess").slideUp(500);
		});
		}
		});
		});
function onUserDeleteComplete(response, status)
{
if (status == "success")
{
	var resultSet = JSON.parse(response);
if (resultSet.status.trim() == "success")
{
	$("#alertSuccess").text("Successfully deleted.");
	$("#alertSuccess").show();
	$("#divUsersGrid").html(resultSet.data);
} else if (resultSet.status.trim() == "error")
{
$("#alertError").text(resultSet.data);
$("#alertError").show();
}
} else if (status == "error")
{
$("#alertError").text("Error while deleting.");
$("#alertError").show();
} else
{
$("#alertError").text("Unknown error while deleting..");
$("#alertError").show();
}
}
// CLIENT-MODEL================================================================
function validateUserForm()
{
// NAME
if ($("#Name").val().trim() == "")
{
return "Insert User Name.";
}
//Acc_Num
if ($("#Acc_Num").val().trim() == "")
{
return "Insert Account Number.";
}
//Email-------------------------------
if ($("#Email").val().trim() == "")
{
return "Insert Your Email.";
}
 
// Password------------------------
if ($("#Password").val().trim() == "")
{
return "Insert Your Password.";
}
 
//Phone------------------------
if ($("#Phone").val().trim() == "")
{
return "Insert Phone.";
}
return true;
}