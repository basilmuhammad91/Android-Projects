<?php

namespace App\Http\Controllers;

use Illuminate\Http\Request;
use App\Model\Country;
use App\Model\City;

class TestController extends Controller
{
    //
    public function index()
	{
		$Countries=Country::get();

		return view("index")->with("Countries",$Countries);
	}
	  public function cities(Request $req)
	{
		$c=City::where(["CountryId"=>$req->CountryId])->get();

		return $c;
	}
}
