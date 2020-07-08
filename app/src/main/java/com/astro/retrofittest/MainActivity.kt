package com.astro.retrofittest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import com.astro.retrofittest.data_cf.cf_api
import com.astro.retrofittest.data_cf.cf_data
import com.google.gson.Gson
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    var progressBar:ProgressBar?=null

    var editTextInput:EditText? = null

    var textViewFirstName:TextView? =null
    var textViewLastName:TextView? =null
    var textViewRank:TextView? =null
    var textViewRating:TextView? =null
    var textViewContribution:TextView? = null
    var textViewMaxRank:TextView? =null
    var textViewMaxRating:TextView? =null
    var textViewHandle:TextView? =null
    var textViewOrganisation:TextView?=null


    var firstName: String? = null;
    var lastName: String? = null;
    var rank: String? = null;
    var rating: String? = null;
    var contribution: String? = null;
    var maxRank: String? = null;
    var maxRating: String? = null;
    var handle: String? = null;
    var organisation: String? = null



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        textViewFirstName=findViewById(R.id.textViewFirstName)
        textViewLastName=findViewById(R.id.textViewLastname)
        textViewRank=findViewById(R.id.textViewRank)
        textViewRating=findViewById(R.id.textViewRating)
        textViewContribution=findViewById(R.id.textViewContribution)
        textViewMaxRank=findViewById(R.id.textViewMaxRank)
        textViewMaxRating=findViewById(R.id.textViewMaxRating)
        textViewHandle=findViewById(R.id.textViewHandle)
        textViewOrganisation=findViewById(R.id.textViewOrganisation)

        editTextInput = findViewById(R.id.editTextTextPersonName)

        progressBar = findViewById(R.id.progressBar)





}



    fun submitClicked(view: View) {
        progressBar?.visibility = View.VISIBLE
        val userInput:String = editTextInput?.text.toString()
        if(userInput.isBlank()){
            progressBar?.visibility = View.INVISIBLE

            Toast.makeText(applicationContext,"Enter Username",Toast.LENGTH_SHORT).show()
        }else{
            val apiService = cf_api()


            val response_cf = apiService.getUserinfo(userInput)



            cf_api.invoke().getUserinfo(userInput).enqueue(object : Callback<cf_data>{
                override fun onFailure(call: Call<cf_data>, t: Throwable) {
                    t.printStackTrace()
                }

                override fun onResponse(call: Call<cf_data>, response: Response<cf_data>) {
                    Log.i("In Response","TRUE")
                    Log.i("Respose",response.code().toString())
                    if(response.code().equals(400)){
                        Toast.makeText(applicationContext,"User not found",Toast.LENGTH_SHORT).show()
                    }else if(response.code().equals(200)){
                        progressBar?.visibility = View.INVISIBLE

                        val data = response.body()


                        data?.let {
                            firstName = it.result[0].firstName
                            lastName = it.result[0].lastName
                            rank = it.result[0].rank
                            rating = it.result[0].rating.toString()
                            maxRank = it.result[0].maxRank
                            maxRating = it.result[0].maxRating.toString()
                            contribution = it.result[0].contribution.toString()
                            handle = it.result[0].handle
                            organisation = it.result[0].organization

                            val sError = "Data Not Found"

                           /* textViewFirstName?.setText(sError)
                            textViewLastName?.setText(""+sError)
                            textViewRank?.setText(sError)
                            textViewRating?.setText(sError)
                            textViewContribution?.setText(sError)
                            textViewMaxRank?.setText(sError)
                            textViewMaxRating?.setText(sError)
                            textViewHandle?.setText(sError)
                            textViewOrganisation?.setText(sError)*/


                            if(firstName.toString()!="null"){textViewFirstName?.setText("Lastname: "+firstName)}else{textViewFirstName?.setText("Firstname: "+sError)}
                            if(lastName.toString()!="null"){textViewLastName?.setText("Lastname: "+lastName)}else{textViewLastName?.setText("Lastname: "+sError)}
                            if(rank.toString()!="null"){textViewRank?.setText("Rank: "+rank)}else{textViewRank?.setText("Rank: "+sError)}
                            if(rating.toString()!="0"){textViewRating?.setText("Rating: "+rating)}else{textViewRating?.setText("Rating: "+sError)}
                            if(maxRank.toString()!="null"){textViewMaxRank?.setText("MaxRank: "+maxRank)}else{textViewMaxRank?.setText("MaxRank: "+sError)}
                            if(maxRating.toString()!="0"){textViewMaxRating?.setText("MaxRating: "+maxRating)}else{textViewMaxRating?.setText("MaxRating: "+sError)}
                            if(contribution.toString()!="null"){textViewContribution?.setText("Contribution: "+contribution)}else{textViewContribution?.setText("Contribution: "+sError)}
                            if(handle.toString()!="null"){textViewHandle?.setText("Handle: "+handle)}else{textViewHandle?.setText("Handle: "+sError)}
                            if(organisation.toString()!="null"){textViewOrganisation?.setText("Oragnisation: "+organisation)}else{textViewOrganisation?.setText("Oragnisation: "+sError)}


                        }


                    }

                }

            })
        }


    }
}