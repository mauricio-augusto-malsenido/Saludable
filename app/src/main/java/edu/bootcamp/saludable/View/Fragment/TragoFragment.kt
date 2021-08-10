package edu.bootcamp.saludable.View.Fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import edu.bootcamp.saludable.Api.Implementation.ApiTragoImp
import edu.bootcamp.saludable.Model.Trago
import edu.bootcamp.saludable.R
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TragoFragment : Fragment() {

    lateinit var txt_trago: TextView
    lateinit var txt_categoria: TextView
    lateinit var img_trago: ImageView
    lateinit var btn_aceptar: Button
    lateinit var api: ApiTragoImp

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_trago, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        inicializarComponentes(view)
        api = ApiTragoImp()

        btn_aceptar.setOnClickListener {
            finalizarFragment()
        }

        api.getTrago().enqueue(object : Callback<Trago>{
            override fun onResponse(call: Call<Trago>, response: Response<Trago>) {
                if (response.body() != null)
                    cargarTrago(response.body())
            }

            override fun onFailure(call: Call<Trago>, t: Throwable) {
                Log.e("apirest", t.message.toString())
            }

        })
    }

    private fun inicializarComponentes(view: View)
    {
        txt_trago = view.findViewById(R.id.txt_trago)
        txt_categoria = view.findViewById(R.id.txt_categoria)
        img_trago = view.findViewById(R.id.img_trago)
        btn_aceptar = view.findViewById(R.id.btn_aceptar)
    }

    private fun cargarTrago(data: Trago?)
    {
        txt_trago.setText(data!!.drinks[0].strDrink)
        txt_categoria.setText(data!!.drinks[0].strCategory)

        Glide
            .with(requireContext())
            .load(data!!.drinks[0].strDrinkThumb)
            .into(img_trago)
    }

    private fun finalizarFragment()
    {
        val transaction = requireActivity().supportFragmentManager.beginTransaction()
        transaction.remove(this)
        transaction.commit()
    }
}