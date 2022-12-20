package com.developer.firebasedatabase

import RvAdapter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.developer.firebasedatabase.databinding.ActivityMainBinding
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.toObject

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var rvAdapter: RvAdapter
    private lateinit var firebaseFireStore:FirebaseFirestore
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        firebaseFireStore=FirebaseFirestore.getInstance()
        binding.myProgressbar.visibility=View.GONE
        firebaseFireStore.collection("people")
            .get()
            .addOnCompleteListener {
                if (it.isSuccessful){
                    val result=it.result
                    val list=ArrayList<Person>()
                    result.forEach {
                        val person=it.toObject(Person::class.java)
                        list.add(person)
                    }
                    val rvAdapter=RvAdapter(list)
                    binding.rv.adapter=rvAdapter
                }
            }


        binding.apply {
            btnSave.setOnClickListener {
                if (edtName.text.toString().isBlank() || edtAge.text.toString().isBlank()){
                    Toast.makeText(this@MainActivity, "avval to'ldiring", Toast.LENGTH_SHORT).show()

                }else{
                    myProgressbar.visibility=View.VISIBLE
                    val person=Person(edtName.text.toString(),edtAge.text.toString().toInt())
                    firebaseFireStore.collection("people")
                        .add(person)
                        .addOnSuccessListener {
                            Toast.makeText(this@MainActivity, "Saqlandi", Toast.LENGTH_SHORT).show()
                            myProgressbar.visibility=View.GONE
                        }
                        .addOnFailureListener{

                            Toast.makeText(this@MainActivity, "saqlanmadi", Toast.LENGTH_SHORT).show()
                            myProgressbar.visibility=View.GONE
                        }

                }
            }
        }







    }
}