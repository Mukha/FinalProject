// The root URL for the RESTful services
var rootURL = "http://localhost:8080/FinalProject/rest/laptops";

var currentLaptop;

// Retrieve list when application starts
findAll();

// Nothing to delete in initial application state
$('#btnDelete').hide();

// Register listeners
$('#btnSearch').click(function() {
	search($('#searchKey').val());
	return false;
});

// Trigger search when pressing 'Return' on search key input field
$('#searchKey').keypress(function(e){
	if(e.which == 13) {
		search($('#searchKey').val());
		e.preventDefault();
		return false;
    }
});

$('#btnAdd').click(function() {
	newLaptop();
	return false;
});

$('#btnSave').click(function() {
	if ($('#laptopId').val() == '')
		addLaptop();
	else
		updateLaptop();
	return false;
});

$('#btnDelete').click(function() {
	deleteLaptop();
	return false;
});

$('#laptopList a').live('click', function() {
	findById($(this).data('identity'));
});

// Replace broken images with default picture
$("img").error(function(){
  $(this).attr("src", "pics/sample.jpg");

});

function search(searchKey) {
	if (searchKey == '') 
		findAll();
	else
		findByName(searchKey);
}

function newLaptop() {
	$('#btnDelete').hide();
	currentLaptop = {};
	renderDetails(currentLaptop); // Display empty form
}

function findAll() {
	console.log('findAll');
	$.ajax({
		type: 'GET',
		url: rootURL + "/all",
		dataType: "json", // data type of response
		success: renderList
	});
}

function findByName(searchKey) {
	console.log('findByName: ' + searchKey);
	$.ajax({
		type: 'GET',
		url: rootURL + '/search/' + searchKey,
		dataType: "json",
		success: renderList 
	});
}

function findById(id) {
	console.log('findById: ' + id);
	$.ajax({
		type: 'GET',
		url: rootURL + '/' + id,
		dataType: "json",
		success: function(data){
			$('#btnDelete').show();
			console.log('findById success: ' + data.name);
			currentLaptop = data;
			renderDetails(currentLaptop);
		}
	});
}

function addLaptop() {
	console.log('addLaptop');
	$.ajax({
		type: 'POST',
		contentType: 'application/json',
		url: rootURL,
		dataType: "json",
		data: formToJSON(),
		success: function(data, textStatus, jqXHR){
			alert('Laptop created successfully');
			$('#btnDelete').show();
			$('#laptopId').val(data.id);
		},
		error: function(jqXHR, textStatus, errorThrown){
			alert('addLaptop error: ' + textStatus);
		}
	});
}

function updateLaptop() {
	console.log('updateLaptop');
	$.ajax({
		type: 'PUT',
		contentType: 'application/json',
		url: rootURL + '/' + $('#laptopId').val(),
		dataType: "json",
		data: formToJSON(),
		success: function(data, textStatus, jqXHR){
			alert('Laptop updated successfully');
		},
		error: function(jqXHR, textStatus, errorThrown){
			alert('updateLaptop error: ' + textStatus);
		}
	});
}

function deleteLaptop() {
	console.log('deleteLaptop');
	$.ajax({
		type: 'DELETE',
		url: rootURL + '/' + $('#laptopId').val(),
		success: function(data, textStatus, jqXHR){
			alert('Laptop deleted successfully');
		},
		error: function(jqXHR, textStatus, errorThrown){
			alert('deleteLaptop error');
		}
	});
}

function renderList(data) {
	// JAX-RS serializes an empty list as null, and a 'collection of one' as an object (not an 'array of one')
	var list = data == null ? [] : (data instanceof Array ? data : [data]);

	$('#laptopList li').remove();
    if (Array.isArray(list[0].laptop)) {
        $.each(list[0].laptop, function(index, laptop) {
            $('#laptopList').append('<li><a href="#" data-identity="' + laptop.id + '">'+laptop.brandName+'</a></li>');
        });
    } else {
        $('#laptopList').append('<li><a href="#" data-identity="' + list[0].laptop.id + '">'+list[0].laptop.brandName+'</a></li>');
    }

}

function renderDetails(laptop) {
	$('#laptopId').val(laptop.id);
	$('#brandname').val(laptop.brandName);
	$('#processor').val(laptop.processor);
	$('#diagonal').val(laptop.diagonal);
	$('#ram').val(laptop.ram);
	$('#hdd').val(laptop.HDD);
	$('#pic').attr('src', 'pics/' + laptop.picture);
	$('#os').val(laptop.os);
}

// Helper function to serialize all the form fields into a JSON string
function formToJSON() {
	var laptopId = $('#laptopId').val();
	return JSON.stringify({
		"id": laptopId == "" ? null : laptopId,
		"brandName": $('#brandname').val(),
		"processor": $('#processor').val(),
		"diagonal": $('#diagonal').val(),
		"ram": $('#ram').val(),
		"HDD": $('#hdd').val(),
		"picture": currentLaptop.picture,
		"os": $('#os').val()
		});
}
