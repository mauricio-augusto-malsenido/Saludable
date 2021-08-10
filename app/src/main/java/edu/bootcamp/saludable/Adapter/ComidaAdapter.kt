package edu.bootcamp.saludable.Adapter

import android.transition.AutoTransition
import android.transition.TransitionManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import edu.bootcamp.saludable.Model.Comida
import edu.bootcamp.saludable.R

class ComidaAdapter (val lista: ArrayList<Comida>) :
    RecyclerView.Adapter<ComidaAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_rv_comida,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.txt_hora.text = lista[position].hora
        holder.txt_tipo_comida.text = lista[position].tipoComida
        holder.txt_comida_principal.text = lista[position].comidaPrincipal
        holder.txt_comida_secundaria.text = lista[position].comidaSecundaria
        holder.txt_bebida.text = lista[position].bebida
        holder.txt_postre.text = lista[position].postre
        holder.txt_otro_alimento.text = lista[position].otroAlimento

        holder.card_expandible.visibility = View.GONE // Cardview inicia contraida

        holder.card_comida.setOnClickListener(View.OnClickListener {
            if (holder.card_expandible.visibility == View.GONE){
                // Expandir Cardview
                TransitionManager.beginDelayedTransition(holder.card_comida as ViewGroup, AutoTransition())
                holder.card_expandible.visibility = View.VISIBLE
                holder.img_indicador.setImageResource(R.drawable.ic_arrow_up)
            }
            else
            {
                // Contraer Cardview
                TransitionManager.beginDelayedTransition(holder.card_comida as ViewGroup, AutoTransition())
                holder.card_expandible.visibility = View.GONE
                holder.img_indicador.setImageResource(R.drawable.ic_arrow_down)
            }
        })
    }

    override fun getItemCount(): Int {
        return lista.size
    }

    class ViewHolder(view: View): RecyclerView.ViewHolder(view)
    {
        var txt_hora: TextView
        var txt_tipo_comida: TextView
        var txt_comida_principal: TextView
        var txt_comida_secundaria: TextView
        var txt_bebida: TextView
        var txt_postre: TextView
        var txt_otro_alimento: TextView
        var card_comida: CardView
        var img_indicador: ImageView
        var card_expandible: LinearLayout

        init {
            txt_hora = view.findViewById(R.id.txt_hora)
            txt_tipo_comida = view.findViewById(R.id.txt_tipo_comida)
            txt_comida_principal = view.findViewById(R.id.txt_comida_principal)
            txt_comida_secundaria = view.findViewById(R.id.txt_comida_secundaria)
            txt_bebida = view.findViewById(R.id.txt_bebida)
            txt_postre = view.findViewById(R.id.txt_postre)
            txt_otro_alimento = view.findViewById(R.id.txt_otro_alimento)
            card_comida = view.findViewById(R.id.card_comida)
            img_indicador = view.findViewById(R.id.img_indicador)
            card_expandible = view.findViewById(R.id.card_expandible)
        }
    }
}