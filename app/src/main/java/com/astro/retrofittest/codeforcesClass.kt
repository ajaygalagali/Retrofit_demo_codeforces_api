package com.astro.retrofittest



data class codeforcesClass(val status:String,val result:List<items>) {

}

class items(val handle:String,val rank:String,val rating:Int,val contribution:Int){}