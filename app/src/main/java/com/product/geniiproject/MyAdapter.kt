import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.project.geniiproject.Card
import com.project.geniiproject.R

/**THIS CLAS IS TO CONVERT THE DEFAULT LIST VIEW STYLE TO THE CUSTOM STYLE**/


class MyAdapter(context: Context, resource: Int, objects: ArrayList<Card>) :
    ArrayAdapter<Card>(context, resource, objects) {

    @SuppressLint("ViewHolder")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

        /** The LayoutInflater class is used to instantiate the contents of layout XML
         *  files into their corresponding View objects.
         *  In other words, it takes an XML file as input and builds the View objects from it.*/

        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        /** LINKS TO THE DESIGN WE WANT**/
        val listItemView = inflater.inflate(R.layout.list_row, parent, false)

        // Get references to the views in the list item layout
        val questionCard = listItemView.findViewById<TextView>(R.id.questiontext)

        // Bind data to the views
        val item = getItem(position)

        if (item != null) {
            questionCard.text = item.getQuestion()
        }

        return listItemView
    }
}
