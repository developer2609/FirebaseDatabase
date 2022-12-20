import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.developer.firebasedatabase.Person
import com.developer.firebasedatabase.databinding.ItemRvBinding

class RvAdapter (var list: List<Person>) : RecyclerView.Adapter<RvAdapter.VP_Vh>() {

    inner class VP_Vh(var itemListBinding: ItemRvBinding):
        RecyclerView.ViewHolder(itemListBinding.root) {
        fun onBind(person: Person,position: Int) {

            itemListBinding.tvNameItem.text=person.name
            itemListBinding.tvAgeItem.text=person.age.toString()

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VP_Vh {
        return VP_Vh(ItemRvBinding.inflate(LayoutInflater.from(parent.context),parent,false))


    }

    override fun onBindViewHolder(holder: VP_Vh, position: Int) {
        holder.onBind(list[position],position)
    }

    override fun getItemCount(): Int = list.size






}