

<h1>Dependent Dropdown</h1>



<table>

<tr>
	<th>Country</th>
	<td>
		<select name="Country" onchange="load_cities(this)">
			@foreach($Countries as $conutry)
				<option value="{{$conutry->CountryId}}">{{$conutry->Name}}</option>
			@endforeach
		</select>
	</td>
</tr>
<tr>
	<th>City</th>
	<td>
		<select id="City">
			<option value="--"> select City </option>
		</select>
	</td>
</tr>
<tr>
	<th>Territory</th>
	<td>
		<select name="Territory">
			<option value="--"> select Territory </option>
		</select>
	</td>
</tr>


	
</table>




<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>


<script>

function load_cities(country)
{
	//alert(country.value);
	var city=document.getElementById('City');
	$.ajax({
		url:"{{action('TestController@cities')}}?CountryId="+country.value,
		success:function(res)
		{
			city.innerHTML="";
			// alert(JSON.stringify(res));
			// var result=JSON.parse(res);
			res.forEach(function(item){
				city.innerHTML+="<option value='"+item.CityId+"' >"+item.Name+"</option>";
			});

		}

	});


}




</script>



