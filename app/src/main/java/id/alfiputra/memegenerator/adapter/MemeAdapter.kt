package id.alfiputra.memegenerator.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import id.alfiputra.memegenerator.R
import id.alfiputra.memegenerator.model.Meme

class MemeAdapter(private val memeList: ArrayList<Meme>) :
    RecyclerView.Adapter<MemeAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_meme, parent, false)
        return ViewHolder(view)
    }


    override fun getItemCount(): Int {
        return memeList.size
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        return holder.bind(memeList[position])
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var imageView: ImageView = itemView.findViewById(R.id.iv_itemMeme)
        fun bind(meme: Meme) {
            Picasso.get().load(meme.url).into(imageView)
        }
        
    }
}

